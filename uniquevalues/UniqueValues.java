/* UniqueValues counts the number of unique values
 * in an array.
 *
 * (c) Tim Mendez 2013
 */

import java.util.*;

public class UniqueValues {
   public static void main(String[] args) {
      int[] arr = new int[]{1, 1, 1, 1, 1, 5, 5, 2, 0, 1};
      Map<Integer, Integer> hashmap = new HashMap();
      int count = 0;      

      for(int i = 0; i < arr.length; i++) {
         if(hashmap.get(arr[i]) == null)
            hashmap.put(arr[i], 1);
      }

      for(Map.Entry<Integer, Integer> entry : hashmap.entrySet())
         count++;

      System.out.println(count);
   }
}
