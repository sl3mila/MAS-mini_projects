package multiaspect;

public class Patient {
    private String firstName;
    private String lastName;
    private String patientNum;
    private String str;
    private Enum<Age> age;

    public Patient(String firstName, String lastName, String patientNum, Enum<Age> age, String str) {
        isNameValid(firstName);
        isNameValid(lastName);
        isPatientNumValid(patientNum);
        if(age == Age.Child){
            isGuardian(str);
        } else if (age == Age.Adult) {
            isPhoneNumValid(str);
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.patientNum = patientNum;
        this.age = age;
        this.str = str;
    }

    //getters and setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatientNum() {
        return patientNum;
    }
    public void setPatientNum(String patientNum) {
        this.patientNum = patientNum;
    }

    //adult
    public String getPhoneNum() {
        if(age == Age.Adult) {
            return str;
        }
        throw new IllegalStateException("Patient is not an adult");
    }
    public void setPhoneNum(String phoneNum) {
        if(age == Age.Adult) {
            isPhoneNumValid(phoneNum);
            this.str = phoneNum;
            return;
        }
        throw new IllegalStateException("Patient is not an adult");
    }

    //child
    public String getGuardian() {
        if(age == Age.Child) {
            return str;
        }
        throw new IllegalStateException("Patient is not an adult");
    }
    public void setGuardian(String guardian) {
        if(age == Age.Child) {
            isGuardian(guardian);
            this.str = guardian;
            return;
        }
        throw new IllegalStateException("Patient is not an adult");
    }

    //validators
    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }

    private void isPatientNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("Patient number must be 7 digits long");
        }
    }
    private void isPhoneNumValid(String num){
        if(!(
                num != null && (
                        num.matches("\\d{3}-\\d{3}-\\d{3}") //123-123-123
                                || num.matches("\\d{3} \\d{3} \\d{3}") //123 123 123
                                || num.matches("\\d{9}")) //123123123
        )){
            throw new IllegalArgumentException("Wrong format of phone number");
        }
    }
    private void isGuardian(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+ [A-Z][a-z]+"))){
            throw new IllegalArgumentException("Name can't be null or invalid");
        }
    }
}
