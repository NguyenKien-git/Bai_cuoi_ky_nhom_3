package Thanh_toan;

public class Chuyen_khoan implements PaymentMethod {
    private String tai_Khoan_Ngan_Hang;

    public Chuyen_khoan(String tai_Khoan_Ngan_Hang) {
        this.tai_Khoan_Ngan_Hang = tai_Khoan_Ngan_Hang;
    }

    public String getTai_Khoan_Ngan_Hang() {
        return tai_Khoan_Ngan_Hang;
    }

    @Override
    public boolean thanh_Toan(double tong_Tien) {
        System.out.println("Chuyen khoan " + tong_Tien + " VND den tai khoan: " + bankAccount);ng)
        System.out.println("Chuyen khoan thanh cong!");
        return true;
    }

    @Override
    public String getTen_Phuong_Thuc() {
        return "Chuyen khoan";
    }
}