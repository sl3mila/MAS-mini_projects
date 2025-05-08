import java.util.*;

public class Ward {
    //Ekstensja
    private String name;
    private static int maxWorkers = 20;
    private static int minWorkers = 1;
    private static final List<Ward> wards = new ArrayList<>();

    public Ward(String name){

        //validation
        isNameValid(name);

        //create object
        this.name = name;

        //add to extension
        wards.add(this);
    }

    //getters and setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        isNameValid(name);
        this.name = name;
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
        if(num == null){
            throw new IllegalArgumentException("License can't be null and has to be registered in ward");
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
