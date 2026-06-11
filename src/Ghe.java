package model;

public class Ghe {
    private String maGhe;
    private String soGhe;
    private LoaiGhe loaiGhe;
    private TrangThaiGhe trangThai;

    // Constructor
    public Ghe(String maGhe, String soGhe, LoaiGhe loaiGhe) {
        this.maGhe = maGhe;
        this.soGhe = soGhe;
        this.loaiGhe = loaiGhe;
        this.trangThai = TrangThaiGhe.CON_TRONG; // Mặc định còn trống
    }

    // Getter - Setter
    public String getMaGhe() {
        return maGhe;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public LoaiGhe getLoaiGhe() {
        return loaiGhe;
    }

    public TrangThaiGhe getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiGhe trangThai) {
        this.trangThai = trangThai;
    }

    // Phương thức kiểm tra
    public boolean conTrong() {
        return trangThai == TrangThaiGhe.CON_TRONG;
    }

    public void datGhe() {
        this.trangThai = TrangThaiGhe.DA_DAT;
    }

    public int getPhuPhi() {
        return loaiGhe == LoaiGhe.VIP ? 50000 : 0; // Phụ phí ghế VIP
    }

    @Override
    public String toString() {
        return "Ghế " + soGhe + " | Loại: " + loaiGhe + " | Trạng thái: " + trangThai;
    }
}