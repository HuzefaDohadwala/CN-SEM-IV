import java.io.*;
import java.net.*;

public class UDPServer{
    public static void main(String args []){
        try{
        DatagramSocket serverSocket = new DatagramSocket(12345);
        System.out.println("Udp server listening ....");
        byte [] reciverbuffer = new byte [1024];
        while(true){
            DatagramPacket receivePacket = new DatagramPacket(reciverbuffer,reciverbuffer.length);
            serverSocket.receive(receivePacket);
            String receiveData = new String(receivePacket.getData(),0,receivePacket.getLength());
            System.out.println("Received from client :" + receiveData);
            if (receiveData.equalsIgnoreCase("exit")) {
                System.out.println("Client has exited. Closing server...");
                break;
            }
            String responceData = "Server:" + receiveData;
            byte [] sendBuffer = responceData.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer,sendBuffer.length,receivePacket.getAddress(),receivePacket.getPort());
            serverSocket.send(sendPacket);
        }
        serverSocket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}