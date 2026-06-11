package model;

public class Seat {
    private String maGhe;
    private int soGhe;
    private String loaiGhe;
    private boolean daDat;


    public Seat(String maGhe, int soGhe, String loaiGhe) {
        this.maGhe = maGhe;
        this.soGhe = soGhe;
        this.loaiGhe = loaiGhe;
        this.daDat = false;
    }


    public String getMaGhe() { return maGhe; }
    public int getSoGhe() { return soGhe; }
    public String getLoaiGhe() { return loaiGhe; }
    public boolean isDaDat() { return daDat; }
    public void setDaDat(boolean daDat) { this.daDat = daDat; }
}