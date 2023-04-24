import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            int serverPort = 12345;

            while (true) {
                System.out.print("Enter message to send to server (type 'exit' to exit): ");
                String sendData = inputReader.readLine();

                byte[] sendBuffer = sendData.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);

                clientSocket.send(sendPacket);

                if (sendData.equalsIgnoreCase("exit")) {
                    System.out.println("Closing client...");
                    break;
                }

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from " + receivePacket.getAddress().getHostAddress() + ": " + receivedData);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
