package abstractClass;

import java.time.LocalDateTime;

public class Nurse extends StaffMember{
    private String licenseNum;

    public Nurse(String licenseNum, String firstName, String lastName, LocalDateTime employment, float wage) {
        super(firstName, lastName, employment, wage);
        //validation
        isLicenseNumValid(licenseNum);

        //creating object
        this.licenseNum = licenseNum;
    }

    //getters and setters

    public String getLicenseNum() {
        return licenseNum;
    }
    public void setLicenseNum(String licenseNum) {
        isLicenseNumValid(licenseNum);
        this.licenseNum = licenseNum;
    }

    //validation
    private static void isLicenseNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("license number must be 7 digits long");
        }
    }

    //mothods
    public float getBonus() {
        return (float) (wage * hoursInMonth * 0.1);
    }

    @Override
    public float getMonthEarning() {
        return wage * hoursInMonth + getBonus();
    }
}
