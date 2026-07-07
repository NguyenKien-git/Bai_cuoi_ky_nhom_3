package model.object;

import model.inheritance.User;

public class Payment {
    private User user;
    private Trip trip;
    private Seat seat;
    private Ticker ticker;
    private int typePayment;

    public Payment(User user, Trip trip, Seat seat, Ticker ticker, int typePayment) {
        this.user = user;
        this.trip = trip;
        this.seat = seat;
        this.ticker = ticker;
        this.typePayment = typePayment;
    }

    //Getter
    public User user() {
        return user;
    }
    public Trip trip() {
        return trip;
    }
    public Seat seat() {
        return seat;
    }
    public Ticker ticker() {
        return ticker;
    }
    public int typePayment() {
        return typePayment;
    }

    //Setter
    public void setUser(User user) {
        this.user = user;
    }
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
    public void setTypePayment(int typePayment) {
        this.typePayment = typePayment;
    }
}
