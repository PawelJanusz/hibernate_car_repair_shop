import dao.CarDao;
import model.Brand;
import model.Car;

import java.text.DateFormat;
import java.util.Date;
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
            if (command.equalsIgnoreCase("list")){
                listCars(dao);
            }
            if (command.equalsIgnoreCase("update")){
                updateCar(dao, scanner);
            }

        }while (!command.equalsIgnoreCase("quit"));
    }

    private static void addCars(CarDao dao, Scanner scanner){
        System.out.println("Podaj dane samochodu: NUMER_REJESTRACYJNY MARKA POJEMNOSC_SILNIKA ROK_PRODUKCJI PRZEBIEG");
        String line = scanner.nextLine();
        String[] words = line.split(" ");

        Car car = Car.builder()
                .registrationNumber(words[0])
                .brand(Brand.valueOf(words[1]))
                .engineCapacity(Double.parseDouble(words[2]))
                .productionYear(Integer.parseInt(words[3]))
                .course(Integer.parseInt(words[4]))
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

    private static void listCars(CarDao dao){
        System.out.println("Lista samochodów: ");

        dao.getAll().stream().forEach(System.out::println);
    }

    private static void updateCar(CarDao dao, Scanner scanner){
        System.out.println("Podaj id samochodu: ");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carOptional = dao.findById(id);
        if (carOptional.isPresent()){
            Car car = carOptional.get();
            System.out.println("Edytujesz rekord: " + car);

            System.out.println("Podaj nowe parametry wybranego samochodu: " +
                    "NUMER_REJESTRACYJNY MARKA POJEMNOŚĆ ROK_PRODUKCJI PRZEBIEG");
            String line = scanner.nextLine();
            String[] words = line.split(" ");

            Car car1 = Car.builder()
                    .registrationNumber(words[0])
                    .brand(Brand.valueOf(words[1]))
                    .engineCapacity(Double.parseDouble(words[2]))
                    .productionYear(Integer.parseInt(words[3]))
                    .course(Integer.parseInt(words[4]))
                    .build();

            dao.saveOrUpdate(car1);
        }else
            System.out.println("ERROR, samochód o id " + id + " nie istnieje");
    }
}
