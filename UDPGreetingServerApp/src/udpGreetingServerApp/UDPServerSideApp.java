package udpGreetingServerApp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * @Israt Jahan Bhuiyan
 *
 */

public class UDPServerSideApp {

    public static void main(String[] args) {

        System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side Application.");

        // Permissible port for this application
        int portNo = 8085;

        try {
            // 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);

            // Continually listen for request
            while (true) {
                // 2. Variable to receive data from the port
                // 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];

                // 3. Object represents packet from client
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);

                // 4. Receive packet
                datagramSocket.receive(receivedPacket);

                // 5. Extract data from packet
                receivedData = receivedPacket.getData();
                String sentence = new String(receivedData, 0, receivedPacket.getLength());
                System.out.println("\nMessage received: " + sentence + ".");

                // 6. Further processing - Sentence Analysis
                SentenceAnalyzer analyzer = new SentenceAnalyzer(sentence);
                int vowelsCount = analyzer.countVowels();
                int consonantsCount = analyzer.countConsonants();
                int punctuationsCount = analyzer.countPunctuations();

                // 7. Get the client information
                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();

                // 8. Prepare the response message
                String response = "Vowels: " + vowelsCount + ", Consonants: " + consonantsCount + ", Punctuations: "
                        + punctuationsCount;
                byte[] outData = response.getBytes();

                // 9. Wrap data into datagram packet
                DatagramPacket outPacket = new DatagramPacket(outData, outData.length, clientAddress, clientPort);

                // 10. Reply to client
                datagramSocket.send(outPacket);
                System.out.println("Message sent: " + response + ".");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("UDPClientSideApp: End of program.");
    }
}
