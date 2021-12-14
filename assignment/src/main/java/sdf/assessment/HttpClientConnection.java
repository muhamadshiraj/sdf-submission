package sdf.assessment;

import java.io.File;
import java.net.Socket;


public class HttpClientConnection {

    static final File WEB_ROOT = new File(".");
    static final String DEFAULT_FILE = "index.html";
    
    private Socket socket;
    private Main clientConnect;

    public void Server(Socket socket){
        this.socket = socket;
    }    

    private String getContentType(String fileRequest){
        if (fileRequest.endsWith(".htm") || fileRequest.endsWith(".html"))
            return "text/html";
        else 
            return "text/plain";

    }

    private void fileNotFound(String fileRequest){
        String title = "HTTP/1.1 404 Not Found";
        String errorMsg = fileRequest + " not found";
        clientConnect.printError(title,errorMsg);
        System.out.println("File " + fileRequest + "not supported");
    }

    private void methodNotAllowed(String method){
        String title = "HTTP/1.1 405 Method Not Allowed";
        String errorMsg = method + " not supported";
        clientConnect.printError(title,errorMsg);
        System.out.println(method + "not supported");
    }

    
    
}
