package abstractClass;

import java.time.LocalDateTime;

public abstract
    class StaffMember {
    protected String firstName;
    protected String lastName;
    protected LocalDateTime dateOfEmployment;
    protected LocalDateTime dateOfDissmissal;
    protected float wage;
    protected int hoursInMonth;

    public StaffMember(String firstName, String lastName, LocalDateTime dateOfEmployment, float wage){
        super();

        //validation
        isNameValid(firstName);
        isNameValid(lastName);
        isDateOfEmploymentValid(dateOfEmployment);
        isWageValid(wage);

        //object
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfEmployment = dateOfEmployment;
        dateOfDissmissal = null;
        this.wage = wage;
        hoursInMonth = 0;
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

    public LocalDateTime getDateOfEmployment() {
        return dateOfEmployment;
    }

    public LocalDateTime getDateOfDissmissal() {
        return dateOfDissmissal;
    }
    public void setDateOfDissmissal(LocalDateTime dateOfDissmissal) {
        isDateOfDismissalValid(dateOfDissmissal, dateOfEmployment);
        this.dateOfDissmissal = dateOfDissmissal;
    }

    public float getWage() {
        return wage;
    }
    public void setWage(float wage) {
        isWageValid(wage);
        this.wage = wage;
    }

    public int getHoursInMonth() {
        return hoursInMonth;
    }
    public void setHoursInMonth(int hoursInMonth) {
        isHoursInMonthValid(hoursInMonth);
        this.hoursInMonth = hoursInMonth;
    }

    //validation
    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }
    protected static void isDateOfEmploymentValid(LocalDateTime date){
        if(!(date.isBefore(LocalDateTime.now().plusMonths(6)))){  //doctor have recorded day of employment max 6 months in advance
            throw new IllegalArgumentException("Date of employment is null or invalid");
        }
    }

    protected static void isDateOfDismissalValid(LocalDateTime date, LocalDateTime dateOfEmployment){
        if(!(date != null && date.isAfter(dateOfEmployment))){
            throw new IllegalArgumentException("Date od dismissal can't be null or in future");
        }
    }

    private static void isWageValid(float wage){
        if (wage < 0){
            throw new IllegalArgumentException("Wage can't be smaller than 0");
        }
    }

    private static void isHoursInMonthValid(int hours){
        if (hours < 0){
            throw new IllegalArgumentException("Hours can't be smaller than 0");
        }
    }

    //methods
    public abstract float getMonthEarning();

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
