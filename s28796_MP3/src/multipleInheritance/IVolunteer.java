package multipleInheritance;

public interface IVolunteer {
    String getOrganization();
    int getHoursVolunteering();
    void setOrganization(String organization);
    public void setHoursVolunteering(int hoursVolunteering);
    int addHours(int amount);

}
