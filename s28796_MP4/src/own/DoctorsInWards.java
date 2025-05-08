package own;

import java.time.LocalDateTime;

public class DoctorsInWards {
    private String worker;
    private String worksAt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public DoctorsInWards(String worker, String worksAt, LocalDateTime startDate) {
        isNameValid(worker);
        isNameValid(worksAt);
        isStartDateValid(startDate);

        this.worker = worker;
        this.worksAt = worksAt;
        this.startDate = startDate;
    }

    public String getWorker() {
        return worker;
    }
    public void setWorker(String worker) {
        isNameValid(worker);
        this.worker = worker;
    }

    public String getWorksAt() {
        return worksAt;
    }
    public void setWorksAt(String worksAt) {
        isNameValid(worksAt);
        this.worksAt = worksAt;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        isStartDateValid(startDate);
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        isEndDateValid(endDate, startDate);
        this.endDate = endDate;
    }

    protected void isNameValid(String name){
        String[] nameSurname = name.split(" ");
        if(nameSurname[0] == null || !(nameSurname[0].matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
        if(nameSurname[1] == null || !(nameSurname[1].matches("[A-Z][a-z]+"))){
            throw new IllegalArgumentException("first name can't be null or invalid");
        }
    }

    private static void isStartDateValid(LocalDateTime date){
        if(date.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("date can't be after current date");
        }
    }

    private static void isEndDateValid(LocalDateTime date, LocalDateTime startDate){
        if(date.isBefore(startDate)){
            throw new IllegalArgumentException("date can't be befor start date");
        }
    }
}
