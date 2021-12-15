package dao;

import model.Brand;
import model.Car;
import model.CarServiceRequest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;

class EntityDaoTest {

    @Test
    void carShouldBeAddedToTheList(){
        //given
        EntityDao<Car> dao = new EntityDao<>();
        Car car = new Car(Brand.FIAT, 1.6, 2014, 5400);
        int listSizeBefore = dao.findAll(Car.class).size();
        //when
        dao.saveOrUpdate(car);
        int listSizeAfter = dao.findAll(Car.class).size();
        //then
        assertThat(listSizeAfter, equalTo(listSizeBefore + 1));
    }

    @Test
    void threeCarServiceRequestShouldBeAddedToTheList(){
        //given
        EntityDao<CarServiceRequest> dao = new EntityDao<>();
        CarServiceRequest carServiceRequest1 = new CarServiceRequest("change front glass door", 450, true);
        CarServiceRequest carServiceRequest2 = new CarServiceRequest("repair door", 950, true);
        CarServiceRequest carServiceRequest3 = new CarServiceRequest("repair roof", 750, false);
        int listSizeBefore = dao.findAll(CarServiceRequest.class).size();
        //when
        dao.saveOrUpdate(carServiceRequest1);
        dao.saveOrUpdate(carServiceRequest2);
        dao.saveOrUpdate(carServiceRequest3);
        int listSizeAfter = dao.findAll(CarServiceRequest.class).size();
        //then
        assertThat(listSizeAfter, is(listSizeBefore + 3));

    }


}
