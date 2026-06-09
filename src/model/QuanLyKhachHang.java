package service;

import java.util.ArrayList;
import model.KhachHang;

public class QuanLyKhachHang {

    private ArrayList<KhachHang> dsKhachHang;

    public QuanLyKhachHang() {
        dsKhachHang = new ArrayList<>();
    }

    public void themKhachHang(KhachHang kh) {
        dsKhachHang.add(kh);
    }

    public void hienThiDanhSach() {
        for (KhachHang kh : dsKhachHang) {
            kh.hienThi();
            System.out.println("------------------");
        }
    }

    public KhachHang timTheoMa(String maKH) {
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKH().equalsIgnoreCase(maKH)) {
                return kh;
            }
        }
        return null;
    }
}