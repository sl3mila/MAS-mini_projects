package pja.mas.eslemp.s28796_mp5.repository;

import org.springframework.data.repository.CrudRepository;
import pja.mas.eslemp.s28796_mp5.model.Patient;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
