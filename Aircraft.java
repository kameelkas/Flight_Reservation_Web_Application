public class Aircraft {
    private int aircraftID;
    private String model;
    private int seatCapacity;
    private boolean companyOwned;

    public Aircraft(int aID, String model, int capacity, boolean owned){
        this.aircraftID = aID;
        this.model = model;
        this.seatCapacity = capacity;
        this.companyOwned = owned;
    }

}
