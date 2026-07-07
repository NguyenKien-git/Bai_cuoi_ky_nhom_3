package service;

import model.inheritance.ChuyenKhoan;
import model.inheritance.TienMat;
import model.inheritance.Vi;
import model.object.Customer;

public class ServicePayment implements TienMat, ChuyenKhoan, Vi {

    /**
     * Thanh toan tien mat
     * @param soTien So tien khach hang dua
     * @param giaVe Gia ve can thanh toan
     * @return true neu thanh toan thanh cong (soTien >= giaVe)
     */
    @Override
    public boolean thanhToan(double soTien, double giaVe) {
        if (soTien >= giaVe) {
            System.out.println("Thanh toan thanh cong!");
            if (soTien > giaVe) {
                double tienThua = soTien - giaVe;
                System.out.printf("Tien thua: %,.0f VND%n", tienThua);
            }
            return true;
        } else {
            System.out.println("Thanh toan khong thanh cong!");
            System.out.printf("So tien %,.0f VND khong du. Can: %,.0f VND%n", soTien, giaVe);
            return false;
        }
    }

    /**
     * Thanh toan chuyen khoan
     * @param idTK So tai khoan
     * @param soTien So tien chuyen
     * @param giaVe Gia ve can thanh toan
     * @return true neu thanh toan thanh cong
     */
    @Override
    public boolean thanhToan(String idTK, double soTien, double giaVe) {
        System.out.println("=== Thanh Toan Chuyen Khoan ===");
        System.out.println("So tai khoan: " + idTK);
        System.out.printf("So tien chuyen: %,.0f VND%n", soTien);

        if (soTien >= giaVe) {
            System.out.println("Thanh toan thanh cong!");
            return true;
        } else {
            System.out.println("Thanh toan khong thanh cong!");
            System.out.printf("So tien %,.0f VND khong du. Can: %,.0f VND%n", soTien, giaVe);
            return false;
        }
    }

    /**
     * Thanh toan qua Vi dien tu
     * @param idVi ID Vi
     * @param maPIN Ma PIN xac nhan
     * @param soTien So tien thanh toan
     * @param giaVe Gia ve can thanh toan
     * @return true neu thanh toan thanh cong
     */
    @Override
    public boolean thanhToan(String idVi, String maPIN, double soTien, double giaVe) {
        System.out.println("=== Thanh Toan Qua Vi ===");
        System.out.println("ID Vi: " + idVi);
        System.out.println("Ma PIN: ****");

        // Kiem tra ma PIN (demo - ma PIN mac dinh la "1234")
        if (!"1234".equals(maPIN)) {
            System.out.println("Thanh toan khong thanh cong! Ma PIN sai.");
            return false;
        }

        if (soTien >= giaVe) {
            System.out.println("Thanh toan thanh cong!");
            return true;
        } else {
            System.out.println("Thanh toan khong thanh cong!");
            System.out.printf("So tien %,.0f VND khong du. Can: %,.0f VND%n", soTien, giaVe);
            return false;
        }
    }

    /**
     * Chon phuong thuc thanh toan va thuc hien
     * @param choose 1 = TienMat, 2 = ChuyenKhoan, 3 = Vi
     * @param customer Khach hang
     * @return true neu thanh toan thanh cong
     */
    public boolean chonPhuongThucThanhToan(int choose, Customer customer) {
        double giaVe = customer.getTickerPrice();

        switch (choose) {
            case 1: // Tien mat
                System.out.println("=== Thanh Toan Tien Mat ===");
                double soTienMat = InOutput.nhapSoThuc("Nhap so tien: ");
                return thanhToan(soTienMat, giaVe);

            case 2: // Chuyen khoan
                String idTK = InOutput.nhapChuoi("Nhap so tai khoan: ");
                double soTienCK = InOutput.nhapSoThuc("Nhap so tien chuyen: ");
                return thanhToan(idTK, soTienCK, giaVe);

            case 3: // Vi
                String idVi = InOutput.nhapChuoi("Nhap ID Vi: ");
                String maPIN = InOutput.nhapChuoi("Nhap ma PIN: ");
                return thanhToan(idVi, maPIN, giaVe, giaVe);

            default:
                System.out.println("Lua chon khong hop le!");
                return false;
        }
    }

    /**
     * Hien thi menu thanh toan va cho chon
     * @param customer Khach hang
     * @return true neu thanh toan thanh cong
     */
    public boolean thanhToan(Customer customer) {
        System.out.println();
        InOutput.inTieuDe("CHON PHUONG THUC THANH TOAN");
        System.out.println("1. Tien Mat");
        System.out.println("2. Chuyen Khoan");
        System.out.println("3. Vi");

        int choose = InOutput.chonMenu("Lua chon (1-3): ", 1, 3);
        return chonPhuongThucThanhToan(choose, customer);
    }
}
