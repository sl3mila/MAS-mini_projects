package multiaspect;

import java.time.LocalDateTime;

public class chronicPatient extends Patient{
    private String illnes;
    private LocalDateTime dateOfDiagnosis;

    public chronicPatient(String firstName, String lastName, String patientNum, Enum<Age> age, String str,
                          String illnes, LocalDateTime dateOfDiagnosis) {
        super(firstName, lastName, patientNum, age, str);
        //validators
        isNameValid(illnes);
        isDateOfDiagnosisValid(dateOfDiagnosis);

        //object
        this.illnes = illnes;
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    public String getIllnes() {
        return illnes;
    }
    public void setIllnes(String illnes) {
        isNameValid(illnes);
        this.illnes = illnes;
    }

    public LocalDateTime getDateOfDiagnosis() {
        return dateOfDiagnosis;
    }
    public void setDateOfDiagnosis(LocalDateTime dateOfDiagnosis) {
        isDateOfDiagnosisValid(dateOfDiagnosis);
        this.dateOfDiagnosis = dateOfDiagnosis;
    }

    //validators
    private void isDateOfDiagnosisValid(LocalDateTime date){
        if(date == null || date.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Date of diagnosis can't be null or in future");
        }
    }
}
