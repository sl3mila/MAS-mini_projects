import java.time.LocalDateTime;

public class DoctorsInWards {
    private Doctor worker;
    private Ward worksAt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public DoctorsInWards(Doctor worker, Ward worksAt, LocalDateTime startDate) {
        isWorkerValid(worker);
        isWorksAtValid(worksAt);
        isStartDateValid(startDate);

        this.worker = worker;
        this.worksAt = worksAt;
        this.startDate = startDate;
    }

    public Doctor getWorker() {
        return worker;
    }
    public void setWorker(Doctor worker) {
        isWorkerValid(worker);
        this.worker = worker;
    }

    public Ward getWorksAt() {
        return worksAt;
    }
    public void setWorksAt(Ward worksAt) {
        isWorksAtValid(worksAt);
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

    private static void isWorkerValid(Doctor worker){
        if(!Doctor.getDoctors().contains(worker)){
            throw new IllegalArgumentException("doctor deasn't exist");
        }
    }
    private static void isWorksAtValid(Ward worksAt){
        if(!Ward.getWards().contains(worksAt)){
            throw new IllegalArgumentException("ward deasn't exist");
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
