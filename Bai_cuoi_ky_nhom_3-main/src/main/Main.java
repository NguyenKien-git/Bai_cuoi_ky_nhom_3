package main;

import model.*;
import service.BookingService;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== HE THONG DAT VE XE KHACH HA NOI - HAI PHONG ===");
            System.out.println("1. Khach hang dat ve");
            System.out.println("2. Nhan vien xem ve da ban");
            System.out.println("3. Thoat");
            System.out.print("Chon: ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                handleCustomerBooking(bookingService, sc);
            } else if (choice == 2) {
                handleStaffView(bookingService);
            } else if (choice == 3) {
                System.out.println("Cam on ban da su dung he thong!");
                break;
            }
        }
        sc.close();
    }

    private static void handleCustomerBooking(BookingService service, Scanner sc) {
        System.out.println("\n--- DAT VE ---");
        System.out.print("Nhap ho ten: ");
        String ten = sc.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String sdt = sc.nextLine();
        System.out.print("Nhap email: ");
        String email = sc.nextLine();
        System.out.print("Loai khach (NORMAL/MEMBER/VIP): ");
        String loai = sc.nextLine().toUpperCase();

        Customer customer = new Customer("KH" + System.currentTimeMillis(), ten, sdt, email, loai);

        System.out.println("\nDanh sach chuyen xe:");
        List<BusTrip> trips = service.getAllTrips();
        for (BusTrip t : trips) {
            System.out.println(t.getMaChuyen() + " | " + t.getDiemDi() + " -> " + t.getDiemDen() + " | " + t.getThoiGianKhoiHanh() + " | Gia: " + t.getGiaCoBan());
        }

        System.out.print("\nNhap ma chuyen xe: ");
        String maChuyen = sc.nextLine();
        BusTrip selectedTrip = service.getTripById(maChuyen);
        if (selectedTrip == null) {
            System.out.println("Khong tim thay chuyen xe!");
            return;
        }

        System.out.println("\nGhe trong cua chuyen " + maChuyen + ":");
        List<Seat> available = selectedTrip.getGheTrong();
        for (Seat s : available) {
            System.out.println(s.getSoGhe() + " (" + s.getLoaiGhe() + ") - Phu phi: " + s.getPhuPhi());
        }

        System.out.print("\nNhap so ghe muon dat: ");
        String soGhe = sc.nextLine();
        Seat chosenSeat = selectedTrip.getGheBySoGhe(soGhe);
        if (chosenSeat == null || !chosenSeat.getTrangThai().equals("AVAILABLE")) {
            System.out.println("Ghe khong hop le hoac da dat!");
            return;
        }

        double finalPrice = service.calculateFinalPrice(selectedTrip, chosenSeat, customer);
        System.out.println("Gia ve cuoi cung: " + finalPrice + " VND (da tinh phu phi + giam gia)");

        System.out.println("\nChon phuong thuc thanh toan:");
        System.out.println("1. Tien mat");
        System.out.println("2. Chuyen khoan");
        System.out.println("3. Vi dien tu");
        System.out.print("Chon: ");
        int pm = Integer.parseInt(sc.nextLine());
        PaymentMethod paymentMethod;
        if (pm == 1) paymentMethod = new CashPayment();
        else if (pm == 2) paymentMethod = new BankTransferPayment();
        else paymentMethod = new EWalletPayment();

        try {
            Ticket ticket = service.bookTicket(customer, maChuyen, soGhe, paymentMethod);
            System.out.println("\n=== DAT VE THANH CONG ===");
            System.out.println("Ma ve: " + ticket.getMaVe());
            System.out.println("Chuyen: " + ticket.getChuyenXe().getMaChuyen());
            System.out.println("Ghe: " + ticket.getGhe().getSoGhe() + " (" + ticket.getGhe().getLoaiGhe() + ")");
            System.out.println("Gia: " + ticket.getGiaVeCuoiCung());
            System.out.println("Thanh toan: " + ticket.getPhuongThucThanhToan());
            System.out.println("Trang thai: " + ticket.getTrangThaiThanhToan());
        } catch (Exception e) {
            System.out.println("Loi dat ve: " + e.getMessage());
        }
    }

    private static void handleStaffView(BookingService service) {
        System.out.println("\n--- VE DA BAN ---");
        List<String> lines = service.getSoldTicketsToday();
        if (lines.isEmpty()) {
            System.out.println("Chua co ve nao duoc ban.");
        } else {
            for (String line : lines) {
                System.out.println(line);
            }
        }
    }
}