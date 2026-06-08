package Thanh_toan;

public class Tien_mat implements Phuong_Thuc_Thanh_Toan {

    public boboolean thanh_Toan(double tong_Tien){
        System.out.println("Thanh toan tien mat: " + tong_Tien + " VND");
        System.out.println("Nhan tien thanh cong!");
        return true;
    }

    @Override
    public String getTen_Phuong_Thuc() {
        return "Tien mat";
    }
}