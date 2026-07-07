package model.inheritance;

public abstract class User {
    private String idUser;
    private String nameUser;
    private int phoneUser;
    private int typeUser;

    public User() {}

    public User(String idUser, String nameUser, int phoneUser, int typeUser) {
        this.idUser = idUser;
        this.phoneUser = phoneUser;
        this.nameUser = nameUser;
        this.typeUser = typeUser;
    }

    //Getter
    public String idUser() {
        return idUser;
    }
    public String nameUser() {
        return nameUser;
    }
    public int phoneUser() {
        return phoneUser;
    }
    public int typeUser() {
        return typeUser;
    }

    //Setter
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public void setPhoneUser(int phoneUser) {
        this.phoneUser = phoneUser;
    }
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }
}
