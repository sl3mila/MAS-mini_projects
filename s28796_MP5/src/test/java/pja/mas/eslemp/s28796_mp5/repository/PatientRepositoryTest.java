package pja.mas.eslemp.s28796_mp5.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PatientRepositoryTest {

    @Autowired
    private PatientRepository personRepository;

    @Test
    public void testRequiredDependencies(){
        assertNotNull(personRepository);
    }

    @Test
    public void TestFetchPeople(){
        personRepository.findAll();
    }

}