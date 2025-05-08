import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Doctor extends Person {
    private String licenseNum; //PWZ - prawo wykonywania zawodu
    private Ward worksAt;
    private LocalDateTime dateOfEmployment;
    private LocalDateTime dateOfDismissal = null;
    private final List<Patient> supervised;
    private final List<Referal> givenRefferals;
    private static final List<Doctor> doctors = new ArrayList<>(); //atrybuty powtarzalnye

    public Doctor(String pesel, String firstName, String lastName , LocalDateTime birthday, String licenseNum,
                  Ward worksAt, LocalDateTime dateOfEmployment, String phoneNum){
        super(pesel, firstName, lastName, birthday, phoneNum);

        //validation
        isLicenseNumValid(licenseNum);
        isWorksAtValid(worksAt);
        isDateOfEmploymentValid(dateOfEmployment, birthday);

        //new object
        this.licenseNum = licenseNum;
        this.worksAt = worksAt;
        worksAt.addWorkers(this);
        this.dateOfEmployment = dateOfEmployment;
        this.supervised = new ArrayList<>();
        this.givenRefferals = new ArrayList<>();

        //add to extension
        doctors.add(this);
    }
    public Doctor(String pesel, String firstName, String middleName, String lastName , LocalDateTime birthday,
                  String licenseNum, Ward worksAt, LocalDateTime dateOfEmployment, String phoneNum){
        super(pesel, firstName, middleName, lastName, birthday, phoneNum);

        //validation
        isLicenseNumValid(licenseNum);
        isWorksAtValid(worksAt);
        isDateOfEmploymentValid(dateOfEmployment, birthday);

        //new object
        this.licenseNum = licenseNum;
        this.worksAt = worksAt;
        worksAt.addWorkers(this);
        this.dateOfEmployment = dateOfEmployment;
        this.supervised = new ArrayList<>();
        this.givenRefferals = new ArrayList<>();

        //add to extension
        doctors.add(this);
    }

    //objects writer and reader
    /*protected void write(DataOutputStream stream) throws IOException {
        stream.writeUTF(pesel);
        stream.writeUTF(firstName);
        stream.writeUTF(middleName != null ? middleName : "NULL");
        stream.writeUTF(lastName);
        stream.writeLong(birthday.toEpochDay());
        stream.writeUTF(licenseNum);
        stream.writeLong(dateOfEmployment.toEpochDay());
        //todo phone number and id
        if (dateOfDismissal != null) {
            stream.writeLong(dateOfDismissal.toEpochDay());
        }else{
            stream.writeLong(0);
        }
    }
    protected void read(DataInputStream stream) throws IOException{
        pesel = stream.readUTF();
        firstName = stream.readUTF();
        String middleNameRead = stream.readUTF();
        middleName = middleNameRead.equals("NULL") ? null : middleNameRead;
        lastName = stream.readUTF();
        long epochDayBir = stream.readLong();
        birthday = LocalDate.ofEpochDay(epochDayBir);
        licenseNum = stream.readUTF();
        long epochDayEmp = stream.readLong();
        dateOfEmployment = LocalDate.ofEpochDay(epochDayEmp);
        //todo phone number and id
        if (stream.readLong() == 0){
            dateOfDismissal = null;
        }else {
            long epochDayDis = stream.readLong();
            dateOfDismissal = LocalDate.ofEpochDay(epochDayDis);
        }
    }

    //extention writer and reader
    public static void writeExtent(DataOutputStream stream) throws IOException{
        stream.writeInt(doctors.size());
        for(Doctor doctor : doctors){
            doctor.write(stream);
        }
    }
    public static void readExtent(DataInputStream stream) throws IOException{
        Doctor doctor = null;

        int docorsCount = stream.readInt();
        doctors.clear();
        for (int i = 0; i < docorsCount; i++){
            doctor = new Doctor();
            doctor.read(stream);
            //to do add doctors
            doctors.add(doctor);
        }
    }*/

    //getters and setters
    public String getLicenseNum() {
        return licenseNum;
    }
    public void setLicenseNum(String number){
        isLicenseNumValid(number);
        licenseNum = number;
    }

    public LocalDateTime getDateOfEmployment(){
        return dateOfEmployment;
    }
    private void setDateOfEmployment(LocalDateTime date){
        isDateOfEmploymentValid(date, birthday);
        this.dateOfEmployment = date;
    }

    public LocalDateTime getDateOfDismissal(){
        return dateOfDismissal;
    }
    public void setDateOfDismissal(LocalDateTime date){
        isDateOfDismissalValid(date);
        dateOfDismissal = date;
    }

    public Ward getWorksAt(){
        return worksAt;
    }
    public void setWorksAt(Ward worksAt){
        isWorksAtValid(worksAt);
        this.worksAt = worksAt;
        worksAt.addWorkers(this);
    }

    public List<Patient> getSupervised() {
        return new ArrayList<>(supervised);
    }
    public void addSupervised(Patient patient){
        isSupervisedValid(patient);
        supervised.add(patient);
        if (patient.getSupervisor() == null){
            patient.setSupervisor(this);
        } else if (!patient.getSupervisor().equals(this)) {
            patient.setSupervisor(null);
        }
    }
    public void removeSupervised(Patient patient){

        supervised.remove(patient);
    }

    public List<Referal> getGivenRefferals() {
        return new ArrayList<>(givenRefferals);
    }
    public void addGivenRefferal(Referal refferal){
        isRefferalValid(refferal);
        this.givenRefferals.add(refferal);
    }

    public static List<Doctor> getDoctors(){
        return new ArrayList<>(doctors);
    }

    //validation
    private static void isLicenseNumValid(String num){
        if (!(num != null && num.matches("\\d{7}"))){
            throw new IllegalArgumentException("license number must be 7 digits long");
        }
    }
    private static void isWorksAtValid(Ward worksAt){
        if (!Ward.getWards().contains(worksAt)) {
            throw new IllegalArgumentException("Ward with this name doesn't exist");
        }
    }
    private static void isDateOfEmploymentValid(LocalDateTime date, LocalDateTime birthday){
        if(!(date != null && date.isAfter(birthday.plusYears(18)) //doctor has to be at least 18 years old
                && date.isBefore(LocalDateTime.now().plusMonths(6)))){  //doctor have recorded day of employment max 6 months in advance
            throw new IllegalArgumentException("Date of employment is null or invalid");
        }
    }
    private static void isDateOfDismissalValid(LocalDateTime date){
        if(date != null && date.isAfter(date)){
            throw new IllegalArgumentException("Date od dismissal can't be null or in future");
        }
    }

    private void isSupervisedValid(Patient patient){
        if(patient == null){
            throw new IllegalArgumentException("Supervised can't be null");
        } else if (!Patient.getPatients().contains(patient)){   //sprawdzenie czy dany pacjent istnieje
            throw new IllegalArgumentException(patient + " doesn't exist in system");
        } else if (supervised.contains(patient)) {  //sprawdzenie czy ten lekarz nie ma już wpisanego tego pacjenta
            throw new IllegalArgumentException(patient + " already added to " + this);
        } else {
            for (Doctor doc : getDoctors()){    //sprawdzenie czy inny lekarz już nie obserwuje tego pacjenta
                if(doc.getSupervised().contains(patient)){
                    throw new IllegalArgumentException(doc + " already has " + patient + " as patient");
                }
            }
        }
    }

    private static void isRefferalValid(Referal ref){
        if(!Referal.getRefferals().contains(ref)){
            throw new IllegalArgumentException(ref + " doesn't exist");
        }
    }

    //class methods
    public static Doctor findByLicense(String number){
        if (number != null) {
            for (Doctor doc : doctors) {
                if (doc.getLicenseNum().equals(number)) {
                    return doc;
                }
            }
            throw new NoSuchElementException("Doctor with license number: " + number + " doesn't exist");
        }else{
            throw new IllegalArgumentException("License number can't be null");
        }
    }
}
