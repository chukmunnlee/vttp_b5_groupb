package day03;

public class Link {

   // Accept any object that implements the Damagable interface
   public void hit(Damagable d) {
      d.damage(5);
   }

   // public void hit(Monster m) {
   //    m.damage(5);
   // }
   // public void hit(Box b) {
   //    b.setIntegrity(b.getIntegrity() - 5);
   // }

}
