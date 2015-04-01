package reflection.CarShop;
import LoggerLib.Logger;
import java.lang.reflect.*;

class Car {
 int wheels;
 int tires;
 int speed;
 //simple constructor
 public Car (int inWheels, int inTires, int inSpeed){
  wheels=inWheels;
  tires = inTires;  
  speed = inSpeed;
 }
}

class Tires {
     int number;
     float diameter;
     public String foo(String a)
    {
	return a+"dtire";
    }
}

public class CarShop {
     Car carList[];

     public CarShop (){          
          carList = new Car[2];          
          createCar("Saturn",0);
          createCar("BMW",1);
           
     }

     public void createCar(String carName,int carNum){
          try{          
               //create the Tires array, which you'll use as  
               //the parameter to the constructor
               Object constructorParam[] = new Tires[1];
               constructorParam[0]= new Tires();

               //get the class name for the car that you want               
               Class cl = Class.forName(carName);
			Logger.reportString(carName,"reflection.CarShop.CarShop37");

               //create an array of Classes, and use this  
               //array to find the constructor that you want
               Class parameters[] = new Class[1];
               parameters[0]= Class.forName("Tires");
			Logger.reportString("Tires","reflection.CarShop.CarShop42");
               Constructor  con = cl.getDeclaredConstructor(parameters);                              
		Tires t=new Tires();
		String dk=t.foo("bingo");
		Logger.reportString(dk,"reflection.CarShop.CarShop55");
               //create a car instance for the carList
               carList[carNum] = (Car)con.newInstance(constructorParam);          
          } catch (Exception e){
               System.out.println("Error creating "+carName +":"+e);
          }
     }

     public static void main(String args[]){
          new CarShop();
     }
}

