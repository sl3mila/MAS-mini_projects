import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Person {
//Ekstensja
    protected String pesel;
    protected String firstName;
    protected String middleName = null;
    protected String lastName;
    protected LocalDateTime birthday;
    protected String phoneNum;
    private final List<IdentificationDocument> identificationDocuments = new ArrayList<>();
    private static final ArrayList<Person> people = new ArrayList<>();

    public Person(String pesel, String firstName, String lastName , LocalDateTime birthday, String phoneNum) {
        //validation
        isPeselValid(pesel);
        isNameValid(firstName);
        isNameValid(lastName);
        isBirthdayValid(birthday);
        isPhoneNumValid(phoneNum);

        //new object
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNum = reformatPhoneNum(phoneNum);

        //add to extension
        people.add(this);
    }
    public Person(String pesel, String firstName, String middleName, String lastName , LocalDateTime birthday,
                  String phoneNum) {
        //validation
        isPeselValid(pesel);
        isNameValid(firstName);
        isMiddleNameValid(middleName);
        isNameValid(lastName);
        isBirthdayValid(birthday);
        isPhoneNumValid(phoneNum);

        //new object
        this.pesel = pesel;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.phoneNum = reformatPhoneNum(phoneNum);

        //add to extension
        people.add(this);
    }

    /*protected void write(DataOutputStream stream) throws IOException {
        stream.writeUTF(pesel);
        stream.writeUTF(firstName);
        stream.writeUTF(middleName != null ? middleName : "NULL");
        stream.writeUTF(lastName);
        stream.writeLong(birthday.toEpochDay());
    }
    protected void read(DataInputStream stream) throws IOException{
        pesel = stream.readUTF();
        firstName = stream.readUTF();
        String middleNameRead = stream.readUTF();
        middleName = middleNameRead.equals("NULL") ? null : middleNameRead;
        lastName = stream.readUTF();
        long epochDay = stream.readLong();
        System.out.println("Read epochDay: " + LocalDate.ofEpochDay(epochDay));
        birthday = LocalDate.ofEpochDay(epochDay);
    }

    //extention writer and reader
    public static void writeExtent(DataOutputStream stream) throws IOException{
        stream.writeInt(people.size());
        for(Person person : people){
            person.write(stream);
        }
    }
    public static void readExtent(DataInputStream stream) throws IOException{
        Person person = null;

        int peopleCount = stream.readInt();
        people.clear();
        for (int i = 0; i < peopleCount; i++){
            person = new Person();
            person.read(stream);
            people.add(person);
        }
    }*/

    //getters and setters
    public String getPesel(){
        return pesel;
    }
    private void setPesel(String pesel){
        isPeselValid(pesel);
        this.pesel = pesel;
    }

    public String getFirstName(){
        return firstName;
    }
    private void setFirstName(String name){
        isNameValid(name);
        firstName = name;
    }

    public String getMiddleName(){
        return middleName;
    }
    public void setMiddleName(String name){    //setter atrybutu opcjonalnego
        isMiddleNameValid(name);
        middleName = name;
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

    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        isPhoneNumValid(phoneNum);
        this.phoneNum = reformatPhoneNum(phoneNum);
    }
    private String reformatPhoneNum(String num){
        if (!num.matches("\\d{3} \\d{3} \\d{3}")) {
            System.out.print("Phone number has been reformated from: " + num);
            num = num.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1 $2 $3");
            System.out.println(", to: " + num);
        }
        return num;
    }

    public List<IdentificationDocument> getIdentificationDocuments() {
        return new ArrayList<>(identificationDocuments);
    }
    public IdentificationDocument createIdentificationDocument(String type, String serialNum,
                                                               LocalDateTime expirationDate){
        isIdentificationDocumentValid(type);
        IdentificationDocument id = new IdentificationDocument(type, serialNum, expirationDate);
        identificationDocuments.add(id);

        return id;
    }

    public static ArrayList<Person> getPeople(){
        return new ArrayList<>(people);
    }
    public int getAge(){
        return LocalDate.now().getYear() - birthday.getYear();
    }

    //validation
    protected void isPeselValid(String pesel){
        if (!(pesel != null && pesel.matches("\\d{11}"))) {
            throw new IllegalArgumentException("Pesel has to be 11 digits long");
        }
    }
    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }
    protected void isMiddleNameValid(String name){
        if(name != null && !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("middle name be empty or invalid");
        }
    }
    protected void isBirthdayValid(LocalDateTime date){
        if(!(date != null && date.isBefore(LocalDateTime.now()))){
            throw new IllegalArgumentException("birthday in the future");
        }
    }
    private void isPhoneNumValid(String num){
        if(!(
                num != null && (
                        num.matches("\\d{3}-\\d{3}-\\d{3}") //123-123-123
                                || num.matches("\\d{3} \\d{3} \\d{3}") //123 123 123
                                || num.matches("\\d{9}")) //123123123
                )){
            throw new IllegalArgumentException("Wrong format of phone number");
        }
    }

    private void isIdentificationDocumentValid(String type){
        for (IdentificationDocument i : identificationDocuments){
            if(i.type.equals(type)){
                throw new IllegalArgumentException("Document with type: " + type + " already exists");
            }
        }
    }

    @Override
    public String toString(){
        if (middleName != null){
            return firstName + " " + middleName + " " + lastName;
        }
        return firstName + " " + lastName;
    }

    public class IdentificationDocument {
        private final String type;
        private String serialNum;
        private LocalDateTime expiryDate;
        private static Map<String, String> typesSerialNum = new HashMap<>();

        public IdentificationDocument(String type, String serialNum, LocalDateTime expiryDate) {
            //validation
            isTypeValid(type);
            isSerialNumValid(serialNum, type);
            isExpiryDateValid(expiryDate);

            //add object
            this.type = type;
            this.serialNum = serialNum;
            this.expiryDate = expiryDate;
        }

        //getters and setters
        public String getType() {
            return type;
        }

        public String getSerialNum() {
            return serialNum;
        }
        public void setSerialNum(String serialNum) {
            isSerialNumValid(serialNum, type);
            this.serialNum = serialNum;
        }

        public LocalDateTime getExpiryDate() {
            return expiryDate;
        }
        public void setExpiryDate(LocalDateTime expiryDate) {
            isExpiryDateValid(expiryDate);
            this.expiryDate = expiryDate;
        }

        public Person getPerson(){
            return Person.this;
        }

        public static Map<String, String> getTypesSerialNum(){
            return new HashMap<>(typesSerialNum);
        }
        public static void addTypesSerialNum(String type, String pattern){
            isTypeAndPatternValid(type, pattern);
            typesSerialNum.put(type, pattern);
        }

        //validation
        private void isTypeValid(String type){
            if (type == null || !typesSerialNum.containsKey(type)){
                throw new IllegalArgumentException(type + " doesn't exist in system or is null");
            }
        }
        private void isSerialNumValid(String serialNum, String type){
            if(!serialNum.matches(typesSerialNum.get(type))){
                throw new IllegalArgumentException("Serial number: " + serialNum + " is incorrect");
            }
        }
        private void isExpiryDateValid(LocalDateTime date){
            if(date == null || date.isBefore(LocalDateTime.now())){
                throw new IllegalArgumentException("Document can't be expired or null");
            }
        }

        private static void isTypeAndPatternValid(String type, String pattern){
            if(type == null || pattern == null){
                throw new IllegalArgumentException(type + " and " + pattern + " can't be null");
            }
        }

        @Override
        public String toString(){
            return Person.this + ", " + type + "-" + serialNum;
        }
    }
}