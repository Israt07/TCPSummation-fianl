// TCPClient.java
package client.app;

import java.io.InputStream;

import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import model.Customer;

/**
 * This class, TCPCustomerClientList, represents a TCP client application that requests the list of all customers
 * from the server, receives the list as a response, and displays the customer information in alphabetical order.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class TCPCustomerClientList {
    public static void main(String[] args) {

        try {
            System.out.println("\tExecuting TCPClientApp");

            // Server information
            int serverPortNo = 888;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to the remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to receive response from the server
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            // 2. Read response from the server - cast object
            List<Customer> customers = (List<Customer>) ois.readObject();

            // 3. Process response - display the customers in alphabetical order
            System.out.println("Customer Information (From the server)");
            customers.stream()
                    .sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()))
                    .forEach(customer -> {
                        System.out.println("\tCustomer ID: " + customer.getCustomerId());
                        System.out.println("\tName: " + customer.getName());
                    });

            // Close the socket when done
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
