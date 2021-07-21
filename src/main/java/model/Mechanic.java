package model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private boolean isHired;

    public Mechanic(String firstName, String lastName, boolean isHired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isHired = isHired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Mechanic mechanic = (Mechanic) o;
        return isHired == mechanic.isHired &&
                Objects.equals(id, mechanic.id) &&
                Objects.equals(firstName, mechanic.firstName) &&
                Objects.equals(lastName, mechanic.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, isHired);
    }
}
