package day05;

import java.io.*;
import java.net.*;

public class FileServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(Constants.PORT);

        System.out.printf("File server started on port %d\n", Constants.PORT);

        while (true) {
            Socket sock = server.accept();
            System.out.printf("New file upload\n");

            ReceiveFile recvFile = new ReceiveFile(sock);
            recvFile.receive();
        }
    }
}
