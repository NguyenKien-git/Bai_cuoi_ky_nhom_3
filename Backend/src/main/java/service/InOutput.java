package service;

import java.util.Scanner;
import java.io.File;

public class InOutput {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Method test - kiem tra file JSON trong repository va chay test
     */
    public static void test() {
        inTieuDe("CHAY TEST TU FILE JSON");
        System.out.println("Kiem tra cac file JSON trong repository...");

        // Lay duong dan tuyet doi den thu muc data
        String basePath = System.getProperty("user.dir");
        // Neu chay tu thu muc con, can them Backend vao duong dan
        File folder = new File(basePath, "Backend/src/main/java/data");

        System.out.println("Duong dan: " + folder.getAbsolutePath());

        if (!folder.exists()) {
            inLoi("Thu muc data khong ton tai tai: " + folder.getAbsolutePath());
            return;
        }

        File[] jsonFiles = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (jsonFiles == null || jsonFiles.length == 0) {
            inThongBao("Khong co file JSON nao trong thu muc data!");
            return;
        }

        System.out.println("Tim thay " + jsonFiles.length + " file JSON:");
        for (File file : jsonFiles) {
            System.out.println("  - " + file.getName());
        }

        System.out.println();
        inTieuDe("BAT DAU CHAY TEST");

        // Chay cac test scenario
        testDatGheConTrong();
        testDatGheDaCoNguoi();
        testChonGheKhongTonTai();
        testVIPDatGheVIP();
        testThanhToan();

        inThanhCong("Hoan thanh tat ca cac test!");
    }

    /**
     * Test 1: Khach dat ghe con trong thanh cong
     */
    private static void testDatGheConTrong() {
        System.out.println();
        System.out.println("=== TEST 1: Dat ghe con trong ===");
        ServiceSeat serviceSeat = new ServiceSeat();

        // Tao seat trong
        model.object.Seat seat = new model.object.Seat(1);

        // Tao customer
        model.object.Customer customer = new model.object.Customer(5, "U1", "Nguyen Van A", 123456, 1);

        boolean result = serviceSeat.addUserToSeat(seat, customer);

        if (result && seat.statusSeat()) {
            inThanhCong("TEST 1 PASSED: Dat ghe thanh cong!");
        } else {
            inLoi("TEST 1 FAILED: Dat ghe that bai!");
        }
    }

    /**
     * Test 2: Khach dat ghe da co nguoi
     */
    private static void testDatGheDaCoNguoi() {
        System.out.println();
        System.out.println("=== TEST 2: Dat ghe da co nguoi ===");
        ServiceSeat serviceSeat = new ServiceSeat();

        model.object.Seat seat = new model.object.Seat(2);
        model.object.Customer customer1 = new model.object.Customer(5, "U1", "Nguyen Van A", 123456, 1);
        model.object.Customer customer2 = new model.object.Customer(10, "U2", "Tran Van B", 654321, 1);

        // Customer 1 dat truoc
        serviceSeat.addUserToSeat(seat, customer1);

        // Customer 2 dat sau - phai that bai
        boolean result = serviceSeat.addUserToSeat(seat, customer2);

        if (!result) {
            inThanhCong("TEST 2 PASSED: Khong the dat ghe da co nguoi!");
        } else {
            inLoi("TEST 2 FAILED: Van dat duoc ghe da co nguoi!");
        }
    }

    /**
     * Test 3: Khach chon ghe khong ton tai
     */
    private static void testChonGheKhongTonTai() {
        System.out.println();
        System.out.println("=== TEST 3: Chon ghe khong ton tai ===");
        ServiceTrip serviceTrip = new ServiceTrip();
        ServiceSeat serviceSeat = new ServiceSeat();

        // Tao trip
        serviceTrip.createTrip("Ha Noi", "Ho Chi Minh", 30, 14, "ABC-123", 40);
        model.object.Trip trip = serviceTrip.findTripById("Trip1");

        // Tim seat khong ton tai
        model.object.Seat fakeSeat = null;
        for (model.object.Seat s : trip.listSeat()) {
            if (s.idSeat().equals("S99")) {
                fakeSeat = s;
                break;
            }
        }

        if (fakeSeat == null) {
            inThanhCong("TEST 3 PASSED: Ghe S99 khong ton tai!");
        } else {
            inLoi("TEST 3 FAILED: Ghe S99 ton tai!");
        }
    }

