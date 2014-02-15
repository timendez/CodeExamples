/**
 * FizzBuzz
 * Prints out the numbers from 1 to 100.
 * But for multiples of 3, "Fizz" gets printed instead,
 * for multiples of 5, "Buzz" gets printed instead,
 * and for multiples of both 3 and 5, "FizzBuzz" gets printed.
 *
 * @author Tim Mendez 
 * @version 2/14/14
 */
public class FizzBuzz
{
   public static void main(String[] args) {      
      for(int count = 1; count <= 100; count++) {
         if(count % 15 == 0)
            System.out.println("FizzBuzz");
         else if(count % 5 == 0)
            System.out.println("Buzz");
         else if(count % 3 == 0)
            System.out.println("Fizz");
         else
            System.out.println(count);
      }
   }
}
