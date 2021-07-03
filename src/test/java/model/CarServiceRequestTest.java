package model;

import dao.EntityDao;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class CarServiceRequestTest {

    @Test
    void descriptionCanBeNull(){
        //given
        CarServiceRequest carService = new CarServiceRequest(null, 340, true);
        EntityDao<CarServiceRequest> dao = new EntityDao<>();
        int listSizeBefore = dao.findAll(CarServiceRequest.class).size();
        //when
        dao.saveOrUpdate(carService);
        int listSizeAfter = dao.findAll(CarServiceRequest.class).size();
        //then
        assertThat(listSizeAfter, is(listSizeBefore + 1));
    }

    @Test
    void twoObjectsShouldNotBeEqual(){
        //given
        CarServiceRequest request1 = new CarServiceRequest("suspension", 5000, false);
        CarServiceRequest request2 = new CarServiceRequest("steering-wheel", 670, false);
        //when
        //then
        assertNotEquals(request1, request2);
    }

}
