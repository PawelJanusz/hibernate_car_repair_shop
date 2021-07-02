package model;

import dao.EntityDao;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void brandCanBeNull(){
        //given
        Car car = new Car(null, 1.9, 2011, 4300);
        EntityDao<Car> dao = new EntityDao<>();
        int listSizeBefore = dao.findAll(Car.class).size();
        //when
        dao.saveOrUpdate(car);
        int listSizeAfter = dao.findAll(Car.class).size();
        //then
        assertThat(listSizeAfter, is(listSizeBefore + 1));
    }

    @Test
    void twoObjectsCarShouldNotBeEqualWithoutEqualsHashcode(){
        //given
        Car car1 = new Car(Brand.BMW, 1.8, 2018, 3400);
        Car car2 = new Car(Brand.BMW, 1.8, 2018, 3400);
        //when
        //then
        assertNotSame(car1, car2);
    }

}
