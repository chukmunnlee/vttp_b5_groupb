package day05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.*;
import java.util.Date;

// Work for a thread to perform
public class ClientHandler implements Runnable {

   private final Socket sock;

   public ClientHandler(Socket s) {
      sock = s;
   }

   // Entry point for the thread
   @Override
   public void run() {

      String threadName = Thread.currentThread().getName();

      try {
         // Get the input stream
         InputStream is = sock.getInputStream();
         Reader reader = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(reader);

         // Get the output stream
         OutputStream os = sock.getOutputStream();
         Writer writer = new OutputStreamWriter(os);
         BufferedWriter bw = new BufferedWriter(writer);

         // Read from the client
         String fromClient = br.readLine();

         System.out.printf(">>> [%s] CLIENT: %s\n", threadName, fromClient);

         // Process the data
         fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

         // Write result back to client
         bw.write(fromClient);
         bw.newLine();
         bw.flush();
         os.flush();

         os.close();
         is.close();
         sock.close();
      } catch (IOException ex) {
         // Exception handler
         ex.printStackTrace();
      }
   }

}
