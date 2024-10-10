package day05;

import java.io.*;
import java.net.*;

public class ReceiveFile {

    private final Socket sock;

    public ReceiveFile(Socket sock) {
        this.sock = sock;
    }

    public void receive() throws IOException {
        // Open input stream to receive file
        InputStream is = sock.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        // Read the metadata
        String fileName = dis.readUTF();
        long fileSize = dis.readLong();

        // Create a file based on the file name
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int bytesRead = 0;
        int recvBytes = 0;
        byte[] buff = new byte[4 * 1024];

        // Read in the file
        while (recvBytes < fileSize) {
            bytesRead = dis.read(buff);
            bos.write(buff, 0, bytesRead);
            recvBytes += bytesRead;
            System.out.printf("Receive %d of %d\n", recvBytes, fileSize);
        }

        // Close the new file
        bos.flush();
        fos.close();

        // Close the socket
        dis.close();
        bis.close();
        is.close();
        sock.close();
    }
}
