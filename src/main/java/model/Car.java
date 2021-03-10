package model;

import lombok.*;

import javax.persistence.*;

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
    private Long registrationNumber;

    @Enumerated(value = EnumType.STRING)
    private Brand brand;
    private double engineCapacity;


}
