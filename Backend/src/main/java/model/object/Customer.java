package model.object;

import model.inheritance.InCustomer;
import model.inheritance.TickerPrice;
import model.inheritance.User;

public class Customer extends User implements TickerPrice, InCustomer {
    private int khuyenMai;

    //Set gia ve mac dinh
    private double TickerPrice = 25000;

    public Customer(int khuyenMai, String idUser, String nameUser, int phoneUser, int typeUser) {
        super(idUser,nameUser,phoneUser,typeUser);
        this.khuyenMai = khuyenMai;
    }

    public int getkhuyenMai() {
        return khuyenMai;
    }
    public double TickerPrice() {
        return TickerPrice;
    }

    public void setKhuyenMai(int khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    @Override
    public double getTickerPrice() {
        return 0;
    }

    @Override
    public void In() {
    }
}
