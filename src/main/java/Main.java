
import controller.CarController;
import controller.CarServiceRequestController;
import controller.MechanicController;

import java.util.Scanner;

public class Main {

    //TODO 1- relation mechanic and car
    //TODO 2 - new fields and @Formula in class Car
    //TODO 3 - TESTS

    public static void main(String[] args) {
        mainMenu();

    }
    private static final Scanner scanner = new Scanner(System.in);
    private static String command;

    private static final CarController car = new CarController();
    private static final CarServiceRequestController carServiceRequest = new CarServiceRequestController();
    private static final MechanicController mechanic = new MechanicController();

    private static void mainMenu(){
        do {
            System.out.println("Choose option number: \n" +
                    "1.Car \n" +
                    "2.Car service request \n" +
                    "3.Mechanic \n" +
                    "Quit");
            command = scanner.nextLine();
            if (command.equals("1")){
                carMenu();
            }
            if (command.equals("2")){
                carServiceRequestMenu();
            }
            if (command.equals("3")){
                mechanicMenu();
            }

        }while (!command.equalsIgnoreCase("quit"));
    }

    private static void carMenu(){
        do {
            System.out.println("Choose operation number for Car:\n" +
                    "1.Add \n" +
                    "2.Delete \n" +
                    "3.List \n" +
                    "4.Update \n" +
                    "5.Find by brand \n" +
                    "6.Find by production year between \n" +
                    "Back \n" +
                    "Quit");

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
            if (command.equalsIgnoreCase("back")){
                mainMenu();
            }

        }while (!command.equalsIgnoreCase("quit"));
    }

    private static void carServiceRequestMenu(){
        do {
            System.out.println("Choose operation number for Car Service Request:\n" +
                    "1.Add car service request \n" +
                    "2.List car service requests \n" +
                    "3.Delete car service request \n" +
                    "4.Update car service request \n" +
                    "5.Find by costs between \n" +
                    "Back \n" +
                    "Quit \n");

            command = scanner.nextLine();
            if (command.equals("1")){
                carServiceRequest.addCarServiceRequestToCar(scanner);
            }
            if (command.equals("2")){
                carServiceRequest.listCarServiceRequests(scanner);
            }
            if (command.equals("3")){
                carServiceRequest.deleteCarServiceRequest(scanner);
            }
            if (command.equals("4")){
                carServiceRequest.updateCarServiceRequest(scanner);
            }
            if (command.equals("5")){
                carServiceRequest.findByCostsBetween(scanner);
            }
            if (command.equalsIgnoreCase("back")){
                mainMenu();
            }

        }while (!command.equalsIgnoreCase("quit"));
    }

    private static void mechanicMenu(){
        do {
            System.out.println("Choose operation number for mechanic: \n" +
                    "1.Add \n" +
                    "2.List \n" +
                    "3.Delete \n" +
                    "4.Update \n" +
                    "5.Find by mechanic last name \n" +
                    "Back \n" +
                    "Quit");

            command = scanner.nextLine();
            if (command.equals("1")){
                mechanic.addMechanic(scanner);
            }
            if (command.equals("2")){
                mechanic.listMechanics();
            }
            if (command.equals("3")){
                mechanic.deleteMechanic(scanner);
            }
            if (command.equals("4")){
                mechanic.updateMechanic(scanner);
            }
            if (command.equals("5")){
                mechanic.findByMechanicLastName(scanner);
            }
            if (command.equalsIgnoreCase("back")){
                mainMenu();
            }
        }while (!command.equalsIgnoreCase("quit"));
    }
}
