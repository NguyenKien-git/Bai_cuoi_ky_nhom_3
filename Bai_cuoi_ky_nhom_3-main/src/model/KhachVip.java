package model;

public class KhachVIP extends KhachHang {

    public KhachVIP(String maKH, String hoTen,
                    String soDienThoai, String email) {
        super(maKH, hoTen, soDienThoai, email);
    }

    @Override
    public double tinhGiaVe(double giaGoc) {
        return giaGoc * 0.8;
    }
}