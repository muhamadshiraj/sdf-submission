package sdf.assessment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    static int port = 3000;
    static Socket socket;
    static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {

        port = Integer.parseInt(args[0]);

        try {

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for Client connection at port:" + port);

            socket = serverSocket.accept();

            System.out.println("Client connected!");

        } catch (Exception e) {
            System.out.println("Connection error :" + e.getMessage());

        }

    }
}
