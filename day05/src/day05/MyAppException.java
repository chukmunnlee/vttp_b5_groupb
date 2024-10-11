package day05;

public class MyAppException extends Exception {

   private int totalBytesRead = 0;

   public int getTotalBytesRead() {
      return totalBytesRead;
   }

   public void setTotalBytesRead(int totalBytesRead) {
      this.totalBytesRead = totalBytesRead;
   }

   // Overriding the constructor
   public MyAppException() { }

   public MyAppException(String msg) {
      // call the super class in the constructor
      // the first line
      super(msg);
   }

   public MyAppException(String msg, Throwable t) {
      super(msg, t);
   }
}
