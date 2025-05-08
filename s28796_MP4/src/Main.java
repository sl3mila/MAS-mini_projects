import subset.Doctor;
import subset.Ward;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Ward ward = new Ward("Kardiologia");

        Doctor doctor = new Doctor("12345678901", "Barbara", "Koniak",
                LocalDateTime.of(1990, 12, 10, 0, 0), ward);

        Patient patient = new Patient("09876543212", "Perl", "Potato",
                LocalDateTime.of(2000, 6, 25, 0, 0), "2345678");


    }
}