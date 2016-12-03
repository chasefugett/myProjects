/*
 * File:   AirportServer.cpp
 * Author: Chase Fugett
 *
 * Copyright (C) 2016 Chase Fugett
 * Created on November 7, 2016, 4:48 PM
 *
 * Processes requests from a browser and returns responses in appropriate HTML
 * format. User can search for airports in database by country or city. They can
 * also find all airports within an inputed mile radius of a given reference
 * airport.
 *
 * All airports stored in a SQL database and contain a code, city, country,
 * latitude, and longitude. Data is accessed through mySQL queries.
 *
 * Visit http://ceclnx01.cec.miamioh.edu/~fugettcj/homework7/index.html
 * for working functionality
 */

#define MYSQLPP_MYSQL_HEADERS_BURIED
#include <mysql++/mysql++.h>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <cmath>

using namespace std;
// This is just pi/180
const float DEG_TO_RAD = 0.017453292519943295769236907684886;
const float earthRadius = 3958.75587;
// Minimizes code reuse by declaring HTML headers as strings
const std::string docHeader = "<!DOCTYPE html>\n<html>\n<head>\n"
        "<link type=\"text/css\" rel=\"stylesheet\" href=\"table.css\">"
        "\n</head>\n<body>\n";
const std::string tableHeader = "<tr><th>Code</th><th>City</th><th>Country</th>"
        "<th>Latitude</th><th>Longitude</th><th>Distance</th></tr>\n";

const std::string errorMessage = "<!DOCTYPE html>\n<html>\n<head>\n"
        "<link type=\"text/css\" rel=\"stylesheet\" href=\"table.css\">"
        "\n</head>\n<body>\nSorry. No matching airports were found.\n"
        "</body>\n</html>\n";

// Prototype declaration for url_decode method defined below
std::string url_decode(std::string);

// Helper method to get the reference longitude and latitude from the reference
// airport
std::vector<float> getRefs(const std::string& code) {
    // Connect to database with: database, server, userID, password -- userID
    // and password redacted for privacy
    mysqlpp::Connection myDB("...", "...", "...", "...");
    // Create a query
    mysqlpp::Query query = myDB.query();
    query << "SELECT latitude, longitude FROM Airports WHERE code = \'"
            << code << "\';";
    query.parse();
    // Run the query and get stored results
    mysqlpp::StoreQueryResult result = query.store();
    if (result.empty()) {
        std::vector<float> references = { -1, -1};
        return references;
    }
    std::vector<float> references = {result[0][0], result[0][1]};
    return references;
}

// Helper method for computing the distance between two points on Earth
// using Haversine's formula
int computeDist(const float lat1, const float long1, const float lat2,
        const float long2) {
    double latitudeArc = (lat1 - lat2) * DEG_TO_RAD;
    double longitudeArc = (long1 - long2) * DEG_TO_RAD;
    double latH = sin(latitudeArc * 0.5);
    latH *= latH;
    double longH = sin(longitudeArc * 0.5);
    longH *= longH;
    double temp = cos(lat1 * DEG_TO_RAD) * cos(lat2 * DEG_TO_RAD);
    float ans = earthRadius * (2.0 * asin(sqrt(latH + temp * longH)));
    int convertedAns = static_cast<int> (ans);
    return convertedAns;
}

// Helper method to print out the code, city, country, latitude, and longitude
// for a given row in a 2D array of results
void printRow(mysqlpp::StoreQueryResult result, int row) {
    // code
    std::cout << "<tr>" << "<td>" << result[row][0].c_str() << "</td>";
    // city
    std::cout << "<td>" << result[row][1].c_str() << "</td>";
    // country
    std::cout << "<td>" << result[row][2].c_str() << "</td>";
    // latitude & longitude
    double latitude = result[row][3];
    double longitude = result[row][4];
    std::cout << "<td>" << latitude << "</td>";
    std::cout << "<td>" << longitude << "</td>";
}

// Helper method that prints the reference airport only
void printRefAirport(const std::string& code) {
    // Connect to database with: database, server, userID, password -- userID
    // and password redacted for privacy
    mysqlpp::Connection myDB("...", "...", "...", "...");
    // Create a query
    mysqlpp::Query query = myDB.query();
    query << "SELECT code, city, country, latitude, longitude FROM Airports "
            "WHERE code = \'" << code << "\';";
    query.parse();
    // Run the query and get stored results
    mysqlpp::StoreQueryResult result = query.store();
    printRow(result, 0);
    std::cout << "<td>" << -1 << "</td>" << "</tr>\n";
}

void findNearby(const std::string& code, const int distance) {
    // Connect to database with: database, server, userID, password -- userID
    // and password redacted for privacy
    mysqlpp::Connection myDB("...", "...", "...", "...");
    // Create a query
    mysqlpp::Query query = myDB.query();
    query << "SELECT code, city, country, latitude, longitude FROM Airports "
            "ORDER BY ID;";
    query.parse();
    // Run the query and get stored results
    mysqlpp::StoreQueryResult result = query.store();
    std::vector<float> ref = getRefs(code);
    // If the reference code does not exist in the list of airports
    if (ref[0] == -1) { std::cout << errorMessage;
    } else {
        std::cout << docHeader << "<table>\n" << tableHeader;
        // Print the reference airport first, everything else after
        printRefAirport(code);
        for (size_t row = 0; (row < result.size()); row++) {
            std::string resultCode = result[row][0].c_str();
            // As long as there is a code
            if (resultCode != "") {
                // Calculates the distance between the two airports
                int distDiff = computeDist(ref[0], ref[1], result[row][3],
                        result[row][4]);
                // Outputs the data to the table if the airport is within range
                // of the reference airport and is not the reference airport
                if (distDiff <= distance && distDiff > 0) {
                    printRow(result, row);
                    // calculated distance
                    std::cout << "<td>" << distDiff << "</td>" << "</tr>\n"; }
            }
        }
        std::cout << "</table>\n</body>\n</html>\n";
    }
}

