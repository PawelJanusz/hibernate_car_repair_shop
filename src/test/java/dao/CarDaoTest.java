package dao;

import model.Brand;
import model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarDaoTest {

    private CarDao carDao;
    private EntityDao<Car> entityDao;

    @BeforeEach
    void createObjects(){
        carDao = new CarDao();
        entityDao = new EntityDao<>();
    }

    @Test
    void findByBrandNameReturnListWithCars(){
        //given
        Car car = new Car(Brand.AUDI, 1.5, 2010, 3400);
        //when
        entityDao.saveOrUpdate(car);
        List<Car> list = carDao.findByBrandName(Brand.AUDI);
        //then
        assertNotNull(list);
    }

    @Test
    void findByProductionYearShouldNotReturnEmptyList(){
        //given
        Car car1 = new Car(Brand.BMW, 1.8, 2019, 1300);
        Car car2 = new Car(Brand.FIAT, 1.4, 2018, 4500);
        //when
        entityDao.saveOrUpdate(car1);
        entityDao.saveOrUpdate(car2);
        List<Car> list = carDao.findByProductionYearBetween(2018, 2019);
        //then
        assertNotNull(list);
        entityDao.delete(car1);
        entityDao.delete(car2);
    }

    @Test
    void savedProductionYearShouldBeEqual(){
        //given
        Car car = new Car(Brand.MERCEDES, 1.8, 2020, 800);
        //when
        entityDao.saveOrUpdate(car);
        int savedProductionYear = car.getProductionYear();
        //then
        assertEquals(2020, savedProductionYear);
        entityDao.delete(car);
    }
}
