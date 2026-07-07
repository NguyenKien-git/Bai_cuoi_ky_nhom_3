package model.object;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private String idTrip;
    private String fromTrip;
    private String toTrip;
    private int mm,hh;
    private String bienSo;
    private int totalSeat;
    private List<Seat> listSeat;

    public Trip(String idTrip, String fromTrip, String toTrip, int mm, int hh, String bienSo, int totalSeat) {
        this.idTrip = idTrip;
        this.fromTrip = fromTrip;
        this.toTrip = toTrip;
        this.mm = mm;
        this.hh = hh;
        this.bienSo = bienSo;
        this.totalSeat = totalSeat;
        this.listSeat = new ArrayList<>();

        for (int i = 1; i <= totalSeat; i++) {
            listSeat.add(new Seat(i));
        }
    }

    //Getter
    public String idTrip() {
        return idTrip;
    }
    public String fromTrip() {
        return fromTrip;
    }
    public String toTrip() {
        return toTrip;
    }
    public int mm() {
        return mm;
    }
    public int hh() {
        return hh;
    }
    public String bienSo() {
        return bienSo;
    }
    public int totalSeat() {
        return totalSeat;
    }
    public List<Seat> listSeat() {
        return listSeat;
    }

    //Setter
    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }
    public void setFromTrip(String fromTrip) {
        this.fromTrip = fromTrip;
    }
    public void setToTrip(String toTrip) {
        this.toTrip = toTrip;
    }
    public void setMm(int mm) {
        this.mm = mm;
    }
    public void setHh(int hh) {
        this.hh = hh;
    }
    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }
    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }

    public void InTrip() {
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-15s | %-8s | %-10s |%n", "ID Trip", "From", "To", "Time", "Total Seat");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-15s | %-8s | %-10s |%n", idTrip, fromTrip, toTrip, mm + ":" + hh, totalSeat);
        System.out.println("-----------------------------------------------------------------------------------------------");
    }

    public static void InAllTrips(List<Trip> trips) {
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-15s | %-8s | %-10s |%n",
                "ID Trip", "From", "To", "Time", "Total Seat");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (Trip trip : trips) {
            System.out.printf("| %-10s | %-15s | %-15s | %-8s | %-10s |%n",
                    trip.idTrip(), trip.fromTrip(), trip.toTrip(), trip.mm() + ":" + trip.hh(), trip.totalSeat());
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
    }
}
