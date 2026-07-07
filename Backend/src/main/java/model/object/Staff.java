package model.object;

import model.inheritance.User;

public class Staff extends User {
    public Staff(String idUser, String nameUser, int phoneUser, int typeUser){
        super(idUser,nameUser,phoneUser,typeUser);
    }
}
