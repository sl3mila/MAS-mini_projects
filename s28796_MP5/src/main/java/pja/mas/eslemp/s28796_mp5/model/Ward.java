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
@ToString
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 25)
    private String name;

    @NotBlank(message = "Building is mandatory")
    @Pattern(regexp = "^[A-Z]{1}$", message = "Patient number has to contain 1 capital letter")
    private String building;

    @OneToMany(mappedBy = "worksIn")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<StaffMember> staffMembers = new HashSet<>();
}
