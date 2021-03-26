package model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Z]{3}[0-9]{4}")
    private String registrationNumber;

    @Enumerated(value = EnumType.STRING)
    private Brand brand;

    private double engineCapacity;

    @Pattern(regexp = "[0-9]{4}")
    private int productionYear;

    private int course;

    //mappedBy permit to show CarServiceRequest list in the car list
    @OneToMany(mappedBy = "carRef", fetch = FetchType.EAGER)
    private Set<CarServiceRequest> carServiceRequestSet;

}
