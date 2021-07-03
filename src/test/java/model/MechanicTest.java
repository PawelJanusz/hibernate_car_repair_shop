package model;

import dao.EntityDao;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class MechanicTest {

    @Test
    void firstAndLastNameCanBeNull(){
        //given
        Mechanic mechanic = new Mechanic(null, null, false);
        EntityDao<Mechanic> dao = new EntityDao<>();
        int listSizeBefore = dao.findAll(Mechanic.class).size();
        //when
        dao.saveOrUpdate(mechanic);
        int listSizeAfter = dao.findAll(Mechanic.class).size();
        //then
        assertThat(listSizeAfter, is(listSizeBefore + 1));
    }

    @Test
    void objectsShouldBeEqual(){
        //given
        Mechanic mechanic1 = new Mechanic(10L,"Adrian", "Kolis", true);
        Mechanic mechanic2 = new Mechanic(10L,"Adrian", "Kolis", true);
        //when
        //then
        assertEquals(mechanic1, mechanic2);
    }

}
