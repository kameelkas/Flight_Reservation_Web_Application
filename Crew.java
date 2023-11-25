public class Crew {
    private int crewID;
    private String name;
    private String role;
    private Flight workingFLight;

    public Crew(int cID, String name, String role, Flight flight){
        this.crewID = cID;
        this.name = name;
        this.role = role;
        this.workingFLight = flight;
    }
}
