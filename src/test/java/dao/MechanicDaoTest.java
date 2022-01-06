package dao;

import model.Mechanic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MechanicDaoTest {

    private EntityDao<Mechanic> dao;
    private MechanicDao mechanicDao;

    @BeforeEach
    void runBeforeEach() {
        dao = new EntityDao<>();
        mechanicDao = new MechanicDao();

    }

    @Test
    void addedMechanicShouldBeFindByLastName(){
        //given
        Mechanic mechanic = new Mechanic("Artur", "Ostrowski", true);
        //when
        dao.saveOrUpdate(mechanic);
        Optional<Mechanic> addedMechanic = dao.findById(Mechanic.class, 9L);
        Optional<Mechanic> foundMechanic = mechanicDao
                .findByMechanicLastName("Ostrowski")
                .stream()
                .findFirst();
        //then
        assertEquals(foundMechanic, addedMechanic);
    }

    @Test
    void addedMechanicShouldBeFindByFirstName(){
        //given
        Mechanic mechanic = new Mechanic("Tomek", "Morawski", true);
        //when
        dao.saveOrUpdate(mechanic);
        Optional<Mechanic> addedMechanic = dao.findById(Mechanic.class, 14L);
        Optional<Mechanic> foundMechanic = mechanicDao
                .findByMechanicFirstName("Tomek")
                .stream()
                .findFirst();
        //then
        assertEquals(foundMechanic, addedMechanic);
    }

    @Test
    void addedMechanicShouldBeFindByFirstAndLastName(){
        //given
        Mechanic mechanic = new Mechanic("Anna", "Kedra", true);
        //when
        dao.saveOrUpdate(mechanic);
        Optional<Mechanic> addedMechanic = dao.findById(Mechanic.class, 22L);
        Optional<Mechanic> foundMechanic = mechanicDao
                .findByMechanicFirstAndLastName("Anna", "Kedra")
                .stream()
                .findFirst();
        //then
        assertEquals(foundMechanic, addedMechanic);
    }


}
