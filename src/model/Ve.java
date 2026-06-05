package model;

public class Ve implements Ma_ve, Kiem_tra_ghe, Kiem_tra_chuyen_xe, Gia_ve{
    private String ma_Ve;
    private Khach_Hang khach_Hang;
    private Chuyen_Xe chuyen_Xe;
    private Ghe ghe;
    private double gia_Ve_Cuoi_Cung;
    private Thanh_Toan thanh_Toan;

    public Ve(String ma_Ve, Khach_Hang khach_Hang, Chuyen_Xe chuyen_Xe, Ghe ghe, Thanh_Toan thanh_Toan) {
        this.ma_Ve = ma_Ve;
        this.khach_Hang  = khach_Hang;
        this.chuyen_Xe  = chuyen_Xe;
        this.ghe = ghe;
        this.thanh_Toan = thanh_Toan;
    }

    public String getMave() {
        return ma_Ve;
    }

    public Khach_Hang getKhachHang() {
        return khach_Hang;
    }

    public Chuyen_Xe getChuyenXe() {
        return chuyen_Xe;
    }

    public Ghe getGhe() {
        return ghe;
    }

    public thanh_Toan Thanh_toan() {
        return Thanh_toan;
    }
}