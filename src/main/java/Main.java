import dao.CarDao;
import model.Brand;
import model.Car;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarDao dao = new CarDao();

        String command;
        do {
            System.out.println("Podaj komendę: add/list/delete/update/quit ");

            command = scanner.nextLine();

            if (command.equalsIgnoreCase("add")){
                addCars(dao, scanner);
            }
            if (command.equalsIgnoreCase("delete")){
                deleteCar(dao, scanner);
            }

        }while (!command.equalsIgnoreCase("quit"));
    }

    private static void addCars(CarDao dao, Scanner scanner){
        System.out.println("Podaj dane samochodu: NUMER_REJESTRACYJNY MARKA POJEMNOSC_SILNIKA");
        String line = scanner.nextLine();
        String[] words = line.split(" ");

        Car car = Car.builder()
                .registrationNumber(words[0])
                .brand(Brand.valueOf(words[1]))
                .engineCapacity(Double.parseDouble(words[2]))
                .build();

        dao.saveOrUpdate(car);
    }

    private static void deleteCar(CarDao dao, Scanner scanner){
        System.out.println("Podaj id samochodu: ");

        Long id = Long.parseLong(scanner.nextLine());
        Optional<Car> carOptional = dao.findById(id);
        if (carOptional.isPresent()){
            Car car = carOptional.get();

            dao.delete(car);
        }
    }
}
