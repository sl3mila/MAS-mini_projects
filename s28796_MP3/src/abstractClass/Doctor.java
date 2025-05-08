package abstractClass;

import java.time.LocalDateTime;

public class Doctor extends StaffMember{
    private String licenseNum;
    private String specialisation;

    public Doctor(String licenseNum,String firstName, String lastName, String specialisation, LocalDateTime employment,
                  float wage) {
        super(firstName, lastName, employment, wage);
        //validation
        isLicenseNumValid(licenseNum);
        isSpecialisationValid(specialisation);

        //createing obejct
        this.licenseNum = licenseNum;;
        this.specialisation = specialisation;
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


    @Override
    public float getMonthEarning() {
        return wage * hoursInMonth;
    }
}
