package subset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ward {
    //Ekstensja
    private String name;
    private static int maxWorkers = 20;
    private static int minWorkers = 1;
    private List<Doctor> workers;
    private List<Doctor> directors;
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

    public List<Doctor> getWorker() {
        return Collections.unmodifiableList(workers);
    }

    public void addWorker(Doctor doctor) {
        isDoctorValid(doctor);
        this.workers.add(doctor);
        if(!doctor.getWorksAt().contains(this)){
            doctor.addWorksAt(this);
        }
    }
    public void removeWorker(Doctor doctor) {
        isDoctorValid(doctor);
        this.workers.remove(doctor);
        if(directors.contains(doctor)) {
            this.removeDirectors(doctor);
        }
        if(doctor.getWorksAt().contains(this)){
            doctor.removeWorksAt(this);
        }
    }

    public static List<Ward> getWards(){
        return new ArrayList<>(wards);
    }

    public List<Doctor> getDirectors() {
        return Collections.unmodifiableList(workers);
    }

    public void addDirects(Doctor doctor) {
        isDoctorValid(doctor);
        isDirectorValid(doctor);
        this.workers.add(doctor);
        if(!doctor.getWorksAt().contains(this)){
            doctor.addWorksAt(this);
        }
    }
    public void removeDirectors(Doctor doctor) {
        isDoctorValid(doctor);
        this.workers.remove(doctor);
        if(doctor.getWorksAt().contains(this)){
            doctor.removeWorksAt(this);
        }
    }

    private void isDirectorValid(Doctor doctor){
        if(!workers.contains(doctor)){
            throw new IllegalArgumentException("for doctor to direct ward they have to be working in it");
        }
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

    private static void isDoctorValid(Doctor doctor){
        if(!Doctor.getDoctors().contains(doctor)){
            throw new IllegalArgumentException("doctor doesn't exist");
        }
    }

    @Override
    public String toString(){
        return name;
    }
}
