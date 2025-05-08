package multipleInheritance;

public class Person {
    private String firstName;
    private String lastName;


    public Person(String firstName, String lastName) {
        //validation
        isNameValid(firstName);
        isNameValid(lastName);

        //new object
        this.firstName = firstName;
        this.lastName = lastName;
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

    //validation
    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
