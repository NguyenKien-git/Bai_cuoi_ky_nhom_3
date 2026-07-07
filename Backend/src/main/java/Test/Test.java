package Test;

import service.InOutput;
import service.ServiceSeat;
import service.ServiceTrip;
import service.ServicePayment;
import model.object.Seat;
import model.object.Trip;
import model.object.VIPUser;
import model.object.Customer;

public class Test {

    /**
     * Method test - kiem tra file JSON trong classpath va chay test
     */
    public static void runTests() {
        InOutput.inTieuDe("CHAY TEST TU FILE JSON");
        System.out.println("Kiem tra cac file JSON trong classpath...");

        // Kiem tra cac file JSON bang classpath
        String[] fileNames = {"users.json", "trips.json", "tickers.json", "payments.json", "seats.json"};
        java.util.List<String> foundFiles = new java.util.ArrayList<>();

        for (String fileName : fileNames) {
            String resourcePath = "data/" + fileName;
            java.io.InputStream is = Test.class.getClassLoader().getResourceAsStream(resourcePath);
            if (is != null) {
                foundFiles.add(fileName);
                try {
                    is.close();
                } catch (Exception e) {}
            }
        }

        if (foundFiles.isEmpty()) {
            InOutput.inThongBao("Khong co file JSON nao trong thu muc data!");
            return;
        }

        System.out.println("Tim thay " + foundFiles.size() + " file JSON:");
        for (String file : foundFiles) {
            System.out.println("  - " + file);
        }

        System.out.println();
        InOutput.inTieuDe("BAT DAU CHAY TEST");

        // Chay cac test scenario
        testDatGheConTrong();
        testDatGheDaCoNguoi();
        testChonGheKhongTonTai();
        testVIPDatGheVIP();
        testThanhToan();

        InOutput.inThanhCong("Hoan thanh tat ca cac test!");
    }

    /**
     * Test 1: Khach dat ghe con trong thanh cong
     */
    private static void testDatGheConTrong() {
        System.out.println();
        System.out.println("=== TEST 1: Dat ghe con trong ===");
        ServiceSeat serviceSeat = new ServiceSeat();

        // Tao seat trong
        Seat seat = new Seat(1);

        // Tao customer
        Customer customer = new Customer(5, "U1", "Nguyen Van A", 123456, 1);

        boolean result = serviceSeat.addUserToSeat(seat, customer);

        if (result && seat.statusSeat()) {
            InOutput.inThanhCong("TEST 1 PASSED: Dat ghe thanh cong!");
        } else {
            InOutput.inLoi("TEST 1 FAILED: Dat ghe that bai!");
        }
    }

    /**
     * Test 2: Khach dat ghe da co nguoi
     */
    private static void testDatGheDaCoNguoi() {
        System.out.println();
        System.out.println("=== TEST 2: Dat ghe da co nguoi ===");
        ServiceSeat serviceSeat = new ServiceSeat();

        Seat seat = new Seat(2);
        Customer customer1 = new Customer(5, "U1", "Nguyen Van A", 123456, 1);
        Customer customer2 = new Customer(10, "U2", "Tran Van B", 654321, 1);

        // Customer 1 dat truoc
        serviceSeat.addUserToSeat(seat, customer1);

        // Customer 2 dat sau - phai that bai
        boolean result = serviceSeat.addUserToSeat(seat, customer2);

        if (!result) {
            InOutput.inThanhCong("TEST 2 PASSED: Khong the dat ghe da co nguoi!");
        } else {
            InOutput.inLoi("TEST 2 FAILED: Van dat duoc ghe da co nguoi!");
        }
    }

    /**
     * Test 3: Khach chon ghe khong ton tai
     */
    private static void testChonGheKhongTonTai() {
        System.out.println();
        System.out.println("=== TEST 3: Chon ghe khong ton tai ===");
        ServiceTrip serviceTrip = new ServiceTrip();

        // Tao trip
        serviceTrip.createTrip("Ha Noi", "Ho Chi Minh", 30, 14, "ABC-123", 40);
        Trip trip = serviceTrip.findTripById("Trip1");

        // Tim seat khong ton tai
        Seat fakeSeat = null;
        for (Seat s : trip.listSeat()) {
            if (s.idSeat().equals("S99")) {
                fakeSeat = s;
                break;
            }
        }

        if (fakeSeat == null) {
            InOutput.inThanhCong("TEST 3 PASSED: Ghe S99 khong ton tai!");
        } else {
            InOutput.inLoi("TEST 3 FAILED: Ghe S99 ton tai!");
        }
    }

    /**
     * Test 4: Khach VIP dat ghe VIP va tinh gia dung
     */
    private static void testVIPDatGheVIP() {
        System.out.println();
        System.out.println("=== TEST 4: VIP dat ghe VIP ===");
        ServiceSeat serviceSeat = new ServiceSeat();

        Seat seat = new Seat(3);
        VIPUser vip = new VIPUser(10, "U1", "VIP Customer", 111111, 3);

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
                InOutput.inThanhCong("TEST 4 PASSED: VIP dat ghe VIP thanh cong, gia dung!");
            } else {
                InOutput.inLoi("TEST 4 FAILED: Gia ve khong dung!");
            }
        } else {
            InOutput.inLoi("TEST 4 FAILED: VIP khong dat duoc ghe VIP!");
        }
    }

    /**
     * Test 5: Thanh toan ve
     */
    private static void testThanhToan() {
        System.out.println();
        System.out.println("=== TEST 5: Thanh toan ve ===");
        ServicePayment servicePayment = new ServicePayment();

        Customer customer = new Customer(10, "U1", "Test Customer", 123456, 1);
        double giaVe = customer.getTickerPrice();

        System.out.printf("Gia ve can thanh toan: %,.0f VND%n", giaVe);

        // Test tra tien du
        boolean result = servicePayment.thanhToan(30000, giaVe);
        if (result) {
            InOutput.inThanhCong("TEST 5 PASSED: Thanh toan thanh cong!");
        } else {
            InOutput.inLoi("TEST 5 FAILED: Thanh toan that bai!");
        }
    }
}
