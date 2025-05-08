package multipleInheritance;

public class StaffMember extends Person{
    private float wage;

    public StaffMember(Person prevPerson, float wage) {
        super(prevPerson.getFirstName(), prevPerson.getLastName());
        isWageValid(wage);

        this.wage = wage;
    }

    public float getWage() {
        return wage;
    }
    public void setWage(float wage) {
        isWageValid(wage);
        this.wage = wage;
    }

    //validators
    private static void isWageValid(float wage){
        if (wage < 0){
            throw new IllegalArgumentException("Wage can't be smaller than 0");
        }
    }
}
