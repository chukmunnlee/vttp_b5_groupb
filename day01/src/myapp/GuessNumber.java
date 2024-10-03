package myapp;

import java.io.Console;
import java.security.SecureRandom;
import java.util.Random;

public class GuessNumber {

   public static void main(String[] args) {

      // Create a random number generator
      Random rand = new SecureRandom();

      // Generate a number between 1 - 10
      int toGuess = rand.nextInt(10) + 1;

      System.out.printf(">>> To guess: %d\n", toGuess);

      // get input from user
      Console cons = System.console();

      // set a variable called tries
      int tries = 0;

      boolean didWin = false;

      // Check if we have gussed 3 times
      while (tries < 3) {

         // get input
         String input = cons.readLine("Guess a number between 1 - 10. ");
         int myGuess = Integer.parseInt(input);

         if (myGuess > toGuess) {
            System.out.println("Too high");

         } else if (myGuess < toGuess) {
            System.out.println("Too low");
         } else {
            System.out.println("YOU WIN!");
            System.exit(0);
            // exit the while loop
            //didWin = true;
            //break;
         }

         tries = tries + 1;
         //tries += 1;
         //tries++;

      } // while loop

      //if (!didWin) {
         System.out.println("YOU LOSE!");
      //}
   }

}
