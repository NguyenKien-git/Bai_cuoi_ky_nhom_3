package api;

import service.InOutput;
import service.ServiceTicker;
import Test.Test;

public class MainApplication {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("========================================");
        System.out.println("   HE THONG QUAN LY VE XE BUYT");
        System.out.println("========================================");

        System.out.println();
        System.out.println("Nhap lua chon:");
        System.out.println("  true  - Mua ve (BuyTicket)");
        System.out.println("  false - Huy ve (CannerTicket)");
        System.out.println("  null  - Thoat / Chay Test");

        Boolean luachon = null;

        if (luachon == null) {
            System.out.println();
            System.out.println("1. Chay Test");
            System.out.println("2. Thoat");

            int choice = 1;

            if (choice == 1) {
                // Chay test - vo hieu hoa cac chuong trinh con lai
                Test.runTests();
            }
            System.out.println("Ket thuc chuong trinh!");
            return;
        }

        // True/False: Chay chuong trinh tuong ung
        ServiceTicker serviceTicker = new ServiceTicker();
        serviceTicker.BuyTicket(luachon);

        System.out.println("Ket thuc chuong trinh!");
    }
}
