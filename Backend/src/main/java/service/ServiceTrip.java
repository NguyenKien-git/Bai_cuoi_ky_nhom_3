package service;

import model.object.Trip;
import java.util.ArrayList;
import java.util.List;

public class ServiceTrip {
    private List<Trip> trips = new ArrayList<>();

    public void createTrip(String fromTrip, String toTrip, int mm, int hh, String bienSo, int totalSeat) {
        String idTrip = "Trip" + (trips.size() + 1);
        Trip trip = new Trip(idTrip, fromTrip, toTrip, mm, hh, bienSo, totalSeat);
        trips.add(trip);
    }

    //Tim Trip theo idTrip
    public Trip findTripById(String idTrip) {
        for (Trip trip : trips) {
            if (trip.idTrip().equals(idTrip)) {
                return trip;
            }
        }
        return null;
    }

    //Tim Trip theo fromTrip và toTrip
    public Trip findTripByRoute(String fromTrip, String toTrip) {
        for (Trip trip : trips) {
            if (trip.fromTrip().equals(fromTrip) && trip.toTrip().equals(toTrip)) {
                return trip;
            }
        }
        return null;
    }

    //Xoa Trip
    public boolean deleteTrip(Trip trip) {
        if (trip == null) {
            return false;
        }
        return trips.remove(trip);
    }
    public List<Trip> getTrips() {
        return trips;
    }
}
