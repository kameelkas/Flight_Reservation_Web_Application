public class Ticket {
    private int ticketID;
    private float price;
    private Customer passenger;
    private Flight bookedFlight;
    private Seat chosenSeat;

    public Ticket(int tID, float price, Customer customer, Flight flight, Seat seat){
        this.ticketID = tID;
        this.price = price;
        this.passenger = customer;
        this.bookedFlight = flight;
        this.chosenSeat = seat;
    }

    public int getTicketID(){
        return this.ticketID;
    }
}
