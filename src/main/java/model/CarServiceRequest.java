package model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CarServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int costs;

    private boolean repairedDone;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime repaired;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Car carRef;

    public CarServiceRequest(String description, int costs, boolean repairedDone) {
        this.description = description;
        this.costs = costs;
        this.repairedDone = repairedDone;
    }

}
