package model;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
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

    @ManyToMany(mappedBy = "carSet")
    private Set<CarServiceRequest> carServiceRequestSet;

}
