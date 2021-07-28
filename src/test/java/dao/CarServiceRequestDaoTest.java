package dao;

import model.CarServiceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceRequestDaoTest {

    private CarServiceRequestDao carServiceRequestDao;
    private EntityDao<CarServiceRequest> entityDao;

    @BeforeEach
    void initializeDao(){
        carServiceRequestDao = new CarServiceRequestDao();
        entityDao = new EntityDao<>();
    }

    @Test
    void findByCostsBetweenShouldBeReturnResults(){
        //given
        CarServiceRequest carServiceRequest1 = new CarServiceRequest("suspension", 340, true);
        CarServiceRequest carServiceRequest2 = new CarServiceRequest("maintenance", 230, true);
        //when
        entityDao.saveOrUpdate(carServiceRequest1);
        entityDao.saveOrUpdate(carServiceRequest2);
        List<CarServiceRequest> list = carServiceRequestDao.findByCostsBetween(230, 340);
        //then
        assertNotNull(list);
        entityDao.delete(carServiceRequest1);
        entityDao.delete(carServiceRequest2);
    }

    @Test
    void twoListWithCarServiceRequestShouldNotBeEqual(){
        //given
        CarServiceRequest carServiceRequest1 = new CarServiceRequest("wheels", 650, true);
        CarServiceRequest carServiceRequest2 = new CarServiceRequest("glass", 430, false);

        List<CarServiceRequest> listBeforeInsert = Collections.singletonList(carServiceRequest1);
        //when
        entityDao.saveOrUpdate(carServiceRequest1);
        entityDao.saveOrUpdate(carServiceRequest2);
        List<CarServiceRequest> listAfterInsert = carServiceRequestDao.findByCostsBetween(450, 650);
        //then
        assertNotEquals(listBeforeInsert, listAfterInsert);
        entityDao.delete(carServiceRequest1);
        entityDao.delete(carServiceRequest2);

    }

}
