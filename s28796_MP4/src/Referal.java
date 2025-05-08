import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Referal {
    private final String recommandation;    //static
    private final String description;
    private final LocalDateTime dateOfRefferal;
    private static List<Referal> refferals = new ArrayList<>();
    private final Patient given;

    public Referal( Patient given, String recommandation, String description){
        LocalDateTime dateOfRefferal = LocalDateTime.now();

        //validation
        isGivenValid(given);
        isRecommandationValid(recommandation);
        isDescriptionValid(description);
        isDateOfRefferalValid(dateOfRefferal);

        //association
        this.given = given;

        //object
        this.recommandation = recommandation;
        this.description = description;
        this.dateOfRefferal = dateOfRefferal;

        refferals.add(this);
        given.addGivenReferal(this);
    }

    //getters and setters
    public String getRecommandation() {
        return recommandation;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateOfRefferal() {
        return dateOfRefferal;
    }

    public static List<Referal> getRefferals() {
        return new ArrayList<>(refferals);
    }


    public Patient getGiven() {
        return given;
    }

    //validators
    private void isRecommandationValid(String rec){
        int maxLength = 20;
        if (!(rec != null && rec.length() < maxLength)){
            throw new IllegalArgumentException("Recommandation cannot be null or longer than" + maxLength + " characters");
        }
    }
    private void isDescriptionValid(String desc){
        int maxLength = 150;
        if (!(desc != null && desc.length() < maxLength)){
            throw new IllegalArgumentException("Recommandation cannot be null or longer than" + maxLength + " characters");
        }
    }
    private void isDateOfRefferalValid(LocalDateTime date){
        if(!(date != null && date.isBefore(LocalDateTime.now()))){
            throw new IllegalArgumentException("Date of employment is null or invalid");
        }
    }
    private void isGivenValid(Patient given){
        if (given != null) {
            boolean doesntExist = true;
            for(Patient p : Patient.getPatients()){
                if (p.equals(given)){
                    doesntExist = false;
                }
            }
            if (doesntExist){
                throw new IllegalArgumentException("Patient " + given + ", doesn't exist");
            }
        }else{
            throw new IllegalArgumentException("Given can't be null");
        }
    }

    @Override
    public String toString(){
        return given + " recommendation for " + recommandation;
    }
}
