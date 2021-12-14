package sdf.assessment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.StringTokenizer;

public class Main {

    private BufferedReader in = null;
    private PrintWriter out = null;
    private BufferedOutputStream dataOut = null;
    
    private String fileRequested;
    private String method;

    public void handleClient(Socket client) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());
        BufferedOutputStream dataOut = new BufferedOutputStream(client.getOutputStream());

        String input = in.readLine();

        StringTokenizer parse = new StringTokenizer(input);
        String method = parse.nextToken().toUpperCase();
        fileRequested = parse.nextToken().toLowerCase();
    }
    
    public void close() throws IOException{
        in.close();
        out.close();
        dataOut.close();
    }

    /* if (!method.equals("GET")) {
            System.out.println("HTTP/1.1 405 Method Not Allowed\r\n");
            System.out.println("\r\n");
            System.out.println(method + "not supported\r\n");
        } else {

        } */
    
    public String getMethod(){
            return this.method;
    }

    public String getFileRequest(){
        return this.fileRequested;
    }
    

    public static byte[] readFileContent(File file, int fileLength) throws IOException {
        FileInputStream fileInput = null;
        byte[] fileContent = new byte[fileLength];

        try {
            fileInput = new FileInputStream(file);
            fileInput.read(fileContent);
        } finally {
            if (fileInput != null)
                fileInput.close();
        }
        return fileContent;
    }

    private static Path getFilePath(String path) {
        if ("/".equals(path)) {
            path = "/index.html";
        }
        return Paths.get("/tmp/www", path);
    }

    public void print(String title, String content, int fileLength, byte[] fileContent) throws IOException{
        out.println(title);
        out.println("Server: Java HTTP Server 1.0");
        out.println("Date: " + new Date());
        out.println("Content-type: " + content);
        out.println("Content-length: " + fileLength);
        out.println();
        out.flush();

        dataOut.write(fileContent, 0, fileLength);
        dataOut.flush();
    }

    private void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) {
        out.println("HTTP/1.1 404 Not Found\r\n");
        out.println("\r\n");
        out.println(fileRequested + "not found\r\n");
    }

    public void printError(String title, String errorMsg) {
        out.println(title);
        out.println();
        out.println(errorMsg);
        out.println();
        out.flush();
    }
}
