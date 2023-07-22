// TCPClient.java
package client.app;

import java.io.DataOutputStream;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import model.Customer;



/**
 * This class, TCPCustomerClientId, represents a TCP client application that interacts with the server
 * to search for customer information based on user input (customer ID).
 * The client sends the customer ID to the server, receives the customer details (if found), and displays them.
 * 
 * @author isratjahanbhuiyan
 *
 */
public class TCPCustomerClientId {
    public static void main(String[] args) {

        try {
            System.out.println("\tExecuting TCPClientApp");

            // Server information
            int serverPortNo = 80;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 1. Connect to the remote machine
            Socket socket = new Socket(serverAddress, serverPortNo);

            // Create stream to send request
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            // Create scanner to read user input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter the customer ID (or type 'exit' to quit): ");
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    // Exit the loop and close the socket if user types 'exit'
                    break;
                }

                try {
                    // Parse user input as integer and send to server
                    int customerId = Integer.parseInt(input);

                    // 2. Send request to the server
                    dos.writeInt(customerId);
                    System.out.println("\tRequesting customer with id: " + customerId);

                    // Create stream to receive response from the server
                    InputStream is = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);

                    // 3. Read response from the server - cast object
                    Customer customer = (Customer) ois.readObject();

                    // 4. Process response - display the object
                    System.out.println("\tCustomer Information (From the server)");
                    if (customer != null) {
                        System.out.println("\tCustomer ID: " + customer.getCustomerId());
                        System.out.println("\tName: " + customer.getName());
                    } else {
                        System.out.println("\tCustomer not found!");
                    }

                } catch (NumberFormatException e) {
                    // Invalid input, continue to next iteration
                    System.out.println("Invalid input. Please enter a valid customer ID.");
                }
            }

            // Close the socket when done
            socket.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
