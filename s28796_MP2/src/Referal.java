import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Referal {
    private final String recommandation;
    private final String description;
    private final LocalDateTime dateOfRefferal;
    private static List<Referal> refferals = new ArrayList<>();
    private final Doctor giver;
    private final Patient given;

    public Referal(Doctor giver, Patient given, String recommandation, String description, LocalDateTime dateOfRefferal){
        //validation
        isGiverValid(giver);
        isGivenValid(given);
        isRecommandationValid(recommandation);
        isDescriptionValid(description);
        isDateOfRefferalValid(dateOfRefferal);

        //association
        this.giver = giver;
        this.given = given;

        //object
        this.recommandation = recommandation;
        this.description = description;
        this.dateOfRefferal = dateOfRefferal;

        refferals.add(this);
        giver.addGivenRefferal(this);
        given.addGivenRefferal(this);
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

    public Doctor getGiver() {
        return giver;
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
    private void isGiverValid(Doctor giver){
        if (giver != null) {
            boolean doesntExist = true;
            for (Doctor d : Doctor.getDoctors()) {
                if (d.equals(giver)) {
                    doesntExist = false;
                }
            }
            if (doesntExist) {
                throw new IllegalArgumentException("Doctor " + giver + ", doesn't exist");
            }
        }else{
            throw new IllegalArgumentException("Giver can't be null");
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
        return "Docotr " + giver + " gave patient " + given + " recommendation for " + recommandation;
    }
}
