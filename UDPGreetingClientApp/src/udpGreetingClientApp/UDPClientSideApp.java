package udpGreetingClientApp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * @Israt Jahan Bhuiyan
 *
 */

public class UDPClientSideApp {

    public static void main(String[] args) {

        System.out.println("\n\tUDPClientSideApp: Demonstration of UDP Client-Side Application.");

        try {
            // 1. Define server port number and address
            int portNo = 8085;
            InetAddress ip = InetAddress.getLocalHost();

            // 2. Prepare and transform data into bytes
            String text = "Hello, how are you? This is a test.";
            byte[] buf = text.getBytes();

            // 3. Wrap data in datagram packet (constructor no 5)
            DatagramPacket outPacket = new DatagramPacket(buf, buf.length, ip, portNo);

            // 4. Create the socket object to transmit the data.
            DatagramSocket datagramSocket = new DatagramSocket();

            // 5. Send datagram packet
            datagramSocket.send(outPacket);
            System.out.println("\n\tSending '" + text + "' (" + text.length() + ") out on the network.");

            // 6. New buffer to extract the received data
            byte[] inData = new byte[65535];

            // 7. Packet to receive data
            DatagramPacket inPacket = new DatagramPacket(inData, inData.length);

            // 8. Receive data
            datagramSocket.receive(inPacket);

            // 9. Extract data
            String response = new String(inPacket.getData(), 0, inPacket.getLength());

            // 10. Display the data received from the server
            System.out.println("\tAnalysis result: " + response);

            datagramSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\tUDPClientSideApp: End of program.");
    }
}
