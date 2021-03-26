package controller;

import dao.EntityDao;
import model.Mechanic;

import java.util.Optional;
import java.util.Scanner;

public class MechanicController {

    public void addMechanic(Scanner scanner){
        EntityDao<Mechanic> dao = new EntityDao<>();

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
        EntityDao<Mechanic> dao = new EntityDao<>();

        System.out.println("Podaj id mechanika: ");

        Long id = Long.parseLong(scanner.nextLine());
        Optional<Mechanic> mechanicOptional = dao.findById(Mechanic.class, id);
        if (mechanicOptional.isPresent()){
            Mechanic mechanic = mechanicOptional.get();

            dao.delete(mechanic);
        }
    }

    public void listMechanics(){
        EntityDao<Mechanic> dao = new EntityDao<>();

        System.out.println("Lista mechaników: ");

        dao.findAll(Mechanic.class).forEach(System.out::println);
    }

    public void updateMechanic(Scanner scanner){
        EntityDao<Mechanic> dao = new EntityDao<>();

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
}
