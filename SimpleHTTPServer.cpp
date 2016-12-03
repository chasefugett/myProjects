/*
 * Copyright (C) 2016 Chase Fugett
 */

/* 
 * File:   SimpleHTTPServer.cpp
 * Author: Chase Fugett
 * 
 * Created on October 16, 2016, 9:39 PM
 *
 * Implements Server.h as a simple HTTP server
 */

#include <boost/asio.hpp>
#include <iostream>
#include <fstream>
#include <string>
#include "Server.h"

/*
 * Constructor
 */
Server::Server(unsigned short port) {
    this->port = port;
}

/**
 * Destructor
 */
Server::~Server() {
}

/**
 * Takes in a file and gives the size back
 * @param inFile
 * @return size as an int
 */
std::string getFileSize(std::ifstream& inFile) {
    inFile.seekg(0, std::ios_base::end);
    const int fileSize = inFile.tellg();
    std::string num = std::to_string(fileSize);
    return num;
}

/**
 * Takes in the extension as a string and returns the MIME type
 * @param line
 * @return MIME type
 */
std::string detType(std::string& line) {
    if (line == "html") {
        return "text/html";
    } else if (line == "png") {
        return "image/png";
    } else if (line == "jpg") {
        return "image/jpg";
    } else if (line == "txt") {
        return "text/plain";
    } else {
        return "text/plain";
    }
}

/**
 * Prints the header if the file was not found
 * @param inFile
 * @param ext
 * @return header as a string
 */
std::string printBadHeader(std::ifstream& inFile, std::string& ext) {
    std::string line = "HTTP/1.1 404 Not Found\r\n";
    line += "Server: SimpleServer\r\n";
    line += "Content-Length: ";
    line += getFileSize(inFile);
    line += "\r\n";
    line += "Connection: Close\r\n";
    line += "Content-Type: ";
    line += detType(ext);
    line += "\r\n\r\n";
    return line;
}

/**
 * Prints the header if the file was good
 * @param inFile
 * @param ext
 * @return the header as a string
 */
std::string printGoodHeader(std::ifstream& inFile, std::string& ext) {
    std::string line = "HTTP/1.1 200 OK\r\n";
    line += "Server: SimpleServer\r\n";
    line += "Content-Length: ";
    line += getFileSize(inFile);
    line += "\r\n";
    line += "Connection: Close\r\n";
    line += "Content-Type: ";
    line += detType(ext);
    line += "\r\n\r\n";
    return line;
}

/**
 * Serves one connection from 1 client by processing HTTP request
 * (ignoring headers) and responding to the request with contents 
 * of a file (specified in the GET request).
 * 
 * @param is The input stream from where the client request is to be read.
 * @param os The output stream where the response is to be written.
 */
void Server::serveClient(std::istream& is, std::ostream& os) {
    std::string item;
    getline(is, item);
    std::string path = item.substr(item.find_first_of(' ') + 1);
    path = path.substr(0, path.find_first_of(' '));
    // Clarification for '/' file name
    if (path == "/") {
        path = "index.html";
    } else {
        // path finally becomes the full path to the file
        path = path.substr(path.find_last_of('/') + 1);
    }
    // Isolates the extension to be used later
    std::string extension = path.substr(path.find_last_of('.') + 1);
    // Creates the file from the path and checks if good
    std::ifstream inFile(path);
    if (!inFile.good()) {
        // Send bad header message back to client with error message
        os << printBadHeader(inFile, extension);
        os << "The following file was not found: " << path << std::endl;
    } else {
        // Send a good header message back to the client
        os << printGoodHeader(inFile, extension);
        // Print the contents of the file to the os
        std::string line;
        inFile.seekg(0);
        while (std::getline(inFile, line)) {
            os << line << std::endl;
        }
    }
}

/**
 * Runs the program as a server processing incoming connections/requests
 * forever.
 * 
 * @param port
 */
void Server::runServer() {
    using namespace boost::asio;
    using namespace boost::asio::ip;
    io_service service;
    // Create end point
    tcp::endpoint myEndpoint(tcp::v4(), port);
    // Create a socket that accepts connections
    tcp::acceptor server(service, myEndpoint);
    // Process client connections one-by-one forever
    while (true) {
        tcp::iostream client;
        // Wait for a client to connect
        server.accept(*client.rdbuf());
        // Process information from client.
        serveClient(client, client);
    }
}
