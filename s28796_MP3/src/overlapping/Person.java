package overlapping;

import java.util.EnumSet;

public class Person implements Patient, StaffMember{
    //Ekstensja
    private String firstName;
    private String lastName;

    private String patientNum;
    private float wage;

    private EnumSet<PersonVer> personType;

    public Person(String firstName, String lastName , EnumSet<PersonVer> ver) {
        //validation
        isNameValid(firstName);
        isNameValid(lastName);
        isPersonVerValid(ver);

        //new object
        this.firstName = firstName;
        this.lastName = lastName;
        this.personType = ver;

    }

    //constructor for patient
    public Person(String firstName, String lastName , EnumSet<PersonVer> ver, String patientNum) {
        //validation
        isNameValid(firstName);
        isNameValid(lastName);
        isPersonVerValid(ver);

        //new object
        this.firstName = firstName;
        this.lastName = lastName;
        this.personType = ver;

        isPatientNumValid(patientNum);
        this.patientNum = patientNum;
    }

    //constructor for stuff member
    public Person(String firstName, String lastName, EnumSet<PersonVer> ver, float wage) {
        //validation
        isNameValid(firstName);
        isNameValid(lastName);
        isPersonVerValid(ver);

        //new object
        this.firstName = firstName;
        this.lastName = lastName;
        this.personType = ver;

        isWageValid(wage);
        this.wage = wage;
    }

    public Person(String firstName, String lastName, EnumSet<PersonVer> ver, String patientNum, float wage) {
        //validation
        isNameValid(firstName);
        isNameValid(lastName);
        isPersonVerValid(ver);

        //new object
        this.firstName = firstName;
        this.lastName = lastName;
        this.personType = ver;

        isPatientNumValid(patientNum);
        this.patientNum = patientNum;

        isWageValid(wage);
        this.wage = wage;
    }

    //getters and setters
    public String getFirstName(){
        return firstName;
    }
    private void setFirstName(String name){
        isNameValid(name);
        firstName = name;
    }

    public String getLastName(){
        return lastName;
    }
    private void setLastName(String name){
        isNameValid(name);
        lastName = name;
    }

    public EnumSet<PersonVer> getPersonType(){
        return EnumSet.copyOf(personType);
    }

    @Override
    public String getPatientNum() {
        if(personType.contains(PersonVer.Patient)) {
            return patientNum;
        }
        throw new IllegalStateException("Person is not a patient");
    }
    @Override
    public void setPatientNum(String patientNum) {
        if(personType.contains(PersonVer.Patient)) {
            isPatientNumValid(patientNum);
            this.patientNum = patientNum;
            return;
        }
        throw new IllegalStateException("Person is not a patient");
    }

    @Override
    public float getWage(){
        if(personType.contains(PersonVer.StuffMember)) {
            return wage;
        }
        throw new IllegalStateException("Person is not a stuff member");
    }
    @Override
    public void setWage(float wage){
        if(personType.contains(PersonVer.StuffMember)) {
            isWageValid(wage);
            this.wage = wage;
            return;
        }
        throw new IllegalStateException("Person is not a stuff member");
    }

    //validation
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

    private static void isWageValid(float wage){
        if (wage < 0){
            throw new IllegalArgumentException("Wage can't be smaller than 0");
        }
    }

    private static void isPersonVerValid(EnumSet<PersonVer> ver){
        if (ver == null){
            throw new IllegalArgumentException("Person type can't be null");
        }
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
