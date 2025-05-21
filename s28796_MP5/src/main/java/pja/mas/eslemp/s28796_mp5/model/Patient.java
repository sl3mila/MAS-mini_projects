package pja.mas.eslemp.s28796_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Pesel is mandatory")
    @Pattern(regexp = "^[0-9]{11}$", message = "PESEL has to contain 11 digits")
    protected String pesel;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 25)
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank(message = "Patient number is mandatory")
    @Pattern(regexp = "^[0-9]{7}$", message = "Patient number has to contain 7 digits")
    protected String patientNum;

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Visit> visits;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<IdentificationDocument> identificationDocuments = new HashSet<>();
}
