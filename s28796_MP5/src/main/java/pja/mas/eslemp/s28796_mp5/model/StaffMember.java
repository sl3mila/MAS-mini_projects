package pja.mas.eslemp.s28796_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class StaffMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private Double wage;
    private Integer hoursMonthly;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ward worksIn;
}
