package controller;

import dao.EntityDao;
import dao.MechanicDao;
import model.Mechanic;

import java.util.Optional;
import java.util.Scanner;

public class MechanicController {

    private final EntityDao<Mechanic> dao = new EntityDao<>();

    public void addMechanic(Scanner scanner){
        System.out.println("Podaj dane mechanika: IMIE NAZWISKO CZY_PRACUJE");
        String line = scanner.nextLine();
        String[] words = line.split(" ");

        Mechanic mechanic = Mechanic.builder()
                .firstName(words[0])
                .lastName(words[1])
                .isHired(Boolean.parseBoolean(words[2]))
                .build();
        dao.saveOrUpdate(mechanic);
    }

    public void deleteMechanic(Scanner scanner){
        System.out.println("Podaj id mechanika: ");

        Long id = Long.parseLong(scanner.nextLine());
        Optional<Mechanic> mechanicOptional = dao.findById(Mechanic.class, id);
        if (mechanicOptional.isPresent()){
            Mechanic mechanic = mechanicOptional.get();

            dao.delete(mechanic);
        }
    }

    public void listMechanics(){
        System.out.println("Lista mechaników: ");

        dao.findAll(Mechanic.class).forEach(System.out::println);
    }

    public void updateMechanic(Scanner scanner){
        System.out.println("Podaj id mechanika: ");
        Long id = Long.parseLong(scanner.nextLine());

        Optional<Mechanic> optionalMechanic = dao.findById(Mechanic.class, id);
        if (optionalMechanic.isPresent()){
            Mechanic mechanic = optionalMechanic.get();
            System.out.println("Edytujesz rekord: " + mechanic);

            System.out.println("Podaj nowe dane mechanika: IMIE NAZWISKO CZY_PRACUJE");
            String line = scanner.nextLine();
            String[] words = line.split(" ");

            Mechanic mechanic1 = Mechanic.builder()
                    .firstName(words[0])
                    .lastName(words[1])
                    .isHired(Boolean.parseBoolean(words[2]))
                    .build();

            dao.saveOrUpdate(mechanic1);
        }else
            System.out.println("Mechanik o id " + id + " nie istnieje");
    }

    public void findByMechanicLastName(Scanner scanner){
        MechanicDao dao = new MechanicDao();

        System.out.println("Podaj nazwisko szukanego mechanika: ");

        String line = scanner.nextLine();

        String lastName = line.split(" ")[0];

        System.out.println("Znaleziono mechaników: ");
        dao.findByMechanicLastName(lastName).forEach(System.out::println);
    }

    public void findByMechanicFirstName(Scanner scanner){
        MechanicDao dao = new MechanicDao();

        System.out.println("Podaj imię szukanego mechanika: ");

        String line = scanner.nextLine();

        String firstName = line.split(" ")[0];

        System.out.println("Znaleziono mechaników: ");
        dao.findByMechanicLastName(firstName).forEach(System.out::println);
    }

    public void findByMechanicFirstAndLastName(Scanner scanner){
        MechanicDao dao = new MechanicDao();

        System.out.println("Podaj imię i nazwisko szukanego mechanika: ");

        String line = scanner.nextLine();

        String firstName = line.split(" ")[0];
        String lastName = line.split(" ")[1];

        System.out.println("Znaleziono mechaników: ");
        dao.findByMechanicFirstAndLastName(firstName, lastName).forEach(System.out::println);
    }
}
