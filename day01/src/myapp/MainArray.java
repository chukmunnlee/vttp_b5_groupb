package myapp;

public class MainArray {

   public static void main(String[] args) {

      String[] myList = new String[5];

      myList[0] = "hello";
      myList[1] = "world";
      myList[2] = "abc123";

      System.out.printf(">> %s\n", myList[2]);
      System.out.printf(">> size: %d\n", myList.length);

      System.out.printf(">> %s\n", myList[myList.length - 1]);

      // Write a while loop to print every element in an array 
      int idx = 0;
      while (idx < myList.length) {
         System.out.printf("%d. %s\n", idx, myList[idx]);
         idx++;
      }
      
   }
   
}
