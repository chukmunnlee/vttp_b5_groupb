package day05;

import java.io.*;
import java.net.*;

public class ClientMain {

   public static void main(String[] args) throws IOException {

      // Set the default port to 3000
      int port = 3000;
      if (args.length > 0)
         port = Integer.parseInt(args[0]);

      // Create a connection to the server
      // Connect to the port on the server
      // localhost - 127.0.0.1
      System.out.println("Connecting to the server");
      Socket sock = new Socket("localhost", port);

      System.out.println("Connected!");
      
      Console cons = System.console();
      // Write a message to the server
      String theMessage = cons.readLine("input: ");

      // Get the output stream
      OutputStream os = sock.getOutputStream();
      Writer writer = new OutputStreamWriter(os);
      BufferedWriter bw = new BufferedWriter(writer);

      // Get the input stream
      InputStream is = sock.getInputStream();
      Reader reader = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(reader);

      // Write the message out
      bw.write(theMessage);
      bw.newLine();
      bw.flush();
      os.flush();

      // Read the result from the server
      String fromServer = br.readLine();

      System.out.printf(">>> SERVER: %s\n", fromServer);

      os.close();
      is.close();
      sock.close();
       
   }
   
}
