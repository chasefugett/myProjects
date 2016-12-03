/* 
 * File:   Server.h
 *
 * Copyright (C) 2016 Chase Fugett
 */

#ifndef SERVER_H
#define SERVER_H

#include <iostream>
#include <string>

/** A simple Server class that can act as a web-server to process
*   simple HTTP GET requests.
*/
class Server {
public:
    /**
     * The constructor to have this class operate as a server on a given port.
     *
     * @param port The port number on which this server should listen.
     */
    explicit Server(unsigned short port = 80);
    
    /**
     * The destructor
     */
    virtual ~Server();
    
    /**
     * Serves one connection from 1 client by processing HTTP request
     * (ignoring headers) and responding to the request with contents 
     * of a file (specified in the GET request).
     * 
     * @param is The input stream from where the client request is to be read.
     * @param os The output stream where the response is to be written.
     */
    virtual void serveClient(std::istream& is = std::cin, 
                             std::ostream& os = std::cout);
    /**
     * Runs the program as a server processing incoming connections/requests
     * for ever.
     * 
     * @param port
     */
    virtual void runServer();
    
private:
    unsigned short port;
};

#endif /* SERVER_H */
