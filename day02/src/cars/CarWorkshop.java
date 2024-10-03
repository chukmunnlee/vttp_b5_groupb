package cars;

public class CarWorkshop {

   public void service(Toyota toyota) {
      System.out.printf("Servicing toyota: %s\n", toyota.getOwner());
   }

   public void service(Car car) {
      if (car instanceof Fiat) {
         System.out.printf("Servicing fiat: %s\n", car.getOwner());
      } else {
         System.out.printf("Servicing generic car: %s\n", car.getOwner());
      }
   }
   
}
