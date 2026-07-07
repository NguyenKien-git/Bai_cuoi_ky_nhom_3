package model.object;

import java.util.List;

public class Seat {
    private String idSeat;
    private int slotSeat;
    private boolean typeSeat;
    private boolean statusSeat;
    private Customer customer;

    public Seat(int slotSeat) {
        this.idSeat = "S" + slotSeat;
        this.typeSeat = false;
        this.slotSeat = slotSeat;
        this.statusSeat = false;
    }

    //Getter
    public String idSeat() {
        return idSeat;
    }
    public int slotSeat() {
        return slotSeat;
    }
    public boolean typeSeat() {
        return typeSeat;
    }
    public boolean statusSeat() {
        return statusSeat;
    }
    public Customer getCustomer() {
        return customer;
    }

    //Setter
    public void setIdSeat(String idSeat) {
        this.idSeat = idSeat;
    }
    public void setSlotSeat(int slotSeat) {
        this.slotSeat = slotSeat;
    }
    public void setTypeSeat(boolean typeSeat) {
        this.typeSeat = typeSeat;
    }
    public void setStatusSeat(boolean statusSeat) {
        this.statusSeat = statusSeat;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void InSeat(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-12s | %-15s |%n",
                "ID Seat", "Slot", "Type", "Status");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10d | %-12s | %-15s |%n",
                idSeat, slotSeat,
                typeSeat ? "VIP" : "Normal",
                statusSeat ? "Da dat" : "Trong");
        System.out.println("---------------------------------------------------------------------------");
    }

    public static void InAllSeats(List<Seat> seats){
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-12s | %-15s |%n",
                "ID Seat", "Slot", "Type", "Status");
        System.out.println("---------------------------------------------------------------------------");
        for (Seat seat : seats) {
            System.out.printf("| %-10s | %-10d | %-12s | %-15s |%n",
                    seat.idSeat(), seat.slotSeat(),
                    seat.typeSeat() ? "VIP" : "Normal",
                    seat.statusSeat() ? "Da dat" : "Trong");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

}
