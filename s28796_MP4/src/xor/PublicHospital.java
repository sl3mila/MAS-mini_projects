package xor;

import java.util.Collections;
import java.util.List;

public class PublicHospital {
    private String name;
    private String foundingBody;
    private List<Doctor> workers;
    private static List<PublicHospital> publicHospitals;

    public PublicHospital(String name, String foundingbody) {
        isNameValid(name);
        isFoundingBodyValid(foundingBody);

        this.name = name;
        this.foundingBody = foundingbody;

        publicHospitals.add(this);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        isNameValid(name);
        this.name = name;
    }

    public String getFoundingBody() {
        return foundingBody;
    }
    public void setFoundingBody(String foundingBody) {
        isFoundingBodyValid(foundingBody);
        this.foundingBody = foundingBody;
    }

    public List<Doctor> getWorkers() {
        return Collections.unmodifiableList(workers);
    }
    public void addWorker(Doctor doctor){
        isDoctorValid(doctor);
        PrivateHospital h = findInPrivateHospital(doctor);
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

    private PrivateHospital findInPrivateHospital(Doctor doctor){
        for(PrivateHospital h : PrivateHospital.getPrivateHospitals()){
            for(Doctor d : h.getWorkers()){
                if(d.equals(doctor)){
                    return h;
                }
            }
        }
        return null;
    }

    public static List<PublicHospital> getPublicHospitals() {
        return Collections.unmodifiableList(publicHospitals);
    }


    protected void isNameValid(String name){
        if(name == null || !(name.matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }

    private void isFoundingBodyValid(String foundingBody){
        if(foundingBody == null || !(foundingBody.matches("[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ ]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }

    private static void isDoctorValid(Doctor doctor){
        if(!Doctor.getDoctors().contains(doctor)){
            throw new IllegalArgumentException("doctor doesn't exist");
        }
    }
}
