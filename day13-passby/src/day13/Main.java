package day13;

public class Main {

   public static void main(String[] args) {
      int x = 3;
      int[] nums = { 0, 1, 2, 3 };

      System.out.printf("before: %d\n", x);
      addOne(x);
      System.out.printf("after: %d\n", x);

      System.out.println("\n\n");

      System.out.println("BEFORE");
      dumpArray(nums);
      addOne(nums);
      System.out.println("AFTER");
      dumpArray(nums);

      String name = "fred";
      System.out.printf("BEFORE: %s\n", name);
      toUppperCase(name);
      System.out.printf("AFTER: %s\n", name);

   }

   public static void toUppperCase(String x) {
      System.out.printf(">>>> in toUpperCasse BEFORE: %s\n", x);
      x = x.toUpperCase();
      System.out.printf(">>>> in toUpperCasse AFTER: %s\n", x);
   }

   public static void dumpArray(int[] vals) {
      for (int i = 0; i < vals.length; i++)
         System.out.printf("%d ", vals[i]);
      System.out.println();
   }

   public static void addOne(int[] vals) {
      for (int i = 0; i < vals.length; i++)
         vals[i] += 1;
   }

   public static void addOne(int x) {
      System.out.printf("x in addOne: before: %d\n", x);
      x += 1;
      System.out.printf("x in addOne: after: %d\n", x);
   }
}