package cars;

public class Car {

   // Members of the class
   protected String make;
   protected String owner;
   protected String colour;
   protected int fuel = 100;

   // Constructor
   public Car() { 
      this.colour = "silver";
      this.make = "honda";
   }
   public Car(String m) {
      this.make = m;
   }
   public Car(String m, String c) {
      this.make = m;
      this.colour = c;
   }

   // Encapsulation
   public String getMake() { return make; }
   public void setMake(String make) { this.make = make; }

   public String getOwner() { return owner; }
   public void setOwner(String owner) { this.owner = owner; }

   public String getColour() { return colour; }
   public void setColour(String colour) { this.colour = colour; }

   // Methods  - behaviours
   // Overloaded method
   public void go() {
      go(1);
      // go(1, false)
      //this.fuel--;
   }
   public void go(String distance) {
      this.go(Integer.parseInt(distance), false);
   }
   public void go(int distance) {
      // go(distance, false)
      //this.fuel -= distance;
      this.go(distance, false);
   }
   public void go(int distance, boolean turbo) {
      if (turbo)
         this.fuel -= 2;
      this.fuel -= distance;
   }
   public void acclerate(int acc) {
      for (int i = 0; i < acc; i++)
         go(i + 1);
   }

   public void fuelGuage() {
      System.out.printf("FUEL: %d\n", this.fuel);
   }

   public void info() {
      System.out.printf("Make: %s, Owner: %s, Colour: %s\n"
            , this.make, this.owner, this.colour);
   }
}