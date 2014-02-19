/* UniqueValues counts the number of unique values
 * in an array.
 *
 * @author Tim Mendez
 * @version 2/18/2014
 */
import java.util.*;

public class UniqueValues {
   public int uniqueVals(int[] arr) {
      HashSet<Integer> hashset = new HashSet();

      for(int i = 0; i < arr.length; i++)
            hashset.add(arr[i]);

      return hashset.size();
   }
}