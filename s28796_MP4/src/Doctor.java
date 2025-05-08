import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Doctor extends Person {
    private String licenseNum; //PWZ - prawo wykonywania zawodu
    private int salary; //dynamic
    private final List<Patient> supervised;
    private static final List<Doctor> doctors = new ArrayList<>(); //atrybuty powtarzalnye

    public Doctor(String pesel, String firstName, String lastName , LocalDateTime birthday, int salary){
        super(pesel, firstName, lastName, birthday);

        //validation
        isLicenseNumValid(licenseNum);
        isSalaryValid(salary);

        //new object
        this.licenseNum = licenseNum;
        this.supervised = new ArrayList<>();
        this.salary = salary;

        //add to extension
        doctors.add(this);
    }

    //getters and setters
    public String getLicenseNum() {
        return licenseNum;
    }
    public void setLicenseNum(String number){
        isLicenseNumValid(number);
        licenseNum = number;
    }

    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        if (salary < this.salary){
            throw new IllegalArgumentException("salary can't be lower than previous one");
        }
        this.salary = salary;
    }

    public List<Patient> getSupervised() {
        return new ArrayList<>(supervised);
    }
    public void addSupervised(Patient patient){
        isSupervisedValid(patient);
        supervised.add(patient);
        if (patient.getSupervisor() == null){
            patient.setSupervisor(this);
        } else if (!patient.getSupervisor().equals(this)) {
            patient.setSupervisor(null);
        }
    }
    public void removeSupervised(Patient patient){

        supervised.remove(patient);
    }

    public static List<Doctor> getDoctors(){
        return new ArrayList<>(doctors);
    }

    //validation
    private static void isLicenseNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("license number must be 7 digits long");
        }
    }

    private void isSupervisedValid(Patient patient){
        if(patient == null){
            throw new IllegalArgumentException("Supervised can't be null");
        } else if (!Patient.getPatients().contains(patient)){   //sprawdzenie czy dany pacjent istnieje
            throw new IllegalArgumentException(patient + " doesn't exist in system");
        } else if (supervised.contains(patient)) {  //sprawdzenie czy ten lekarz nie ma już wpisanego tego pacjenta
            throw new IllegalArgumentException(patient + " already added to " + this);
        } else {
            for (Doctor doc : getDoctors()){    //sprawdzenie czy inny lekarz już nie obserwuje tego pacjenta
                if(doc.getSupervised().contains(patient)){
                    throw new IllegalArgumentException(doc + " already has " + patient + " as patient");
                }
            }
        }
    }

    private static void isSalaryValid(int salary){
        if(salary <= 10000){
            throw new IllegalArgumentException("salary can't be lower than 10000");
        }
    }


    //class methods
    public static Doctor findByLicense(String number){
        if (number != null) {
            for (Doctor doc : doctors) {
                if (doc.getLicenseNum().equals(number)) {
                    return doc;
                }
            }
            throw new NoSuchElementException("Doctor with license number: " + number + " doesn't exist");
        }else{
            throw new IllegalArgumentException("License number can't be null");
        }
    }
}

