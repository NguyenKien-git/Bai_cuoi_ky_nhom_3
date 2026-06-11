package model;

import java.util.ArrayList;
import java.util.List;

public class BusTrip {

    private String maChuyenXe;
    private String diemDi;
    private String diemDen;
    private String thoiGianKhoiHanh;
    private String bienSoXe;
    private int tongSoGhe;
    private List<Seat> danhSachGhe; // Dùng Collection List để quản lý ghế


    public BusTrip(String maChuyenXe, String diemDi, String diemDen, String thoiGianKhoiHanh, String bienSoXe, int tongSoGhe) {
        this.maChuyenXe = maChuyenXe;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.bienSoXe = bienSoXe;
        this.tongSoGhe = tongSoGhe;
        this.danhSachGhe = new ArrayList<>();


        tuDongSinhDanhSachGhe();
    }


    private void tuDongSinhDanhSachGhe() {
        for (int i = 1; i <= tongSoGhe; i++) {

            String loaiGhe = (i <= 5) ? "Ghế VIP" : "Ghế thường";
            String maGhe = maChuyenXe + "-S" + i;
            danhSachGhe.add(new Seat(maGhe, i, loaiGhe));
        }
    }


    public String getMaChuyenXe() { return maChuyenXe; }
    public String getDiemDi() { return diemDi; }
    public String getDiemDen() { return diemDen; }
    public String getThoiGianKhoiHanh() { return thoiGianKhoiHanh; }
    public String getBienSoXe() { return bienSoXe; }
    public int getTongSoGhe() { return tongSoGhe; }
    public List<Seat> getDanhSachGhe() { return danhSachGhe; }
}