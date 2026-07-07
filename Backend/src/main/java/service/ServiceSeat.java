package service;

import model.object.Customer;
import model.object.VIPUser;
import model.object.Seat;
import java.util.ArrayList;
import java.util.List;

public class ServiceSeat {
    private List<Seat> seats;
    public ServiceSeat() {
        this.seats = new ArrayList<>();
    }

    public ServiceSeat(List<Seat> seats) {
        this.seats = seats;
    }

    //Them mot user vao mot seat cu the
    public boolean addUserToSeat(Seat seat, Customer user) {
        if (seat == null || user == null) {
            return false;
        }
        if (seat.statusSeat()) {
            return false;
        }
        if (user instanceof VIPUser) {
            seat.setTypeSeat(true);
        }
        seat.setCustomer(user);
        seat.setStatusSeat(true);
        return true;
    }

    //Xoa user khoi mot seat
    public boolean removeUserFromSeat(Seat seat) {
        if (seat == null) {
            return false;
        }
        if (!seat.statusSeat()) {
            return false;
        }
        seat.setCustomer(null);
        seat.setStatusSeat(false);
        seat.setTypeSeat(false);

        return true;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}
