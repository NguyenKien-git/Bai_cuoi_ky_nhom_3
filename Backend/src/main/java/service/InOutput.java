package service;

import java.util.Scanner;

public class InOutput {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Nhap so nguyen
     * @param message Thong bao nhap
     * @return So nguyen
     */
    public static int nhapSoNguyen(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Vui long nhap so nguyen!");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextInt();
    }

    /**
     * Nhap so thuc
     * @param message Thong bao nhap
     * @return So thuc
     */
    public static double nhapSoThuc(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Vui long nhap so thuc!");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextDouble();
    }

    /**
     * Nhap chuoi
     * @param message Thong bao nhap
     * @return Chuoi nhap vao
     */
    public static String nhapChuoi(String message) {
        System.out.print(message);
        scanner.nextLine();
        return scanner.nextLine();
    }

    /**
     * Chon menu trong khoang min - max
     * @param message Thong bao nhap
     * @param min Gia tri nho nhat
     * @param max Gia tri lon nhat
     * @return Lua chon
     */
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

    /**
     * Nhap Boolean
     * @param message Thong bao nhap
     * @return true/false
     */
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

    /**
     * In tieu de
     * @param tieuDe Tieu de can in
     */
    public static void inTieuDe(String tieuDe) {
        System.out.println();
        System.out.println("========================= " + tieuDe + " =========================");
    }

    /**
     * In thong bao thanh cong
     * @param message Thong bao
     */
    public static void inThanhCong(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    /**
     * In thong bao loi
     * @param message Thong bao
     */
    public static void inLoi(String message) {
        System.out.println("[ERROR] " + message);
    }

    /**
     * In thong bao thong thuong
     * @param message Thong bao
     */
    public static void inThongBao(String message) {
        System.out.println("[NOTICE] " + message);
    }
}