    /**
     * Test 4: Khach VIP dat ghe VIP va tinh gia dung
     */
    private static void testVIPDatGheVIP() {
        System.out.println();
        System.out.println("=== TEST 4: VIP dat ghe VIP ===");
        ServiceSeat serviceSeat = new ServiceSeat();

        model.object.Seat seat = new model.object.Seat(3);
        model.object.VIPUser vip = new model.object.VIPUser(10, "U1", "VIP Customer", 111111, 3);

        double giaVeGoc = vip.getTickerPrice();
        boolean result = serviceSeat.addUserToSeat(seat, vip);

        if (result && seat.typeSeat() && seat.statusSeat()) {
            // Kiem tra gia ve VIP (co phu phi 25%)
            double basePrice = 25000;
            double phuPhi = 0.25;
            double khuyenMai = 10;
            double giaVeDung = basePrice * (1 + phuPhi) - basePrice * (khuyenMai / 100.0);

            System.out.printf("Gia ve VIP (co phu phi 25%%, khuyen mai 10%%): %,.0f VND%n", giaVeDung);
            System.out.printf("Gia ve tinh duoc: %,.0f VND%n", giaVeGoc);

            if (Math.abs(giaVeGoc - giaVeDung) < 1) {
                inThanhCong("TEST 4 PASSED: VIP dat ghe VIP thanh cong, gia dung!");
            } else {
                inLoi("TEST 4 FAILED: Gia ve khong dung!");
            }
        } else {
            inLoi("TEST 4 FAILED: VIP khong dat duoc ghe VIP!");
        }
    }

    /**
     * Test 5: Thanh toan ve
     */
    private static void testThanhToan() {
        System.out.println();
        System.out.println("=== TEST 5: Thanh toan ve ===");
        ServicePayment servicePayment = new ServicePayment();

        model.object.Customer customer = new model.object.Customer(10, "U1", "Test Customer", 123456, 1);
        double giaVe = customer.getTickerPrice();

        System.out.printf("Gia ve can thanh toan: %,.0f VND%n", giaVe);

        // Test tra tien du
        boolean result = servicePayment.thanhToan(30000, giaVe);
        if (result) {
            inThanhCong("TEST 5 PASSED: Thanh toan thanh cong!");
        } else {
            inLoi("TEST 5 FAILED: Thanh toan that bai!");
        }
    }

    public static int nhapSoNguyen(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Vui long nhap so nguyen!");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextInt();
    }

    public static double nhapSoThuc(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Vui long nhap so thuc!");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextDouble();
    }

    public static String nhapChuoi(String message) {
        System.out.print(message);
        scanner.nextLine();
        return scanner.nextLine();
    }

    public static int chonMenu(String message, int min, int max) {
        int choice;
        while (true) {
            choice = nhapSoNguyen(message);
            if (choice >= min && choice <= max) {
                break;
            }
            System.out.println("Vui long chon trong khoang " + min + " - " + max);
        }
        return choice;
    }

    public static Boolean nhapBoolean(String message) {
        System.out.print(message + " (true/false): ");
        while (true) {
            String input = scanner.next().toLowerCase();
            if (input.equals("true") || input.equals("1") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("0") || input.equals("n")) {
                return false;
            }
            System.out.println("Vui long nhap true hoac false!");
        }
    }

    /**
     * Nhap Boolean co the tra ve null
     * @param message Thong bao nhap
     * @return true/false/null
     */
    public static Boolean nhapBooleanNull(String message) {
        System.out.print(message + " (true/false/null): ");
        while (true) {
            String input = scanner.next().toLowerCase().trim();
            if (input.equals("true") || input.equals("1") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("0") || input.equals("n")) {
                return false;
            } else if (input.equals("null") || input.equals("")) {
                return null;
            }
            System.out.println("Vui long nhap true, false hoac null!");
        }
    }

    public static void inTieuDe(String tieuDe) {
        System.out.println();
        System.out.println("========================= " + tieuDe + " =========================");
    }

    public static void inThanhCong(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public static void inLoi(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void inThongBao(String message) {
        System.out.println("[NOTICE] " + message);
    }
}
