package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vet")
public class Vet extends Person{
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialty",
            joinColumns = @JoinColumn(name = "vet_id"),
    inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

}
