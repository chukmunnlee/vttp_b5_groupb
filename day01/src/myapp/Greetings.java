package myapp;

import java.io.Console;

public class Greetings {

   public static void main(String[] args) {

      // Declare a variable of type Console
      // and get the console
      Console cons = System.console();
      
      // Prompt the user for something, then result is 
      // assigned to the variable called input
      String input = cons.readLine("What is your name? ");
      String input2 = cons.readLine("How old are you? ");
      // convert string to int
      int age = Integer.parseInt(input2);

      // Print the input
      System.out.printf("Hello. You said '%s'. You also said this: %d\n", input, age);

      input = input.trim();
      System.out.printf("After trim '%s'\n", input);
   }
   
}
