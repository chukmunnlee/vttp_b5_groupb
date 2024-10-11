package day05.gol;

public class Evaluator {

   public static int countNeighbours(int x, int y, char[][] board) {
      int star = 0;
      int height = board.length;
      int width = board[0].length;

      for (int i = 0; i < Constants.X_DELTA.length; i++) {
         // Get the position to examine
         int lX = x + Constants.X_DELTA[i];
         int lY = y + Constants.Y_DELTA[i];
         if ((lX < 0) || (lX >= width))
            continue;
         if ((lY < 0) || (lY >= height))
            continue;
         if (board[lY][lX] == Constants.STAR)
            star++;
      }

      return star;
   }
   
}
