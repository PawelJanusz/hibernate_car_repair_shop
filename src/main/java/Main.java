import com.sun.org.apache.xpath.internal.operations.Bool;
import controller.CarController;
import controller.CarServiceRequestController;
import dao.CarDao;
import dao.EntityDao;
import model.Brand;
import model.Car;
import model.CarServiceRequest;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarController car = new CarController();
        CarServiceRequestController carServiceRequest = new CarServiceRequestController();

        String command;
    do {
            System.out.println("Podaj nr komendy:\n" +
                    "1.Add \n" +
                    "2.Delete \n" +
                    "3.List \n" +
                    "4.Update \n" +
                    "5.Find by brand \n" +
                    "6.Find by production year between \n" +
                    "7.Add car service request \n" +
                    "8.List car service requests \n" +
                    "9.Delete car service request \n" +
                    "10.Update car service request \n" +
                    "Quit \n");

            command = scanner.nextLine();

            if (command.equals("1")){
                car.addCars(scanner);
            }
            if (command.equals("2")){
                car.deleteCar(scanner);
            }
            if (command.equals("3")){
                car.listCars();
            }
            if (command.equals("4")){
                car.updateCar(scanner);
            }
            if (command.equals("5")){
                car.findByBrand(scanner);
            }
            if (command.equals("6")){
                car.findByProductionYearBetween(scanner);
            }
            if (command.equals("7")){
                carServiceRequest.addCarServiceRequestToCar(scanner);
            }
            if (command.equals("8")){
                carServiceRequest.listCarServiceRequests(scanner);
            }
            if (command.equals("9")){
                carServiceRequest.deleteCarServiceRequest(scanner);
            }
            if (command.equals("10")){
                carServiceRequest.updateCarServiceRequest(scanner);
            }

        }while (!command.equalsIgnoreCase("quit"));
    }
}
