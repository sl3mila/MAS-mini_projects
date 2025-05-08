package dynamic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class StaffMember {

    //Ekstensja
    private String firstName;
    private String lastName;
    private float wage;
    private static List<StaffMember> staffMembers = new ArrayList<>();

    //constructor for stuff member
    public StaffMember(String firstName, String lastName, float wage) {
        //validation
        isNameValid(firstName);
        isNameValid(lastName);
        isWageValid(wage);

        //new object
        this.firstName = firstName;
        this.lastName = lastName;
        this.wage = wage;

        staffMembers.add(this);
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

    public float getWage(){
        return wage;
    }
    public void setWage(float wage){
        isWageValid(wage);
        this.wage = wage;
    }

    public static List<StaffMember> getStaffMembers() {
        return Collections.unmodifiableList(staffMembers);
    }
    public static void removeStaffMember(StaffMember staffMember){
        if(staffMember != null){
            staffMembers.remove(staffMember);
        }
    }

    //validation
    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }

    private static void isWageValid(float wage){
        if (wage < 0){
            throw new IllegalArgumentException("Wage can't be smaller than 0");
        }
    }
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + ": " + firstName + " " + lastName;
    }
}