void searchCountry(const std::string& searchLocation) {
    // Connect to database with: database, server, userID, password -- userID
    // and password redacted for privacy
    mysqlpp::Connection myDB("...", "...", "...", "...");
    // Create a query
    mysqlpp::Query query = myDB.query();
    query << "SELECT code, city, country, latitude, longitude FROM Airports"
            " WHERE country LIKE '%" << searchLocation << "%' ORDER BY ID;";
    query.parse();
    // Run the query and get stored results
    mysqlpp::StoreQueryResult result = query.store();
    // Results is a 2D vector of mysqlpp::String objects.
    // If the result returns nothing, then no matching airports were found
    if (result.empty() || (std::string::npos !=
            searchLocation.find_first_of("0123456789"))) {
        std::cout << errorMessage;
    } else {
        std::cout << docHeader << "<table>\n" << tableHeader;
        for (size_t row = 0; (row < result.size()); row++) {
            std::string code = result[row][0].c_str();
            if (code != "") {
                printRow(result, row);
                // Make all entries in distance column -1
                std::cout << "<td>" << -1 << "</td>" << "</tr>\n";
            }
        }
        std::cout << "</table>\n</body>\n</html>\n";
    }
}

void searchCity(const std::string& searchLocation) {
    // Connect to database with: database, server, userID, password -- userID
    // and password redacted for privacy
    mysqlpp::Connection myDB("...", "...", "...", "...");
    // Create a query
    mysqlpp::Query query = myDB.query();
    query << "SELECT code, city, country, latitude, longitude FROM Airports"
            " WHERE city LIKE '%" << searchLocation << "%' ORDER BY ID;";
    query.parse();
    // Run the query and get stored results
    mysqlpp::StoreQueryResult result = query.store();
    // If the result returns nothing, then no matching airports were found
    if (result.empty() || (std::string::npos !=
            searchLocation.find_first_of("0123456789"))) {
        std::cout << errorMessage;
    } else {
        std::cout << docHeader << "<table>\n" << tableHeader;
        for (size_t row = 0; (row < result.size()); row++) {
            std::string code = result[row][0].c_str();
            if (code != "") {
                printRow(result, row);
                // Make all entries in distance column -1
                std::cout << "<td>" << -1 << "</td>" << "</tr>\n";
            }
        }
        std::cout << "</table>\n</body>\n</html>\n";
    }
}

/**
 * Method to process inputs from user.
 *
 * @param inputs Combined input from query string
 */
void process(std::string inputs) {
    std::cout << "Content-Type: text/html\r\n\r\n";
    // Prints name, value from the supplied inputs string.
    std::istringstream is(inputs);
    std::string name, value, operation, locationType, searchLocation, code;
    int distance;
    while (std::getline(is, name, '=')) {
        std::getline(is, value, '&');
        value = url_decode(value);
        // Assigns values to string variables for the search function
        if (value == "search") operation = "search";
        if (value == "City") locationType = "City";
        if (value == "Country") locationType = "Country";
        if (name == "searchLocation") searchLocation = value;
        // Assigns variables for the findNearby function
        if (value == "findNearby") operation = "findNearby";
        if (name == "code") code = value;
        if (name == "distance") distance = std::stoi(value);
    }
    // If the operation is 'search'
    if (operation == "search") {
        if (locationType == "City") searchCity(searchLocation);
        if (locationType == "Country") searchCountry(searchLocation);
    }
    // If the operation is 'findNearby'
    if (operation == "findNearby") {
        findNearby(code, distance);
    }
}

/**
 * URL decode helper method
 *
 * @param str Encoded inputs
 * @return Decoded inputs
 */
std::string url_decode(std::string str) {
    // Decode entities in the form "%xx"
    size_t pos = 0;
    while ((pos = str.find_first_of("%+", pos)) != std::string::npos) {
        switch (str.at(pos)) {
            case '+': str.replace(pos, 1, " ");
                break;
            case '%':
            {
                std::string hex = str.substr(pos + 1, 2);
                char ascii = std::stoi(hex, nullptr, 16);
                str.replace(pos, 3, 1, ascii);
            }
        }
        pos++;
    }
    return str;
}

/*
 * The main method that serves as interface point for CGI
 */
int main() {
    const char QS[] = "QUERY_STRING";
    // Get query string (if any) from environment
    std::string queryStr = getenv(QS) != NULL ? getenv(QS) : "";
    // Get 1 line of POST data from std::cin (for printing)
    std::string postData;
    std::getline(std::cin, postData);
    // Combine parameters to make overall processing easier
    // Print name = value pairs as  result
    process(postData);
    return 0;
}
