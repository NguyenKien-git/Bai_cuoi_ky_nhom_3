package model.object;

public class NomalUser extends Customer {
    public NomalUser(int khuyenMai, String idUser, String nameUser, int phoneUser, int typeUser){
        super(khuyenMai,idUser,nameUser,phoneUser,typeUser);
    }

    @Override
    public double getTickerPrice() {
        double basePrice = super.TickerPrice();
        return basePrice - basePrice * (getkhuyenMai() / 100.0);
    }

    @Override
    public void In() {
        System.out.println("| " + idUser() + " | " + nameUser() + " | " + phoneUser() + " | " + typeUser() + " | Khuyen Mai: " + getkhuyenMai() + "% |");
    }
}
