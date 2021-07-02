package model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @ParameterizedTest
    @EnumSource(Brand.class)
    void brandWordCarShouldBeLongerThan2Characters(Brand brand){
        assertThat(brand.toString().length(), greaterThan(2));
        assertThat(brand.toString().length(), greaterThanOrEqualTo(2));
    }

    @ParameterizedTest
    @EnumSource(Brand.class)
    void brandWordShouldBeEndWithLetter(Brand brand){
        String[] brandList = {"OPEL", "AUDI", "FIAT", "MAZDA", "NISSAN",
                "DODGE",
                "MERCEDES",
                "BMW"};
        assertAll(
                () -> brand.toString().endsWith("a"),
                () -> brand.toString().startsWith("m"),
                () -> brand.toString().equals(brandList)
        );
    }

}
