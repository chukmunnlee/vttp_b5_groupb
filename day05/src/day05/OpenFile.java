package day05;

import java.io.*;

public class OpenFile {

    public void openFile2(String fileName) throws MyAppException {

        int totalRead = 0;
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(fileName);
            byte[] buff = new byte[1024];
            int read = 0;
            while ((read = fis.read(buff)) > 0) {
                // read the file
                totalRead += read;
            }
            System.out.printf(">>> completed reading the file");

        } catch (FileNotFoundException ex) {
            throw new MyAppException("Cannot open file %s".formatted(fileName), ex);

        } catch (IOException ex) {
            MyAppException myAppEx = new MyAppException("Error while processing %s".formatted(fileName), ex);
            myAppEx.setTotalBytesRead(totalRead);
            throw myAppEx;
        } finally {
            if (null != fis)
                try { fis.close(); } catch (Exception ex) { }
        }
    }

    public void openFile(String fileName) throws FileNotFoundException, IOException {

        FileInputStream fis = new FileInputStream(fileName);
        byte[] buff = new byte[1024];
        int read = 0;
        while ((read = fis.read(buff)) > 0) {
            // read the file
        }
        System.out.printf(">>> completed reading the file");
        fis.close();
    }

}
