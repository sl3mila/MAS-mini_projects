package pja.mas.eslemp.s28796_mp5.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Nurse extends StaffMember{
    @Min(300)
    private Double bonus;
}
