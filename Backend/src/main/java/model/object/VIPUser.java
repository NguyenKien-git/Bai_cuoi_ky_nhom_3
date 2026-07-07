package model.object;

public class VIPUser extends Customer {
    private double phuPhi = 0.25;

    public VIPUser(int khuyenMai, String idUser, String nameUser, int phoneUser, int typeUser){
        super(khuyenMai,idUser,nameUser,phoneUser,typeUser);
    }

    @Override
    public double getTickerPrice() {
        double basePrice = super.TickerPrice();
        return basePrice * (1 + phuPhi) - basePrice * (getkhuyenMai() / 100.0);
    }

    @Override
    public void In() {
        System.out.println("| " + idUser() + " | " + nameUser() + " | " + phoneUser() + " | " + typeUser() + " | Khuyen Mai: " + getkhuyenMai() + "% | Phu Phi: " + (phuPhi * 100) + "% |");
    }
}
