package tcpSummationClientApp;

import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 
 * @Israt Jahan Bhuiyan
 *
 */

public class TCPSummationClientApp {

    public static void main(String[] args) {

        System.out.println("\n\t Executing TCPSummationClientApp");

        // Sample data
        int number1 = 101;
        int number2 = 2001;
        int number3 = 50;

        try {

            // 1. Define server information
            int serverPortNo = 8082;
            InetAddress serverAddress = InetAddress.getLocalHost();

            // 2. Bind to the server, request for connection
            Socket socket = new Socket(serverAddress, serverPortNo);

            // 3. Send data to the server
            OutputStream outStream = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(outStream);
            dos.writeInt(number1);
            dos.writeInt(number2);
            dos.writeInt(number3);

            // 4. Process response from the server
            InputStream inStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inStream);
            int sum = dis.readInt();
            int multiplication = dis.readInt();

            // 5. Further processing - display the results
            System.out.println("\tSending to the server: " + number1 + ", " + number2 + ", " + number3);
            System.out.println("\tReceiving from server: Sum=" + sum + ", Multiplication=" + multiplication);

            // Close all the closable
            dis.close();
            dos.close();
            socket.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        System.out.println("\tEnd of execution at TCPSummationClientApp");

    }
}