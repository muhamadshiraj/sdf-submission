package sdf.assessment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HttpServer {
    static int port = 3000;
    static Socket socket;
    static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);

        try {

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for Client connection at port:" + port);
            while (true) {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                
                System.out.println("Client connected!");
                Thread thread = new Thread();
                thread.start();
                handleClient(client);
            }



        } catch (Exception e) {
            System.out.println("Connection error :" + e.getMessage());

        }

    }
}
