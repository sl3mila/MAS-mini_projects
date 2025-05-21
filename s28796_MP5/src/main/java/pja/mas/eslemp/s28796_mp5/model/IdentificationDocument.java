package pja.mas.eslemp.s28796_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
public class IdentificationDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Serial number is mandatory")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{6}$", message = "Serial number has to contain 3 letters and 6 digits")
    private String serialNum;

    @NotNull
    private LocalDate expiryDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    private Patient owner;
}
