package model;
import java.util.ArrayList;
import java.util.List;

public class ChuyenXe {
    private String maChuyen;
    private String diemDi;
    private String diemDen;
    private String thoiGianKhoiHanh;
    private String bienSoXe;
    private List<Ghe> danhSachGhe;

    public ChuyenXe(String maChuyen, String diemDi, String diemDen,
                    String thoiGianKhoiHanh, String bienSoXe, int tongSoGhe) {
        this.maChuyen = maChuyen;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.thoiGianKhoiHanh = thoiGianKhoiHanh;
        this.bienSoXe = bienSoXe;
        this.danhSachGhe = new ArrayList<>();
        taoDanhSachGhe(tongSoGhe);
    }

    private void taoDanhSachGhe(int tongSoGhe) {
        for (int i = 1; i <= tongSoGhe; i++) {
            String soGheStr = "A" + String.format("%02d", i);
            LoaiGhe loai = (i <= 5) ? LoaiGhe.VIP : LoaiGhe.THUONG;
            String maGhe = maChuyen + "-" + soGheStr;

            danhSachGhe.add(new Ghe(maGhe, soGheStr, loai));
        }
    }

    public List<Ghe> getGheConTrong() {
        List<Ghe> ketQua = new ArrayList<>();
        for (Ghe g : danhSachGhe) {
            if (g.conTrong()) {
                ketQua.add(g);
            }
        }
        return ketQua;
    }

    public Ghe timGhe(String soGhe) {
        for (Ghe g : danhSachGhe) {
            if (g.getSoGhe().equalsIgnoreCase(soGhe)) {
                return g;
            }
        }
        return null;
    }

    public String getMaChuyen() {
        return maChuyen;
    }

    public List<Ghe> getDanhSachGhe() {
        return danhSachGhe;
    }

    @Override
    public String toString() {
        return maChuyen + " | " + diemDi + " → " + diemDen + " | " + thoiGianKhoiHanh;
    }
}