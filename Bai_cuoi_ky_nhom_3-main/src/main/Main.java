package main;

import model.*;
import service.QuanLyKhachHang;

public class Main {
    public static void main(String[] args) {

        QuanLyKhachHang ql = new QuanLyKhachHang();

        KhachHang kh1 = new KhachThuong(
                "KH01",
                "Nguyen Van A",
                "0123456789",
                "a@gmail.com");

        KhachHang kh2 = new KhachThanhVien(
                "KH02",
                "Tran Thi B",
                "0987654321",
                "b@gmail.com");

        KhachHang kh3 = new KhachVIP(
                "KH03",
                "Le Van C",
                "0111222333",
                "c@gmail.com");

        ql.themKhachHang(kh1);
        ql.themKhachHang(kh2);
        ql.themKhachHang(kh3);

        ql.hienThiDanhSach();

        double giaVe = 200000;

        System.out.println("Gia KH thuong: "
                + kh1.tinhGiaVe(giaVe));

        System.out.println("Gia KH thanh vien: "
                + kh2.tinhGiaVe(giaVe));

        System.out.println("Gia KH VIP: "
                + kh3.tinhGiaVe(giaVe));
    }
}