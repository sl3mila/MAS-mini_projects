import java.util.*;

public class Ward {
//Ekstensja
    private static int lastId = 0;
    private String name;
    private char building;
    private String address;
    private static int maxWorkers = 20;
    private static int minWorkers = 1;
    private final Map<String,Doctor> workers;
    private static final List<Ward> wards = new ArrayList<>();

    public Ward(String name, char building, String address){

        //validation
        isNameValid(name);
        isBuildingValid(building);
        isAddressValid(address);

        //create object
        this.name = name;
        this.building = building;
        this.address = address;
        this.workers = new HashMap<>();

        //add to extension
        wards.add(this);
    }

    //objects writer and reader
    /*protected void write(DataOutputStream stream) throws IOException{
        stream.writeUTF(name);
        stream.writeChar(building);

        stream.writeInt(doctors.size());
        for (Doctor doctor : doctors){
            doctor.write(stream);
        }
    }
    protected void read(DataInputStream stream) throws IOException{
        name = stream.readUTF();
        building = stream.readChar();

        int roomCount = stream.readInt();
        doctors = new ArrayList<>();
        Doctor doctor;
        for (int i = 0; i < roomCount; i++){
            doctor = new Doctor("00000000000", "Na", "Na",
                    LocalDate.now().minusYears(21), "0000000", LocalDate.now(),
                    "000 000 000");   //placeholders
            doctor.read(stream);
            doctors.add(doctor);

        }
    }

    //extension writer and reader
    public static void writeExtent(DataOutputStream stream) throws IOException{
        stream.writeInt(wards.size());
        for(Ward ward : wards){
            ward.write(stream);
        }
    }
    public static void readExtent(DataInputStream stream) throws IOException{
        Ward ward;

        int wardsCount = stream.readInt();
        wards.clear();
        for (int i = 0; i < wardsCount; i++){
            ward = new Ward();
            ward.read(stream);
            wards.add(ward);
        }
    }*/

    //getters and setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        isNameValid(name);
        this.name = name;
    }

    public char getBuilding(){
        return building;
    }
    public void setBuilding(char building){
        isBuildingValid(building);
        this.building = building;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        isAddressValid(address);
        this.address = address;
    }

    public static int getMaxWorkers(){
        return maxWorkers;
    }
    public static void setMaxWorkers(int maxNumOfDoctors){
        maxWorkers = maxNumOfDoctors;
    }

    public static int getMinWorkers(){
        return minWorkers;
    }
    public static void setMinWorkers(int minNumOfDoctors){
        minWorkers = minNumOfDoctors;
    }

    public Map<String, Doctor> getWorkers(){
        return new HashMap<>(workers);
    }
    public void addWorkers(Doctor doctor){
        isWorkerValid(doctor);
        if (!(workers.size() < maxWorkers)) {
            throw new IllegalStateException( this + " is already full, can't add " +
                    doctor);
        }
        if (doctor.getWorksAt() != this) {
            doctor.setWorksAt(this);
        }
        workers.put(doctor.getLicenseNum(), doctor);
        System.out.println("Added " + doctor + " to " + this);
    }
    public Doctor findWorker(String licenseNum){
        isLicenseNumValid(licenseNum);
        return workers.get(licenseNum);
    }
    public void removeWorker(Doctor doctor){
        if(workers.containsKey(doctor.getLicenseNum())){
            if (!(workers.size() > minWorkers)) {
                throw new IllegalStateException( doctor + " is last doctor in " + this + " , can't remove");
            }
            workers.remove(doctor.getLicenseNum());
            System.out.println("Removed " + doctor + " from " + this);
        }else {
            throw new IllegalArgumentException(doctor + " is not working in ward " + this);
        }
    }
    public static List<Ward> getWards(){
        return new ArrayList<>(wards);
    }

    //validation
    private void isNameValid(String name){
        boolean exists = false;
        for (Ward w : wards){
            if(name.equals(w.getName())){
                exists = true;
                break;
            }
        }
        if (exists){
            throw new IllegalArgumentException("One of existing wards alredy has name: " + name);
        } else if (!(name != null && name.matches("[A-Z][a-z]+"))) {
            throw new IllegalArgumentException("Ward name is null or incorrect");
        }
    }
    private static void isBuildingValid(char building){
        if(!(String.valueOf(building).matches("[A-Z]"))){
            throw new IllegalArgumentException("Building has to be a capital letter");
        }
    }

    private void isAddressValid(String address){
        if(!(address != null && address.matches("[A-Z][a-z]+, [A-Z][a-z]+, \\d{2}-\\d{3}"))){   //City, Street, post number
            throw new IllegalArgumentException("Addres format: [city], [street], [post number]");
        }
    }
    private void isWorkerValid(Doctor worker){
        if(Doctor.getDoctors().contains(worker)){
            throw new IllegalArgumentException(worker + " doesnt exist in system");
        }
    }

    private void isLicenseNumValid(String num){
        if(num == null || !workers.containsKey(num)){
            throw new IllegalArgumentException("License can't be null and has to be registered in ward");
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
