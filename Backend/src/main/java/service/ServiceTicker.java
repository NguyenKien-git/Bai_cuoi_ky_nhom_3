package service;

import model.object.Customer;
import model.object.Trip;
import model.object.Seat;
import model.object.Ticker;
import java.util.ArrayList;
import java.util.List;

public class ServiceTicker {
    private List<Ticker> tickers = new ArrayList<>();
    private int tickerCount = 0;
    private ServiceUser serviceUser;
    private ServiceTrip serviceTrip;
    private ServiceSeat serviceSeat;
    private ServicePayment servicePayment;
    private StaffService staffService;

    public ServiceTicker() {
        this.serviceUser = new ServiceUser();
        this.serviceTrip = new ServiceTrip();
        this.serviceSeat = new ServiceSeat();
        this.servicePayment = new ServicePayment();
        this.staffService = new StaffService();
    }

    // Constructor với các service
    public ServiceTicker(ServiceUser serviceUser, ServiceTrip serviceTrip,
                        ServiceSeat serviceSeat, ServicePayment servicePayment, StaffService staffService) {
        this.serviceUser = serviceUser;
        this.serviceTrip = serviceTrip;
        this.serviceSeat = serviceSeat;
        this.servicePayment = servicePayment;
        this.staffService = staffService;
        this.tickers = new ArrayList<>();
    }

    /**
     * Mua ve - luachon true/false
     * @param luachon true = tao user moi, false = tim user cu
     */
    public void BuyTicket(Boolean luachon) {
        Customer customer = null;

        if (luachon == null) {
            InOutput.inThongBao("Ket thuc chuong trinh!");
            return;
        }

        if (luachon) {
            // === TAO USER MOI ===
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
            customer = serviceUser.createUser(chosse, tempCustomer);

            if (customer != null) {
                InOutput.inThanhCong("Tao user thanh cong!");
                customer.In();
            }
        } else {
            // === TIM USER CU (luachon = false) ===
            InOutput.inTieuDe("TIM USER");
            String idUser = InOutput.nhapChuoi("Nhap ID user: ");

            // InUser tra ve true neu tim thay, false neu khong tim thay
            boolean found = serviceUser.InUser(idUser);

            if (found) {
                // Tim thay user -> lay thong tin va chay tiep mua ve
                customer = serviceUser.findUserById(idUser);
                InOutput.inThongBao("Tim thay user, tiep tuc mua ve...");
            } else {
                // Khong tim thay -> goi StaffService
                InOutput.inThongBao("Khong tim thay user! Chuyen sang StaffService...");
                staffService.StartService();
                return;
            }
        }

        // === TIEP TUC MUA VE (DOAN CHAY TIEP **) ===
        if (customer != null) {
            buyTicketProcess(customer);
        }
    }

    /**
     * Quy trinh mua ve (Day la doan ** trong yeu cau)
     */
    private void buyTicketProcess(Customer customer) {
        // In tat ca trip
        InOutput.inTieuDe("DANH SACH TRIP");
        List<Trip> trips = serviceTrip.getTrips();
        if (trips.isEmpty()) {
            InOutput.inLoi("Khong co trip nao!");
            return;
        }
        Trip.InAllTrips(trips);

        // Chon trip
        String idTrip = InOutput.nhapChuoi("Nhap ID trip: ");
        Trip trip = serviceTrip.findTripById(idTrip);
        if (trip == null) {
            InOutput.inLoi("Khong tim thay trip!");
            return;
        }

        // In tat ca seat cua trip
        InOutput.inTieuDe("DANH SACH GHE");
        Seat.InAllSeats(trip.listSeat());

        // Chon seat
        String idSeat = InOutput.nhapChuoi("Nhap ID seat: ");
        Seat seat = null;
        for (Seat s : trip.listSeat()) {
            if (s.idSeat().equals(idSeat)) {
                seat = s;
                break;
            }
        }
        if (seat == null) {
            InOutput.inLoi("Khong tim thay seat!");
            return;
        }

        // Kiem tra seat da dat chua
        if (seat.statusSeat()) {
            InOutput.inLoi("Seat da duoc dat!");
            return;
        }

        // Them user vao seat
        boolean added = serviceSeat.addUserToSeat(seat, customer);
        if (!added) {
            InOutput.inLoi("Khong the dat cho!");
            return;
        }

        // Tao ticket
        tickerCount++;
        String idTicker = "T" + tickerCount;
        Ticker ticker = new Ticker(idTicker, customer, trip, seat);
        tickers.add(ticker);

        // In ticket
        InOutput.inTieuDe("THONG TIN VE");
        ticker.InTicker();

        // Thanh toan
        boolean thanhToan = servicePayment.thanhToan(customer);
        if (thanhToan) {
            ticker.setPayment(true);
            InOutput.inThanhCong("Dat ve thanh cong!");
        } else {
            // Hoan tra neu thanh toan that bai
            serviceSeat.removeUserFromSeat(seat);
            tickers.remove(ticker);
            InOutput.inLoi("Dat ve that bai!");
        }
    }

