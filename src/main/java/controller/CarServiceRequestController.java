package controller;

import dao.CarServiceRequestDao;
import dao.EntityDao;
import model.Car;
import model.CarServiceRequest;

import java.util.Optional;
import java.util.Scanner;


public class CarServiceRequestController {

    private EntityDao<Car> dao = new EntityDao<>();

    //create
    public void addCarServiceRequestToCar(Scanner scanner){
        EntityDao<CarServiceRequest> carServiceRequestDao = new EntityDao<>();

        System.out.println("Podaj id samochodu");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carOptional = dao.findById(Car.class, id);
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
    //read
    public void listCarServiceRequests(Scanner scanner){
        System.out.println("Podaj id samochodu");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Car> carOptional = dao.findById(Car.class, id);
        if (carOptional.isPresent()){
            Car car = carOptional.get();

            car.getCarServiceRequestSet()
                    .forEach(System.out::println);
        }
    }
    //delete
    public void deleteCarServiceRequest(Scanner scanner){
        EntityDao<CarServiceRequest> dao = new EntityDao<>();

        System.out.println("Podaj id zlecenia");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<CarServiceRequest> carServiceRequestOptional = dao.findById(CarServiceRequest.class, id);
        if (carServiceRequestOptional.isPresent()){
            CarServiceRequest carServiceRequest = carServiceRequestOptional.get();
            dao.delete(carServiceRequest);
        }
    }

    //update
    public void updateCarServiceRequest(Scanner scanner){
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
    public void findByCostsBetween(Scanner scanner){
        CarServiceRequestDao dao = new CarServiceRequestDao();

        System.out.println("Podaj zakres opłat za zlecenia: ");

        String line = scanner.nextLine();

        int costsFrom = Integer.parseInt(line.split(" ")[0]);
        int costsTo = Integer.parseInt(line.split(" ")[1]);

        System.out.println("Znalezione zlecenia w podanym przedziale kwot: ");
        dao.findByCostsBetween(costsFrom, costsTo).forEach(System.out::println);
    }
}
