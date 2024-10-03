package calc;

import java.io.Console;

public class CalculatorMark2 {

   // Constant
   public static final int CMD_POS = 0;

   public static void main(String[] args) {

      // declare an accumulator
      float acc = 0;
      float value = 0;

      boolean stop = false;

      Console cons = System.console();

      while (!stop) {

         // Read a line and trim it
         // ADD 1 2 3, BRK
         String input = cons.readLine("CMD> ").trim();

         // split up into terms
         String[] terms = input.split(" ");

         // ADD, BRK, ...
         switch (terms[CMD_POS].toUpperCase()) {
            // ADD 5 6 7 9
            case "ADD":
               for (int idx = 1; idx < terms.length; idx++) {
                  acc += Float.parseFloat(terms[idx]);
               }
               break;

            // SUB 5 6 7 9
            case "SUB":
               break;

            // MUL 5 6 7 9
            case "MUL":
               break;

            // DIV 4
            case "DIV":
               break;

            // initializes the accumulator to 0
            case "INIT":
               acc = 0;
               break;

            case "BRK":
               stop = true;
               break;

            case "SHOW":
               System.out.printf("> %.3f\n", acc);
               break;

            default:

         }

      }

   }

}