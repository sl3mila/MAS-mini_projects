import abstractClass.*;
import abstractClass.Doctor;
import abstractClass.Nurse;
import dynamic.StaffMember;
import overlapping.*;

import java.time.LocalDateTime;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== abstract class ==========");
        Doctor d1 = new Doctor("1234567", "Sandra", "Konik", "Onkologia",
                LocalDateTime.of(2020, 12, 1, 0, 0), 221.20f);
        Nurse n1 = new Nurse("2234567", "Dorota", "Branko",
                LocalDateTime.of(2020, 12, 1, 0, 0), 80.10f);

        d1.setHoursInMonth(100);
        n1.setHoursInMonth(100);

        System.out.println(d1 + " earnings this month " + d1.getMonthEarning());
        System.out.println(n1 + " earnings this month " + n1.getMonthEarning());

        System.out.println();
        System.out.println("========== overlapping ==========");
        Person p1 = new Person("Sandra", "Konik", EnumSet.of(PersonVer.StuffMember),221.20f);
        Person p2 = new Person("Barbara", "Kaszelek", EnumSet.of(PersonVer.StuffMember, PersonVer.Patient),
                "0987654", 221.20f);

        System.out.println(p1 + " " + p1.getWage());
        System.out.println(p2 + " " + p2.getWage() + " " + p2.getPatientNum());

        System.out.println();
        System.out.println("========== dynamic ==========");
        StaffMember sm1 = new StaffMember("Karolina", "Wiaderko", 100);
        sm1 = new dynamic.Doctor(sm1, "1234567", "kardiologia");

        System.out.println();
        System.out.println("========== multiple heritence ==========");

    }
}