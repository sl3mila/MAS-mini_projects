import java.time.LocalDateTime;
import java.util.*;

public class Patient extends Person {
    private final String patientNum;
    private String email = null;
    private Doctor supervisor = null;
    private final List<Referal> givenRefferals;
    private static final List<Patient> patients = new ArrayList<>();

    public Patient(String pesel, String firstName, String lastName, LocalDateTime birthday, String patientNum,
                   String phoneNum) {
        super(pesel, firstName, lastName, birthday, phoneNum);
        //validation
        isPatientNumValid(patientNum);

        //new object
        this.patientNum = patientNum;
        this.givenRefferals = new ArrayList<>();

        patients.add(this);
    }

    public Patient(String pesel, String firstName, String middleName, String lastName, LocalDateTime birthday,
                   String patientNum, String phoneNum) {
        super(pesel, firstName, middleName, lastName, birthday, phoneNum);
        //validation
        isPatientNumValid(patientNum);

        //new object
        this.patientNum = patientNum;
        this.givenRefferals = new ArrayList<>();

        patients.add(this);
    }

    //getters and setters
    public String getPatientNum() {
        return patientNum;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        isEmailValid(email);
        this.email = email;
    }

    public Doctor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Doctor supervisor) {
        isSupervisorValid(supervisor);
        if(this.supervisor != null && supervisor == null){  //pacjent był zapisany u kogoś ale już nie jest
            this.getSupervisor().getSupervised().remove(this);
        }
        this.supervisor = supervisor;
        if(supervisor != null) {  // jeśli nowy lekarz nie jest null dodajemy do odpowiedniego lekarza pacjenta
            supervisor.addSupervised(this);
        }
    }

    public List<Referal> getGivenRefferals() {
        return new ArrayList<>(givenRefferals);
    }

    public void addGivenRefferal(Referal refferal) {
        isRefferalValid(refferal);
        this.givenRefferals.add(refferal);
    }

    public static List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    //validation
    private void isPatientNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("Patient number must be 7 digits long");
        }
    }
    private void isEmailValid(String email){
        if (!(email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))){    //^ - start od string; $ - end of string
            throw new IllegalArgumentException("Email is incorrect");
        }
    }
    private void isSupervisorValid(Doctor supervisor){
        if(supervisor != null && !Doctor.getDoctors().contains(supervisor)){
            throw new IllegalArgumentException(supervisor + " is not in the system");
        }
    }

    private static void isRefferalValid(Referal ref){
        if(!Referal.getRefferals().contains(ref)){
            throw new IllegalArgumentException(ref + " doesn't exist");
        }
    }
}
