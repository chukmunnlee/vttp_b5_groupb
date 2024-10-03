package cars;

import cars.Car;

public class Main {

   public static void main(String[] args) {

      // Instantiation 
      // Creating an instance of the class
      // myCar is an instance of Car
      Car myCar = new Car("toyota");
      myCar.setOwner("Fred");
      myCar.info();

      Car yourCar = new Car("nissan", "red");
      yourCar.setOwner("Barney");
      yourCar.info();

      Car ourCar = new Car();
      ourCar.setMake("BMW");
      ourCar.setOwner("Wilma");
      ourCar.info();

      Car batCar = new Car();
      batCar.setOwner("bruce wayne");
      batCar.info();

      batCar.fuelGuage();
      batCar.go();
      batCar.fuelGuage();
      batCar.go(3, true);
      batCar.fuelGuage();

      // car trailer for 6 cars
      Car[] trailer = new Car[6];
      trailer[0] = myCar;
   }
   
}
