package multipleInheritance;

public class RescueWorker extends StaffMember implements IVolunteer{
    private String organization;
    private int hoursVolunteering;

    public RescueWorker(Person prevPerson, float wage, String organization, int hoursVolunteering) {
        super(prevPerson, wage);
        isNameValid(organization);
        isHoursValid(hoursVolunteering);

        this.organization = organization;
        this.hoursVolunteering = hoursVolunteering;
    }

    //getters and setters
    @Override
    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        isNameValid(organization);
        this.organization = organization;
    }

    @Override
    public int getHoursVolunteering() {
        return hoursVolunteering;
    }
    public void setHoursVolunteering(int hoursVolunteering) {
        isHoursValid(hoursVolunteering);
        this.hoursVolunteering = hoursVolunteering;
    }

    //validator
    private void isHoursValid(float hours){
        if (hours < 0){
            throw new IllegalArgumentException("Hours can't be smaller than 0");
        }
    }

    @Override
    public int addHours(int amount) {
        if(amount > 0) {
            hoursVolunteering += amount;
            return hoursVolunteering;
        }
        throw new IllegalArgumentException("Added hours have to be more than 0");
    }
}
