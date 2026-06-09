package model;

public class KhachThanhVien extends KhachHang {

    public KhachThanhVien(String maKH, String hoTen,
                          String soDienThoai, String email) {
        super(maKH, hoTen, soDienThoai, email);
    }

    @Override
    public double tinhGiaVe(double giaGoc) {
        return giaGoc * 0.9;
    }
}