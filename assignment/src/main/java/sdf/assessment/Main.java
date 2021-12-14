package sdf.assessment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Main{

    String fileRequested = null;
    
    public void handleClient(Socket client){
        PrintWriter pw = new PrintWriter(client.getOutputStream());
        BufferedReader br = new BufferedReader(InputStreamReader(client.getInputStream()));
        BufferedOutputStream dataOut = new BufferedOutputStream(client.getOutputStream());

        input = br.readLine();
        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase();
        fileRequested = parse.nextToken().toLowerCase();

        if (!method.equals("GET")){
            System.out.println("HTTP/1.1 405 Method Not Allowed\r\n");
            System.out.println("\r\n");
            System.out.println(method + "not supported\r\n");
        }
        else {

        }
    }

    
    private static Reader InputStreamReader(InputStream inputStream) {
        return null;
    }


    private static Object getInputStream() {
        return null;
    }


    public static void handleRequest(){

    }


    public static byte[] readFileContent(File file, int fileLength) throws IOException{
        FileInputStream fileInput = null;
        byte[] fileContent = new byte[fileLength];

        try {
            fileInput = new FileInputStream(file);
            fileInput.read(fileContent);
        }
        finally{
            if (fileInput != null)
                fileInput.close();
        }
        return fileContent;
    }


    private static Path getFilePath(String path){
        if ("/".equals(path)){
            path = "/index.html";
        }
        return Paths.get("/tmp/www", path);
    }


    private void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested){
        out.println("HTTP/1.1 404 Not Found\r\n");
        out.println("\r\n");
        out.println(fileRequested + "not found\r\n");
    }
}
