public class TestCases {
   public static void main(String[] args) {
      UniqueValues UV = new UniqueValues();
      int[] arr1 = new int[]{0,5,1,-3,72,6,6,2,1,1,1,1};
      int[] arr2 = new int[]{};
      int[] arr3 = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5};
      int[] arr4 = new int[]{1,2,1,2,1,2,1,2,1,2,0,1,2,0,1,2,-1,2,0,-2,-1,-2,-1,0,1,2};
      int val;

      val = UV.uniqueVals(arr1);
      if(val != 7)
         System.out.println("Test 1 failed.\nShould be 7, got " + val);

      val = UV.uniqueVals(arr2);
      if(val != 0)
          System.out.println("Test 2 failed.\nShould be 0, got " + val);

      val = UV.uniqueVals(arr3);
      if(val != 1)
          System.out.println("Test 3 failed.\nShould be 1, got " + val);

      val = UV.uniqueVals(arr4);
      if(val != 5)
          System.out.println("Test 4 failed.\nShould be 5, got " + val);

      System.out.println("Tests completed.");
   }
}