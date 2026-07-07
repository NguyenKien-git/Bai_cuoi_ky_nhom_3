package service;

import model.object.Customer;
import model.object.MemberUser;
import model.object.NomalUser;
import model.object.VIPUser;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser {
    private List<Customer> customers = new ArrayList<>();
    private int userCount = 0;

    /**
     * Tạo user dựa trên loại chọn
     * @param choose 1 = NomalUser, 2 = MemberUser, 3 = VIPUser
     * @param customer Thông tin customer (không cần truyền idUser)
     * @return Customer đã tạo, null nếu choose không hợp lệ
     */
    public Customer createUser(int choose, Customer customer) {
        if (customer == null) {
            return null;
        }

        userCount++;
        String idUser = "U" + userCount;

        Customer newCustomer = null;

        switch (choose) {
            case 1:
                newCustomer = new NomalUser(
                        customer.getkhuyenMai(),
                        idUser,
                        customer.nameUser(),
                        customer.phoneUser(),
                        customer.typeUser()
                );
                break;
            case 2:
                newCustomer = new MemberUser(
                        customer.getkhuyenMai(),
                        idUser,
                        customer.nameUser(),
                        customer.phoneUser(),
                        customer.typeUser()
                );
                break;
            case 3:
                newCustomer = new VIPUser(
                        customer.getkhuyenMai(),
                        idUser,
                        customer.nameUser(),
                        customer.phoneUser(),
                        customer.typeUser()
                );
                break;
            default:
                userCount--;
                System.out.println("Lua chon chi trong khoang 1-3");
                return null;
        }

        customers.add(newCustomer);
        return newCustomer;
    }

    /**
     * Tìm user theo idUser
     * @param idUser ID của user cần tìm
     * @return Customer tìm được, null nếu không tìm thấy
     */
    public Customer findUserById(String idUser) {
        for (Customer customer : customers) {
            if (customer.idUser().equals(idUser)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * In thông tin một user
     * @param customer Customer cần in
     * @return true nếu tìm thấy, false nếu không
     */
    public boolean InUser(Customer customer) {
        if (customer == null) {
            return false;
        }
        customer.In();
        return true;
    }

    /**
     * In thông tin một user theo idUser
     * @param idUser ID của user cần in
     * @return true nếu tìm thấy, false nếu không
     */
    public boolean InUser(String idUser) {
        Customer customer = findUserById(idUser);
        if (customer == null) {
            System.out.println("Khong tim thay user co id: " + idUser);
            return false;
        }
        customer.In();
        return true;
    }

    /**
     * In tất cả user
     */
    public void InAllUsers() {
        if (customers.isEmpty()) {
            System.out.println("Danh sach user trong!");
            return;
        }
        System.out.println("=============================== DANH SACH USER ===============================");
        for (Customer customer : customers) {
            customer.In();
        }
        System.out.println("==========================================================================");
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
