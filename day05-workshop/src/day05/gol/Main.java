package day05.gol;

public class Main {

   public static void main(String[] args) throws Exception {

      if (args.length <= 0) {
         System.err.println("Missing GOL file");
         System.exit(1);
      }

      GameOfLife gol = new GameOfLife(args[0]);
      gol.readFile();
      gol.printBoard();

      for (int i = 0; i < 5; i++) {
         System.out.println("\n-------------------\n");
         gol = gol.nextGeneration();
         gol.printBoard();
      }
      
   }
}