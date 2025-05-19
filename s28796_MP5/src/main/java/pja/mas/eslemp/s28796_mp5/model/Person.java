package pja.mas.eslemp.s28796_mp5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 11, max = 11)
    protected String pesel;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 25)
    protected String firstName;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 50)
    protected String lastName;

    /*@NotBlank(message = "Birthday is mandatory")
    protected LocalDateTime birthday;*/

}
