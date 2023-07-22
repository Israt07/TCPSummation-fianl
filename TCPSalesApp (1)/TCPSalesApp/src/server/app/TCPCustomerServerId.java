// TCPServer.java
package server.app;

import java.io.DataInputStream;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Customer;
import server.controller.CustomerController;

/**
 * This class, TCPCustomerServerId, represents a TCP server application that processes customer data.
 * It listens for client requests, receives customer IDs from clients, searches for the corresponding
 * customers in the 'CustomerController', and sends back the customer details to the clients using ObjectOutputStream.
 * 
 * @author isratjahanbhuiyan
 *
 */
public class TCPCustomerServerId {

    public static void main(String[] args) {
        int portNo = 80;
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

                // 4. Process request - create input stream to read request
                // Request - customer's id:int
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                // Read customer's id from the client
                int customerId = dis.readInt();
                System.out.println("\tRequest for customer with id: " + customerId);

                // Get customer
                Customer customer = controller.searchCustomerById(customerId);

                // 5. Respond to client
                OutputStream os = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(customer);
                System.out.println("\tSending customer details: " + customer);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
