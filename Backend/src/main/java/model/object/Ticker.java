package model.object;

public class Ticker {
    private String idTicker;
    private Customer customer;
    private Trip trip;
    private Seat seat;
    private boolean payment;

    public Ticker(String idTicker, Customer customer, Trip trip, Seat seat) {
        this.idTicker = idTicker;
        this.customer  = customer;
        this.trip = trip;
        this.seat = seat;
        this.payment = false;
    }

    //Getter
    public String idTicker() {
        return idTicker;
    }
    public Customer customer() {
        return customer;
    }
    public Trip trip() {
        return trip;
    }
    public Seat seat() {
        return seat;
    }
    public boolean payment() {
        return payment;
    }

    //Setter
    public void setIdTicker(String idTicker) {
        this.idTicker = idTicker;
    }
    public void setUser(Customer customer) {
        this.customer = customer;
    }
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public void InTicker() {
        System.out.println("================================ TICKET INFO =================================");
        System.out.print("Customer: ");
        customer.In();
        trip.InTrip();
        seat.InSeat();
        System.out.printf("|| Total Price: %,.0f VND                                                              ||%n", customer.getTickerPrice());
        System.out.println("================================================================================");
    }
}
