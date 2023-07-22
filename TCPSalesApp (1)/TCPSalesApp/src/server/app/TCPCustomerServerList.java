// TCPServer.java
package server.app;

import java.io.ObjectOutputStream;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.Customer;
import server.controller.CustomerController;


/**
 * This class, TCPCustomerServerList, represents a TCP server application that provides a list of all customers to clients.
 * It listens for client requests, retrieves the list of customers from the 'CustomerController',
 * and sends back the list to the clients using ObjectOutputStream.
 * 
 * @author isratjahanbhuiyan
 *
 */

public class TCPCustomerServerList {

    public static void main(String[] args) {
        int portNo = 888;
        CustomerController controller = new CustomerController();

        System.out.println("\n\tExecuting TCPServerApp");

        try {
            System.out.println("\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen to requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();

                // 4. Get list of all customers
                List<Customer> customers = controller.getAllCustomers();
                System.out.println("\tSending list of customers to the client");

                // 5. Respond to client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customers);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
