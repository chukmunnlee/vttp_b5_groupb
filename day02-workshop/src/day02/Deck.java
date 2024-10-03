package day02;

public class Deck {

   public static final String[] SUIT = { 
      "Diamond", "Spade", "Hearts", "Clubs" 
   };
   public static final String[] NAMES = {
      "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
      "Nine", "Ten", "Jack", "Queen", "King"
   };
   public static final int[] VALUES = {
      1, 2, 3, 4, 5, 6, 7, 8,
      9, 10, 10, 10, 10
   };

   protected Card[] cards;

   public Deck() {
      int idx = 0;
      cards = new Card[52];
      for (int s = 0; s < SUIT.length; s++) {
         String suit = SUIT[s];
         for (int n = 0; n < NAMES.length; n++) {
            String name = NAMES[n];
            int value = VALUES[n];

            Card card = new Card(suit, name, value);
            cards[idx] = card;
            idx++;
         }
      }
   }

   public Card get() {
      return this.get(0);
   }
   public Card get(int pos) {
      return this.cards[pos];
   }

   @Override
   public String toString() {
      return "Number of cards: %d".formatted(cards.length);
   }
   
}
