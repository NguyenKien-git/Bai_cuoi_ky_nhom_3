package model;

public abstract class KhachHang implements TinhGiaVe {
    private String maKH;
    private String hoTen;
    private String soDienThoai;
    private String email;

    public KhachHang(String maKH, String hoTen, String soDienThoai, String email) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void hienThi() {
        System.out.println("Ma KH: " + maKH);
        System.out.println("Ho ten: " + hoTen);
        System.out.println("SDT: " + soDienThoai);
        System.out.println("Email: " + email);
    }
}