package xor;

import java.util.Collections;
import java.util.List;

public class PrivateHospital {
    private String name;
    private String owner;
    private List<Doctor> workers;
    private static List<PrivateHospital> privateHospitals;

    public PrivateHospital(String name, String owner) {
        isNameValid(name);
        String[] nameSurname = owner.split(" ");
        isNameValid(nameSurname[0]);
        isNameValid(nameSurname[1]);

        this.name = name;
        this.owner = owner;

        privateHospitals.add(this);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        isNameValid(name);
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        String[] nameSurname = owner.split(" ");
        isNameValid(nameSurname[0]);
        isNameValid(nameSurname[1]);
        this.owner = owner;
    }

    public List<Doctor> getWorkers() {
        return Collections.unmodifiableList(workers);
    }
    public void addWorker(Doctor doctor){
        isDoctorValid(doctor);
        PublicHospital h = findInPublicHospital(doctor);
        if(h == null){
            workers.add(doctor);
        }else{
            h.removeWorker(doctor);
            workers.add(doctor);
        }
    }
    public void removeWorker(Doctor doctor){
        workers.remove(doctor);
    }

    private PublicHospital findInPublicHospital(Doctor doctor){
        for(PublicHospital h : PublicHospital.getPublicHospitals()){
            for(Doctor d : h.getWorkers()){
                if(d.equals(doctor)){
                    return h;
                }
            }
        }
        return null;
    }

    public static List<PrivateHospital> getPrivateHospitals() {
        return Collections.unmodifiableList(privateHospitals);
    }

    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }

    private static void isDoctorValid(Doctor doctor){
        if(!Doctor.getDoctors().contains(doctor)){
            throw new IllegalArgumentException("doctor doesn't exist");
        }
    }
}
