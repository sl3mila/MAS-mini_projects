package dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Doctor extends StaffMember{
    private String licenseNum;
    private String specialisation;
    private static List<Doctor> doctors = new ArrayList<>();

    public Doctor(StaffMember prevStaffMember, String licenseNum, String specialisation) {
        super(prevStaffMember.getFirstName(), prevStaffMember.getLastName(), prevStaffMember.getWage());
        //validation
        isLicenseNumValid(licenseNum);
        isSpecialisationValid(specialisation);

        //createing obejct
        this.licenseNum = licenseNum;;
        this.specialisation = specialisation;

        StaffMember.removeStaffMember(prevStaffMember);
        doctors.add(this);
    }

    //getters and setters
    public String getLicenseNum() {
        return licenseNum;
    }
    public void setLicenseNum(String licenseNum) {
        isLicenseNumValid(licenseNum);
        this.licenseNum = licenseNum;
    }

    public String getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(String specialisation) {
        isSpecialisationValid(specialisation);
        this.specialisation = specialisation;
    }

    public static List<Doctor> getDoctors() {
        return Collections.unmodifiableList(doctors);
    }
    public static void removeDoctor(Doctor doctor){
        if(doctor != null){
            doctors.remove(doctor);
        }
    }

    //validation
    private static void isLicenseNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("License number must be 7 digits long");
        }
    }

    private static void isSpecialisationValid(String spec){
        if (spec == null || spec.isEmpty()){
            throw new IllegalArgumentException("Specialisation can't be null");
        }
    }
}
