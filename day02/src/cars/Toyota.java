package cars;

public class Toyota extends Car {

   private boolean gps = false;

   public Toyota() {
      setMake("Toyota");
   }

   // Overriding
   // Method signature is the same as the parents
   @Override //annotation 
   public void go(int d, boolean t) {
      this.fuel -= d;
   }

   @Override
   public void setMake(String m) { }

   public boolean isGps() { return gps; }
   public void setGps(boolean gps) { this.gps = gps; }
   
}