    /**
     * Huy ve - Nhap IdUser -> In cac cho da dat -> Chon IdSeat -> Xac nhan huy
     */
    public void CannerTicket() {
        // Nhap IdUser
        String idUser = InOutput.nhapChuoi("Nhap ID user: ");

        // Tim user
        Customer customer = serviceUser.findUserById(idUser);
        if (customer == null) {
            InOutput.inLoi("Khong tim thay user!");
            return;
        }

        // Tim tat ca ticket cua user
        List<Ticker> userTickets = new ArrayList<>();
        for (Ticker t : tickers) {
            if (t.customer().idUser().equals(idUser) && t.payment()) {
                userTickets.add(t);
            }
        }

        if (userTickets.isEmpty()) {
            InOutput.inThongBao("User chua dat ve nao!");
            return;
        }

        // In cac ve da dat
        InOutput.inTieuDe("CAC VE DA DAT");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-10s | %-15s | %-15s | %-10s |%n",
                "ID Ticket", "ID Seat", "From", "To", "Price");
        System.out.println("---------------------------------------------------------------------------");
        for (Ticker t : userTickets) {
            System.out.printf("| %-10s | %-10s | %-15s | %-15s | %,.0f VND |%n",
                    t.idTicker(),
                    t.seat().idSeat(),
                    t.trip().fromTrip(),
                    t.trip().toTrip(),
                    t.customer().getTickerPrice());
        }
        System.out.println("---------------------------------------------------------------------------");

        // Chon seat can huy
        String idSeat = InOutput.nhapChuoi("Nhap ID seat can huy: ");

        // Tim ticket tuong ung
        Ticker ticketToCancel = null;
        for (Ticker t : userTickets) {
            if (t.seat().idSeat().equals(idSeat)) {
                ticketToCancel = t;
                break;
            }
        }

        if (ticketToCancel == null) {
            InOutput.inLoi("Khong tim thay ve voi seat nay!");
            return;
        }

        // Xac nhan huy
        InOutput.inTieuDe("XAC NHAN HUY VE");
        System.out.println("Thong tin ve can huy:");
        ticketToCancel.InTicker();

        Boolean xacNhan = InOutput.nhapBoolean("Ban co chac chan muon huy ve?");

        if (xacNhan) {
            // Xoa user khoi seat
            serviceSeat.removeUserFromSeat(ticketToCancel.seat());

            // Xoa ticket
            tickers.remove(ticketToCancel);

            InOutput.inThanhCong("Huy ve thanh cong!");
        } else {
            InOutput.inThongBao("Da huy thao tac!");
        }
    }

    // Getters
    public List<Ticker> getTickers() {
        return tickers;
    }

    public ServiceUser getServiceUser() {
        return serviceUser;
    }

    public ServiceTrip getServiceTrip() {
        return serviceTrip;
    }
}
