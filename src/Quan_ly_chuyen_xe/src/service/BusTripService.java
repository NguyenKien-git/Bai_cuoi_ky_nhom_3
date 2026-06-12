package service;

import model.BusTrip;
import model.Seat;
import java.util.ArrayList;
import java.util.List;

public class BusTripService {

    private List<BusTrip> danhSachChuyenXe;

    public BusTripService() {
        this.danhSachChuyenXe = new ArrayList<>();
    }

    public void themChuyenXe(BusTrip chuyenXe) {
        this.danhSachChuyenXe.add(chuyenXe);
    }

    public void hienThiDanhSachChuyenXe() {
        if (danhSachChuyenXe.isEmpty()) {
            System.out.println("Hiện tại không có chuyến xe nào.");
            return;
        }
        System.out.println("\n=== DANH SÁCH CHUYẾN XE ĐANG HOẠT ĐỘNG ===");
        for (BusTrip trip : danhSachChuyenXe) {
            System.out.printf("Mã: %s | Tuyến: %s -> %s | Khởi hành: %s | Xe: %s | Tổng số ghế: %d\n",
                    trip.getMaChuyenXe(), trip.getDiemDi(), trip.getDiemDen(),
                    trip.getThoiGianKhoiHanh(), trip.getBienSoXe(), trip.getTongSoGhe());
        }
    }


    public List<BusTrip> timChuyenXe(String diemDi, String diemDen) {
        List<BusTrip> ketQua = new ArrayList<>();
        for (BusTrip trip : danhSachChuyenXe) {

            if (trip.getDiemDi().equalsIgnoreCase(diemDi) && trip.getDiemDen().equalsIgnoreCase(diemDen)) {
                ketQua.add(trip);
            }
        }


        if (ketQua.isEmpty()) {
            System.out.println("❌ Không tìm thấy chuyến xe nào phù hợp với tuyến đường bạn yêu cầu.");
        }
        return ketQua;
    }


    public void xemDanhSachGheTrong(String maChuyenXe) {
        BusTrip chuyenXeTimDuoc = null;

        for (BusTrip trip : danhSachChuyenXe) {
            if (trip.getMaChuyenXe().equalsIgnoreCase(maChuyenXe)) {
                chuyenXeTimDuoc = trip;
                break;
            }
        }


        if (chuyenXeTimDuoc == null) {
            System.out.println("❌ Lỗi: Mã chuyến xe '" + maChuyenXe + "' không tồn tại trong hệ thống.");
            return;
        }

        System.out.println("\n=== DANH SÁCH GHẾ CÒN TRỐNG CỦA CHUYẾN " + maChuyenXe.toUpperCase() + " ===");
        int demGheTrong = 0;

        for (Seat seat : chuyenXeTimDuoc.getDanhSachGhe()) {
            if (!seat.isDaDat()) { // Nếu ghế chưa đặt (còn trống)
                System.out.printf("  -> Mã ghế: %s | Số ghế: %d | Loại: %s\n",
                        seat.getMaGhe(), seat.getSoGhe(), seat.getLoaiGhe());
                demGheTrong++;
            }
        }

        if (demGheTrong == 0) {
            System.out.println("Chuyến xe này đã hết sạch ghế trống!");
        } else {
            System.out.println("Tổng cộng còn trống " + demGheTrong + " ghế.");
        }
    }
}