package vttp.batch5.ssf.day12.models;

import java.util.LinkedList;
import java.util.List;

public class Constants {

   public static final String[] ITEMS = {
      "apple", "orange", "pear", "grapes", "plum"
   };

   public static final int[] QUANTITY = {
      10, 5, 8, 3, 7
   };

   public static List<LineItem> generateLineItems(int count) {

      List<LineItem> items = new LinkedList<>();

      if (count > ITEMS.length)
         count = ITEMS.length;

      for (int i = 0; i < count; i++) {
         LineItem li = new LineItem();
         li.setName(ITEMS[i]);
         li.setQuantity(QUANTITY[i]);
         items.add(li);
      }

      return items;
   }

   public static List<LineItem> generateLineItems() {
      return generateLineItems(ITEMS.length);
   }
   
}
