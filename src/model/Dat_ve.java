package model;

public class Booking {
    private String ma_Dat_Ve;
    private Ve ve;
    private int thoi_Gian_Dat_Ve;
    private String trang_Thai_Dat_Ve;

    public Booking(String ma_Dat_Ve, Ve ve, int thoi_Gian_Dat_Ve, String trang_Thai_Dat_Ve) {
        this.ma_Dat_Ve = ma_Dat_Ve;
        this.ve = ve;
        this.thoi_Gian_Dat_Ve = thoi_Gian_Dat_Ve;
        this.status = "Chua thanh toan";
    }

    public String getMa_Dat_Ve(){
        return ma_Dat_Ve;
    }

    public Ticket getVe(){
        return ve;
    }

    public String getThoi_Gian_Dat_Ve(){
        return thoi_Gian_Dat_Ve;
    }

    public String getTrang_Thai_Dat_Ve(){
        return trang_Thai_Dat_Ve;
    }
    public void setTrang_Thai_Dat_Ve(String trang_Thai_Dat_Ve) {
        this.trang_Thai_Dat_Ve = trang_Thai_Dat_Ve;
    }
}