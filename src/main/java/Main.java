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
            System.out.println("Podaj nr komendy: 1.Add " +
                    "2.Delete " +
                    "3.List " +
                    "4.Update " +
                    "5.FindByBrand" +
                    "6.FindByProductionYearBetween" +
                    "Quit ");

            command = scanner.nextLine();

            if (command.equals("1")){
                addCars(dao, scanner);
            }
            if (command.equals("2")){
                deleteCar(dao, scanner);
            }
            if (command.equals("3")){
                listCars(dao);
            }
            if (command.equals("4")){
                updateCar(dao, scanner);
            }
            if (command.equals("5")){
                findByBrand(dao, scanner);
            }
            if (command.equals("6")){
                findByProductionYearBetween(dao, scanner);
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

    private static void findByBrand(CarDao dao, Scanner scanner){
        System.out.println("Podaj nazwę marki: OPEL,\n" +
                "    AUDI,\n" +
                "    FIAT,\n" +
                "    MAZDA,\n" +
                "    NISSAN,\n" +
                "    DODGE,\n" +
                "    MERCEDES,\n" +
                "    BMW");

        String line = scanner.nextLine();

        Brand brand = Brand.valueOf(line.toUpperCase());
        System.out.println("Zanlezione samochody: ");
        dao.findByBrandName(brand).forEach(System.out::println);
    }

    private static void findByProductionYearBetween(CarDao dao, Scanner scanner){
        System.out.println("Podaj zakres lat produkcji samochodu: productionYearFrom, productionYearTo");

        String line = scanner.nextLine();

        int productionYearFrom = Integer.parseInt(line.split(" ")[0]);
        int productionYearTo = Integer.parseInt(line.split(" ")[1]);

        System.out.println("Znalezione samochody w podanym przedziale lat produkcji: ");
        dao.findByProductionYearBetween(productionYearFrom, productionYearTo).forEach(System.out::println);
    }



}
