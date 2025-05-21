package pja.mas.eslemp.s28796_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Doctor extends StaffMember{
    @NotBlank(message = "License number is mandatory")
    @Pattern(regexp = "^[0-9]{7}$", message = "License number has to contain 7 digits")
    protected String licenseNum;

    @ElementCollection
    @CollectionTable(name="doctor_skill", joinColumns = @JoinColumn(name="doctor_id"))
    @Builder.Default
    private Set<String> skills = new HashSet<>();

    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Visit> visits;
}
