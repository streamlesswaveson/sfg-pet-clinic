package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialty extends BaseEntity{
    private String description;
}
