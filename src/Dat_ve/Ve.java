package Dat_ve;

public class Ve{
    private String ma_Ve;
    private Khach_Hang khach_Hang;
    private Chuyen_Xe chuyen_Xe;
    private Ghe ghe;
    private double gia_Ve_Cuoi_Cung;
    private Thanh_Toan thanh_Toan;

    public Ve(String ma_Ve, Khach_Hang khach_Hang, Chuyen_Xe chuyen_Xe, Ghe ghe, Thanh_Toan thanh_Toan) {
        this.ma_Ve   = ma_Ve;
        this.khach_Hang   = khach_Hang;
        this.chuyen_Xe    = chuyen_Xe;
        this.ghe       = ghe;
        this.thanh_Toan     = thanh_Toan;
    }

    public String getMave() {
        return ma_Ve;
    }

    public Customer getKhachHang() {
        return customer;
    }

    public BusTrip getBusTrip() {
        return busTrip;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}