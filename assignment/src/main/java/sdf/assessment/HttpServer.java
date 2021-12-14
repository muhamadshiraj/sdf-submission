package sdf.assessment;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HttpServer {
    static int port = 3000;
    static String defaultPath = "static";

    static Socket socket;
    static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
       
        for (int i = 0 ; i < args.length; i++){
            switch (args[i]){
                case "--port":
                    port = Integer.parseInt(args[i + 1]);
                    i++;
                    break;
                case "--docRoot":
                    defaultPath = args[i+1];
                    i++;
                    break;
            }
        }

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Waiting for Client connection at port:" + port);
        
            try {

            
            while (true) {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                
                System.out.println("Client connected!");
                Thread thread = new Thread();
                thread.start();
                //handleClient(client);
                

            }


        } catch (Exception e) {
            System.out.println("Connection error :" + e.getMessage());

        }
        socket.close();
        pool.shutdown();
        

    }
}
