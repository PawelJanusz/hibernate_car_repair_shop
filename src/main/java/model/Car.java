package model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

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
    private int productionYear;
    private int course;


}
