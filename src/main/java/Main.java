import com.sun.org.apache.xpath.internal.operations.Bool;
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
                addCars(scanner);
            }
            if (command.equals("2")){
                deleteCar(scanner);
            }
            if (command.equals("3")){
                listCars();
            }
            if (command.equals("4")){
                updateCar(scanner);
            }
            if (command.equals("5")){
                findByBrand(scanner);
            }
            if (command.equals("6")){
                findByProductionYearBetween(scanner);
            }
            if (command.equals("7")){
                addCarServiceRequestToCar(scanner);
            }
            if (command.equals("8")){
                listCarServiceRequests(scanner);
            }
            if (command.equals("9")){
                deleteCarServiceRequest(scanner);
            }
            if (command.equals("10")){
                updateCarServiceRequest(scanner);
            }

        }while (!command.equalsIgnoreCase("quit"));
    }

    private static void addCars(Scanner scanner){
        EntityDao<Car> dao = new EntityDao<>();

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

    private static void deleteCar(Scanner scanner){
        EntityDao<Car> dao = new EntityDao<>();

        System.out.println("Podaj id samochodu: ");

        Long id = Long.parseLong(scanner.nextLine());
        Optional<Car> carOptional = dao.findById(Car.class, id);
        if (carOptional.isPresent()){
            Car car = carOptional.get();

            dao.delete(car);
        }
    }

    private static void listCars(){
        EntityDao<Car> dao = new EntityDao<>();

        System.out.println("Lista samochodów: ");

        dao.findAll(Car.class).forEach(System.out::println);
    }

    private static void updateCar(Scanner scanner){
        EntityDao<Car> dao = new EntityDao<>();

        System.out.println("Podaj id samochodu: ");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carOptional = dao.findById(Car.class, id);
        if (carOptional.isPresent()){
            Car car = carOptional.get();
            System.out.println("Edytujesz rekord: " + car);

            System.out.println("Podaj nowe parametry wybranego samochodu: " +
                    "NUMER_REJESTRACYJNY MARKA POJEMNOŚĆ ROK_PRODUKCJI PRZEBIEG");
            String line = scanner.nextLine();
            String[] words = line.split(" ");

            Car car1 = Car.builder()
                    .registrationNumber(words[0])
                    .brand(Brand.valueOf(words[1].toUpperCase()))
                    .engineCapacity(Double.parseDouble(words[2]))
                    .productionYear(Integer.parseInt(words[3]))
                    .course(Integer.parseInt(words[4]))
                    .build();

            dao.saveOrUpdate(car1);
        }else
            System.out.println("ERROR, samochód o id " + id + " nie istnieje");
    }

    private static void findByBrand(Scanner scanner){
        CarDao dao = new CarDao();

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

    private static void findByProductionYearBetween(Scanner scanner){
        CarDao dao = new CarDao();

        System.out.println("Podaj zakres lat produkcji samochodu: productionYearFrom, productionYearTo");

        String line = scanner.nextLine();

        int productionYearFrom = Integer.parseInt(line.split(" ")[0]);
        int productionYearTo = Integer.parseInt(line.split(" ")[1]);

        System.out.println("Znalezione samochody w podanym przedziale lat produkcji: ");
        dao.findByProductionYearBetween(productionYearFrom, productionYearTo).forEach(System.out::println);
    }

    private static void addCarServiceRequestToCar(Scanner scanner){
        EntityDao<Car> carDao = new EntityDao<>();
        EntityDao<CarServiceRequest> carServiceRequestDao = new EntityDao<>();

        System.out.println("Podaj id samochodu");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carOptional = carDao.findById(Car.class, id);
        if (carOptional.isPresent()){
            System.out.println("Podaj dane zlecenia: OPIS KWOTA_NAPRAWY CZY_ZAKOŃCZONE");
            String line = scanner.nextLine();

            String description = line.split(" ")[0];
            int costs = Integer.parseInt(line.split(" ")[1]);
            boolean repaired = Boolean.parseBoolean(line.split(" ")[2]);

            CarServiceRequest carServiceRequest = new CarServiceRequest(description, costs, repaired);
            carServiceRequestDao.saveOrUpdate(carServiceRequest);

            Car car = carOptional.get();
            carServiceRequest.setCarRef(car);

            carServiceRequestDao.saveOrUpdate(carServiceRequest);
        }
    }

    private static void listCarServiceRequests(Scanner scanner){
        EntityDao<Car> dao = new EntityDao<>();

        System.out.println("Podaj id samochodu");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carOptional = dao.findById(Car.class, id);
        if (carOptional.isPresent()){
            Car car = carOptional.get();

            car.getCarServiceRequestSet()
                    .forEach(System.out::println);
        }
    }

    private static void deleteCarServiceRequest(Scanner scanner){
        EntityDao<CarServiceRequest> dao = new EntityDao<>();

        System.out.println("Podaj id zlecenia");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<CarServiceRequest> carServiceRequestOptional = dao.findById(CarServiceRequest.class, id);
        if (carServiceRequestOptional.isPresent()){
            CarServiceRequest carServiceRequest = carServiceRequestOptional.get();
            dao.delete(carServiceRequest);
        }
    }

    private static void updateCarServiceRequest(Scanner scanner){
        EntityDao<CarServiceRequest> dao = new EntityDao<>();

        System.out.println("Podaj id zlecenia");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<CarServiceRequest> carServiceRequestOptional = dao.findById(CarServiceRequest.class, id);
        if (carServiceRequestOptional.isPresent()){
            CarServiceRequest carServiceRequest = carServiceRequestOptional.get();
            System.out.println("Próbujesz edytować zlecenie: " + carServiceRequest);

            System.out.println("Podaj nowe dane zlecenia: OPIS KWOTA_NAPRAWY CZY_ZAKOŃCZONE");
            String line = scanner.nextLine();
            String[] words = line.split(" ");

            carServiceRequest = CarServiceRequest.builder()
                    .id(id)
                    .description(words[0])
                    .costs(Integer.parseInt(words[1]))
                    .repairedDone(Boolean.parseBoolean(words[2]))
                    .build();
            dao.saveOrUpdate(carServiceRequest);
        }else {
            System.err.println("Zlecenie o takim id nie istnieje");
        }
    }


}
