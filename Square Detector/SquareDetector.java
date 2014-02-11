
/**
 * Facebook Hacker Cup 2014 Qualification Round
 * @author Tim Mendez
 */
import java.util.*;

public class SquareDetector
{
   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);
      int numTestCases = scan.nextInt();
      int sidelength;

      for(int i = 0; i < numTestCases; i++) {
         boolean pass = true;
         sidelength = scan.nextInt();
         char[][] grid = new char[sidelength][sidelength];

         for(int j = 0; j < sidelength; j++) { //populating the grid
            char[] tmpcharr = scan.next().toCharArray();

            if(!lengthCheck(sidelength, tmpcharr.length))
               pass = false;

            for(int k = 0; k < sidelength; k++)
               grid[k][j] = tmpcharr[k];

         }

         if(pass && !isSquareCheck(grid))
            pass = false;

         if(pass)
            System.out.println("Case #" + (i+1) + ": YES");
         else
            System.out.println("Case #" + (i+1) + ": NO");
      }
   }

   /*
    * Determines whether the input is valid
    * by checking if the #'s form a fully filled
    * square.
    **/
   private static boolean isSquareCheck(char[][] grid) {
      int side = 0, count = 0;

      for(int i = 0; i < grid.length && side == 0; i++) {
         for(int j = 0; j < grid.length; j++) {
            if(grid[i][j] == '#') {
               side++;
            }
         }
      }
      
      if(side*side < 4)
         return false;

      for(int i = 0; i < grid.length; i++) {
         for(int j = 0; j < grid.length; j++) {
            if(grid[j][i] == '#') {
               count++;
            }
         }
      }

      return count == side*side;
   }

   /*
    * Determines the length of the side of the square
    **/
   private static boolean lengthCheck(int sidelength, int linelength) {
      return sidelength == linelength;
   }
}

