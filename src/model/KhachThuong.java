package model;

public class KhachThuong extends KhachHang {

    public KhachThuong(String maKH, String hoTen,
                       String soDienThoai, String email) {
        super(maKH, hoTen, soDienThoai, email);
    }

    @Override
    public double tinhGiaVe(double giaGoc) {
        return giaGoc;
    }
}