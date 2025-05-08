package subset;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Doctor extends Person {
    private String licenseNum; //PWZ - prawo wykonywania zawodu
    private List<Ward> worksAt;
    private List<Ward> directs;
    private static final List<Doctor> doctors = new ArrayList<>(); //atrybuty powtarzalnye

    public Doctor(String pesel, String firstName, String lastName , LocalDateTime birthday, Ward worksAt){
        super(pesel, firstName, lastName, birthday);

        //validation
        isLicenseNumValid(licenseNum);
        isWordValid(worksAt);

        //new object
        this.licenseNum = licenseNum;
        this.worksAt = new ArrayList<>();
        this.worksAt.add(worksAt);

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

    public List<Ward> getWorksAt() {
        return Collections.unmodifiableList(worksAt);
    }

    public void addWorksAt(Ward ward) {
        isWordValid(ward);
        this.worksAt.add(ward);
        if(!ward.getWorker().contains(this)){
            ward.addWorker(this);
        }
    }
    public void removeWorksAt(Ward ward) {
        isWordValid(ward);
        this.worksAt.remove(ward);
        if(directs.contains(ward)) {
            this.removeDirects(ward);
        }
        if(ward.getWorker().contains(this)){
            ward.removeWorker(this);
        }
    }

    public List<Ward> getDirects() {
        return Collections.unmodifiableList(worksAt);
    }

    public void addDirects(Ward ward) {
        isWordValid(ward);
        isDirectsValid(ward);
        this.worksAt.add(ward);
        if(!ward.getWorker().contains(this)){
            ward.addWorker(this);
        }
    }
    public void removeDirects(Ward ward) {
        isWordValid(ward);
        this.worksAt.remove(ward);
        if(ward.getWorker().contains(this)){
            ward.removeWorker(this);
        }
    }

    private void isDirectsValid(Ward ward){
        if(!worksAt.contains(ward)){
            throw new IllegalArgumentException("for doctor to direct ward they have to be working in it");
        }
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

    private static void isWordValid(Ward ward){
        if(!Ward.getWards().contains(ward)){
            throw new IllegalArgumentException("word doesn't exist");
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

