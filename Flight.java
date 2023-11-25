public class Flight {
    private int flightID;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String departureCity;
    private String departureCountry;
    private String departureAirport;
    private String destinationCity;
    private String destinationCountry;
    private String destinationAirport;
    private Aircraft plane;

    public Flight(int fID, String depDate, String depTime, String arrDate, String ArrTime, String depCity,
            String depCntry, String depAirport, String destCity, String destCntry, String destAirport, Aircraft plane) {
        this.flightID = fID;
        this.departureDate = depDate;
        this.departureTime = depTime;
        this.arrivalDate = arrDate;
        this.arrivalTime = ArrTime;
        this.departureCity = depCity;
        this.departureCountry = depCntry;
        this.departureAirport = depAirport;
        this.destinationCity = destCity;
        this.destinationCountry = destCntry;
        this.destinationAirport = destAirport;
        this.plane = plane;
    }

    public int getFlightID() {
        return this.flightID;
    }

}
