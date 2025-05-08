import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Person {
    //Ekstensja
    protected String pesel; //unique
    protected static Set<String> pesels = new HashSet<>();
    protected String firstName;
    protected String lastName;
    protected LocalDateTime birthday;   //static
    private static final ArrayList<Person> people = new ArrayList<>();

    public Person(String pesel, String firstName, String lastName , LocalDateTime birthday) {
        //validation
        isPeselValid(pesel);
        isNameValid(firstName);
        isNameValid(lastName);
        isBirthdayValid(birthday);

        //new object
        this.pesel = pesel;
        pesels.add(pesel);

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;

        //add to extension
        people.add(this);
    }

    //getters and setters
    public String getPesel(){
        return pesel;
    }
    private void setPesel(String pesel){
        isPeselValid(pesel);    //sprawdza czy pesel ma dobry format i czy nie istnieje już taki pesel
        pesels.remove(this.pesel);  //usunięcie poprzedniego peselu
        this.pesel = pesel; //ustawienie nowego peselu
        pesels.add(pesel);  //dodanie nowego peselu
    }

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

    public LocalDateTime getBirthday(){
        return birthday;
    }
    private void setBirthday(LocalDateTime date){
        isBirthdayValid(date);
        birthday = date;
    }

    public static ArrayList<Person> getPeople(){
        return new ArrayList<>(people);
    }
    public int getAge(){
        return LocalDate.now().getYear() - birthday.getYear();
    }

    //validation
    protected void isPeselValid(String pesel){
        if (!(pesel != null && pesel.matches("\\d{11}")) || pesels.contains(pesel)) {
            throw new IllegalArgumentException("Pesel has to be 11 digits long");
        }
    }
    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }
    protected void isBirthdayValid(LocalDateTime date){
        if(!(date != null && date.isBefore(LocalDateTime.now()))){
            throw new IllegalArgumentException("birthday in the future");
        }
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }
}
