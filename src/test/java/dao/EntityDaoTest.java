package dao;

import model.Brand;
import model.Car;
import model.CarServiceRequest;
import model.Mechanic;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
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

    @Test
    void mechanicShouldBeDeleteFromTheList(){
        //given
        EntityDao<Mechanic> dao = new EntityDao<>();
        Mechanic mechanic = new Mechanic("Tomasz", "Opolski", true);
        int sizeListBefore = dao.findAll(Mechanic.class).size();
        //when
        dao.saveOrUpdate(mechanic);
        dao.delete(mechanic);
        int sizeListAfter = dao.findAll(Mechanic.class).size();
        //then
        assertThat(sizeListAfter, is(sizeListBefore));
    }

    @Test
    void listCarsShouldNotBeEqualAfterAddNewCar(){
        //given
        EntityDao<Car> dao = new EntityDao<>();
        Car car = new Car(Brand.MERCEDES, 1.7, 2015, 2000);
        //when
        List<Car> cars1 = dao.findAll(Car.class);
        dao.saveOrUpdate(car);
        List<Car> cars2 = dao.findAll(Car.class);
        //then
        assertNotEquals(cars1, cars2);
    }

    @Test
    void carServiceRequestShouldBeDeleteFromTheList(){
        //given
        EntityDao<CarServiceRequest> dao = new EntityDao<>();
        CarServiceRequest carServiceRequest = new CarServiceRequest("wheel", 430, true);
        int listSizeBefore = dao.findAll(CarServiceRequest.class).size();
        //when
        dao.saveOrUpdate(carServiceRequest);
        dao.delete(carServiceRequest);
        int listSizeAfter = dao.findAll(CarServiceRequest.class).size();
        //then
        assertThat(listSizeAfter, is(listSizeBefore));
    }

    @Test
    void returnOptionalEmpty(){
        //given
        EntityDao<Car> dao = new EntityDao<>();
        //when
        Optional<Car> optionalCar = dao.findById(Car.class, 400L);
        //then
        assertSame(Optional.empty(), optionalCar);
    }

    @Test
    void returnNotEmptyOptional(){
        //given
        EntityDao<Mechanic> dao = new EntityDao<>();
        //when
        Optional<Mechanic> optionalMechanic = dao.findById(Mechanic.class, 2L);
        //then
        assertThat(optionalMechanic, not(Optional.empty()));
    }


}
