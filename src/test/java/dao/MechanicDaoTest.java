package dao;

import model.Mechanic;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MechanicDaoTest {

    @Test
    void addedMechanicShouldBeFindByLastName(){
        //given
        EntityDao<Mechanic> dao = new EntityDao<>();
        MechanicDao mechanicDao = new MechanicDao();
        Mechanic mechanic = new Mechanic("Artur", "Ostrowski", true);
        //when
        dao.saveOrUpdate(mechanic);
        Optional<Mechanic> addedMechanic = dao.findById(Mechanic.class, 9L);
        Optional<Mechanic> foundMechanic = mechanicDao.findByMechanicLastName("Ostrowski")
                                                        .stream()
                                                        .findFirst();
        //then
        assertEquals(addedMechanic, foundMechanic);
    }



}
