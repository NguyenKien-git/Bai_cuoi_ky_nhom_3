package model;

public class Thanh_Toan{
    private String ma_Thanh_Toan;
    private Ve ve;
    private String phuong_Thuc_Thanh_Toan;
    private double tong_Tien;
    private boolean ket_Qua_Thanh_Toan;

    public Payment(String ma_Thanh_Toan, Ve ve, String phuong_Thuc_Thanh_Toan, double tong_Tien, boolean ket_Qua_Thanh_Toan) {
        this.ma_Thanh_Toan = ma_Thanh_Toan;
        this.ve = ve;
        this.phuong_Thuc_Thanh_Toan = phuong_Thuc_Thanh_Toan;
        this.tong_Tien = tong_Tien;
        this.ket_Qua_Thanh_Toan = false;
    }

    public String  getMa_Thanh_Toan() {
        return ma_Thanh_Toan;
    }

    public Ticket  getVe() {
        return ve;
    }

    public String  getPhuong_Thuc_Thanh_Toan() {
        return phuong_Thuc_Thanh_Toan;
    }

    public double  getTong_Tien() {
        return tong_Tien;
    }

    public boolean getKet_Qua_Thanh_Toan() {
        return ket_Qua_Thanh_Toan;
    }
    public void setKet_Qua_Thanh_Toan(boolean ket_Qua_Thanh_Toan) {
        this.ket_Qua_Thanh_Toan = ket_Qua_Thanh_Toan;
    }
}