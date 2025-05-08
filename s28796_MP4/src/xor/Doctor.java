package xor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Doctor extends Person {
    private String licenseNum; //PWZ - prawo wykonywania zawodu
    private static final List<Doctor> doctors = new ArrayList<>(); //atrybuty powtarzalnye

    public Doctor(String pesel, String firstName, String lastName , LocalDateTime birthday, int salary){
        super(pesel, firstName, lastName, birthday);

        //validation
        isLicenseNumValid(licenseNum);
        //new object
        this.licenseNum = licenseNum;

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


    public static List<Doctor> getDoctors(){
        return new ArrayList<>(doctors);
    }

    //validation
    private static void isLicenseNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("license number must be 7 digits long");
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

