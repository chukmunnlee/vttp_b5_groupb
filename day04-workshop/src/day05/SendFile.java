package day05;

import java.io.*;
import java.net.*;

public class SendFile {

    private String host;
    private int port;
    private File file;

    public SendFile(String host, int port, File file) {
        this.host = host;
        this.port = port;
        this.file = file;
    }

    public void send() throws IOException {
        // perform the send
        Socket sock = new Socket(host, port);

        // Open a OutputStream
        OutputStream os = sock.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        // Open the file for reading
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Get the file information
        String fileName = file.getName();
        long fileSize = file.length();

        // Write the file metadata
        dos.writeUTF(fileName);
        dos.writeLong(fileSize);

        // Write file to server
        int readBytes = 0;
        int sendBytes = 0;
        byte[] buff = new byte[4 * 1024];

        while ((readBytes = bis.read(buff)) > 0) {
            // Write the amount that I have read
            sendBytes += readBytes;
            dos.write(buff, 0, readBytes);
            System.out.printf("Send %d of %d\n", sendBytes, fileSize);
        }

        // Close the file
        bis.close();
        fis.close();

        // Flush connection
        dos.flush();
        bos.flush();
        os.flush();

        // Close connection
        dos.close();
        bos.close();
        os.close();
        sock.close();
    }
}
