package multiaspect;

import java.time.LocalDateTime;

public class regularPatient extends Patient {
    private LocalDateTime lastCheckUp;

    public regularPatient(String firstName, String lastName, String patientNum, Enum<Age> age, String str,
                          LocalDateTime lastCheckUp) {
        super(firstName, lastName, patientNum, age, str);
        isDateValid(lastCheckUp);

        this.lastCheckUp = lastCheckUp;
    }

    public LocalDateTime getLastCheckUp() {
        return lastCheckUp;
    }
    public void setLastCheckUp(LocalDateTime lastCheckUp) {
        isDateValid(lastCheckUp);
        this.lastCheckUp = lastCheckUp;
    }

    //validators
    private void isDateValid(LocalDateTime date){
        if(date == null || date.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Date of diagnosis can't be null or in future");
        }
    }
}
