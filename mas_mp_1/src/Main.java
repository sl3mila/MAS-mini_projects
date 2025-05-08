import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Adding identification types ===");
        Person.IdentificationDocument.addTypesSerialNum("id", "^[A-Z]{3}\\d{6}$");
        Person.IdentificationDocument.addTypesSerialNum("passport", "^[A-Z]{2}\\d{6}$");

        System.out.println(Person.IdentificationDocument.getTypesSerialNum());

        System.out.println("=== Adding Wards ===");

        Ward w1 = new Ward("Onkologia", 'B', "Warszawa, Polna, 02-123");
        System.out.println("Added " + w1);

        Ward w2 = new Ward("Neurologia", 'A', "Warszawa, Polna, 02-123");
        System.out.println("Added " + w2);

        System.out.println("\n=== Adding People ===");

        Person p1 = new Person("84849302023", "Beata", "Denko",
                LocalDateTime.of(1995, 4, 26, 0, 0), "111-111-111");
        System.out.println("Added " + p1);

        System.out.println("\n=== Adding Doctors ===");

        Doctor d1 = new Doctor(p1.getPesel(), p1.getFirstName(), p1.getLastName(), p1.getBirthday(),
                "9876543", w2,  LocalDateTime.of(2024, 10, 12, 0, 0),
                "123123123");
        System.out.println("Added " + d1);

        Doctor d2 = new Doctor("46797643432", "Mariusz", "Bernard", "Zaklepa",
                LocalDateTime.of(1980, 2, 2, 0, 0), "1234567", w1
                , LocalDateTime.of(2020, 12, 12, 0, 0), "321 321 321");
        System.out.println("Added " + d2);

        Patient patient1 = new Patient("24327649872", "Katarzyna", "Kosik",
                LocalDateTime.of(1985, 7, 22, 0, 0), "0984567",
               "456-456-456");
        System.out.println("Added " + d2);



        System.out.println("\n=== Association ===");

        patient1.setSupervisor(d1);
        System.out.println(d1 + " supervises: "+ d1.getSupervised());

        System.out.println("\n=== Association with attribute ===");
        Referal r1 = new Referal(d1, patient1, "bla bla bla", "bula bla bla",
                LocalDateTime.of(2025, 2, 12, 0,0));
        System.out.println("refferal added: " + r1);

        System.out.println("\n=== Aggravated association ===");
        System.out.println(w1.getWorkers());
        System.out.println("In " + w1 + " found worker " + w1.findWorker("1234567"));

        System.out.println("\n=== Composition ===");

        System.out.println(p1.createIdentificationDocument("id", "POL987654",
                LocalDateTime.of(2027, 10, 12, 0, 0)));

        /*final String extentFile = "extentFile.bin";
        try {
            DataOutputStream out2 = new DataOutputStream(new BufferedOutputStream(new
                    FileOutputStream(extentFile)));
            Person.writeExtent(out2);
            Doctor.writeExtent(out2);
            Ward.writeExtent(out2);
            out2.close();

            DataInputStream in2 = new DataInputStream(new BufferedInputStream(new
                    FileInputStream(extentFile)));
            Person.readExtent(in2);
            Doctor.readExtent(in2);
            Ward.readExtent(in2);
            in2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Ward.getWards());
        System.out.println(Person.getPeople());
        System.out.println(Doctor.getDoctors());*/
    }
}