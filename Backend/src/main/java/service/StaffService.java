package service;

import model.object.Trip;
import model.object.Customer;

public class StaffService {
    private ServiceTrip serviceTrip;
    private ServiceUser serviceUser;
    private ServiceSeat serviceSeat;

    public StaffService() {
        this.serviceTrip = new ServiceTrip();
        this.serviceUser = new ServiceUser();
        this.serviceSeat = new ServiceSeat();
    }

    /**
     * Menu chinh cua Staff (Nhan vien)
     */
    public void StartService() {
        System.out.println();
        System.out.println("========================= MENU STAFF =========================");
        System.out.println("1. Quan ly Trip");
        System.out.println("2. Quan ly User");
        System.out.println("3. Quan ly Seat");
        System.out.println("4. Xem danh sach Trip");
        System.out.println("5. Xem danh sach User");
        System.out.println("0. Quay lai");
        System.out.println("============================================================");

        int choice = InOutput.chonMenu("Lua chon: ", 0, 5);

        switch (choice) {
            case 1:
                menuTrip();
                break;
            case 2:
                menuUser();
                break;
            case 3:
                menuSeat();
                break;
            case 4:
                viewAllTrips();
                break;
            case 5:
                serviceUser.InAllUsers();
                break;
            case 0:
                return;
        }

        // Tiep tuc
        System.out.println();
        Boolean tiepTuc = InOutput.nhapBoolean("Ban co muon tiep tuc (Staff)?");
        if (tiepTuc) {
            StartService();
        }
    }

    private void menuTrip() {
        System.out.println();
        System.out.println("--------------- QUAN LY TRIP ---------------");
        System.out.println("1. Tao Trip moi");
        System.out.println("2. Xoa Trip");
        System.out.println("3. Xem danh sach Trip");
        System.out.println("0. Quay lai");

        int choice = InOutput.chonMenu("Lua chon: ", 0, 3);

        switch (choice) {
            case 1:
                createTrip();
                break;
            case 2:
                deleteTrip();
                break;
            case 3:
                viewAllTrips();
                break;
            case 0:
                return;
        }
    }

    private void createTrip() {
        InOutput.inTieuDe("TAO TRIP MOI");

        String from = InOutput.nhapChuoi("Nhap diem di: ");
        String to = InOutput.nhapChuoi("Nhap diem den: ");
        int hh = InOutput.chonMenu("Nhap gio (0-23): ", 0, 23);
        int mm = InOutput.chonMenu("Nhap phut (0-59): ", 0, 59);
        String bienSo = InOutput.nhapChuoi("Nhap bien so: ");
        int totalSeat = InOutput.nhapSoNguyen("Nhap so ghe: ");

        serviceTrip.createTrip(from, to, mm, hh, bienSo, totalSeat);
        InOutput.inThanhCong("Tao trip thanh cong!");
    }

    private void deleteTrip() {
        viewAllTrips();

        if (serviceTrip.getTrips().isEmpty()) {
            return;
        }

        String idTrip = InOutput.nhapChuoi("Nhap ID trip can xoa: ");
        Trip trip = serviceTrip.findTripById(idTrip);

        if (trip == null) {
            InOutput.inLoi("Khong tim thay trip!");
            return;
        }

        Boolean xacNhan = InOutput.nhapBoolean("Ban co chac chan muon xoa trip?");
        if (xacNhan) {
            serviceTrip.deleteTrip(trip);
            InOutput.inThanhCong("Xoa trip thanh cong!");
        }
    }

    private void viewAllTrips() {
        InOutput.inTieuDe("DANH SACH TRIP");
        Trip.InAllTrips(serviceTrip.getTrips());
    }

    private void menuUser() {
        System.out.println();
        System.out.println("--------------- QUAN LY USER ---------------");
        System.out.println("1. Tao User moi");
        System.out.println("2. Xem danh sach User");
        System.out.println("3. Tim User");
        System.out.println("0. Quay lai");

        int choice = InOutput.chonMenu("Lua chon: ", 0, 3);

        switch (choice) {
            case 1:
                createUser();
                break;
            case 2:
                serviceUser.InAllUsers();
                break;
            case 3:
                String idUser = InOutput.nhapChuoi("Nhap ID user: ");
                serviceUser.InUser(idUser);
                break;
            case 0:
                return;
        }
    }

    private void createUser() {
        InOutput.inTieuDe("TAO USER MOI");

        System.out.println("1. NomalUser");
        System.out.println("2. MemberUser");
        System.out.println("3. VIPUser");
        int chosse = InOutput.chonMenu("Chon loai user (1-3): ", 1, 3);

        String name = InOutput.nhapChuoi("Nhap ten: ");
        int phone = InOutput.nhapSoNguyen("Nhap so dien thoai: ");
        int type = InOutput.nhapSoNguyen("Nhap type: ");
        int khuyenMai = InOutput.nhapSoNguyen("Nhap % khuyen mai: ");

        Customer tempCustomer = new Customer(khuyenMai, "", name, phone, type);
        Customer customer = serviceUser.createUser(chosse, tempCustomer);

        if (customer != null) {
            InOutput.inThanhCong("Tao user thanh cong!");
            customer.In();
        }
    }

    private void menuSeat() {
        System.out.println();
        System.out.println("--------------- QUAN LY SEAT ---------------");

        viewAllTrips();

        if (serviceTrip.getTrips().isEmpty()) {
            return;
        }

        String idTrip = InOutput.nhapChuoi("Nhap ID trip de xem seat: ");
        Trip trip = serviceTrip.findTripById(idTrip);

        if (trip == null) {
            InOutput.inLoi("Khong tim thay trip!");
            return;
        }

        InOutput.inTieuDe("DANH SACH GHE - TRIP " + idTrip);
        model.object.Seat.InAllSeats(trip.listSeat());
    }

    // Getters
    public ServiceTrip getServiceTrip() {
        return serviceTrip;
    }

    public ServiceUser getServiceUser() {
        return serviceUser;
    }
}
