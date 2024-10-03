package day02;

//public class Card extends Object {
public class Card {

   private final String name;
   private final String suit;
   private final int value;

   // You cannot create a blank card
   public Card(String suit, String name, int value) {
      this.suit = suit;
      this.name = name;
      this.value = value;
   }

   public String getName() { return name; }
   public String getSuit() { return suit; }
   public int getValue() { return value; }

   @Override
   public String toString() {
      return "Card{suit: %s, name: %s, value: %d}".formatted(suit, name, value);
   }

}