package sdf.assessment;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class HttpClientConnection {


    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("localhost", 3000);

        try {
            System.out.println("Connected with server at port 3000");
            
        
        } catch (Exception e) {
            System.out.println("unable to connect");
            e.printStackTrace();
        
        } finally {
            try {
                if (socket != null)
                    socket.close();
                sc.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
