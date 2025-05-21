package pja.mas.eslemp.s28796_mp5.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints ={
        @UniqueConstraint(columnNames = {"doctor_id", "patient_id"})
})
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull
    private Patient patient;

    @NotBlank(message = "Recommendation name is mandatory")
    @Size(min = 2, max = 50)
    private String diagnosis;

    @NotBlank(message = "Description name is mandatory")
    @Size(min = 2, max = 255)
    private String description;
}
