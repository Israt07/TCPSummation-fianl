package server.app;

import java.io.DataInputStream;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.Customer;
import server.controller.CustomerController;


/**
 * This class, TCPCustomerServerAppName, 
 * represents a TCP server application that processes customer data.
 * It listens for client requests, 
 * receives customer names from clients, searches for the corresponding
 * customers in the 'CustomerController', 
 * and sends back the customer details to the clients using ObjectOutputStream.
 * 
 * @author isratjahanbhuiyan
 *
 */


public class TCPCustomerServerAppName {

    public static void main(String[] args) {
        int portNo = 777;
        CustomerController controller = new CustomerController();

        System.out.println("\n\tExecuting TCPCustomerServerApp");

        try {
            // Display list of all customers
            List<Customer> allCustomers = controller.getAllCustomers();
            System.out.println("List of all customers:");
            for (Customer customer : allCustomers) {
                System.out.println(customer);
            }

            System.out.println("\n\tWaiting for next request");

            // 1. Bind to a port
            ServerSocket serverSocket = new ServerSocket(portNo);

            // 2. Server needs to continually run to listen to requests
            while (true) {
                // 3. Accept request from client
                Socket clientSocket = serverSocket.accept();

                // 4. Process request - create input stream to read request
                // Request - customer's name:string
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                // Read customer's name from the client
                String customerName = dis.readUTF();
                System.out.println("\tRequest for customer with name: " + customerName);

                // Get customer
                Customer customer = controller.searchCustomerByName(customerName);

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
