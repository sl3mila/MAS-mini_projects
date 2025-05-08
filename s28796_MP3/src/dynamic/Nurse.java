package dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nurse extends StaffMember{
    private String licenseNum;

    private static List<Nurse> nurses = new ArrayList<>();

    public Nurse(StaffMember prevStaffMember, String licenseNum) {
        super(prevStaffMember.getFirstName(), prevStaffMember.getLastName(), prevStaffMember.getWage());

        //validaiton
        isLicenseNumValid(licenseNum);

        //object
        this.licenseNum = licenseNum;

        StaffMember.removeStaffMember(prevStaffMember);
        nurses.add(this);
    }

    public String getLicenseNum() {
        return licenseNum;
    }
    public void setLicenseNum(String licenseNum) {
        isLicenseNumValid(licenseNum);
        this.licenseNum = licenseNum;
    }

    public static List<Nurse> getNurses() {
        return Collections.unmodifiableList(nurses);
    }
    public static void removeNurse(Nurse nurse){
        if(nurse != null){
            nurses.remove(nurse);
        }
    }

    //validation
    private static void isLicenseNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("license number must be 7 digits long");
        }
    }
}
