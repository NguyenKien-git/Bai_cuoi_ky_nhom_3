package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Customer;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserIO {
    private static final String FILE_PATH = "data/users.json";
    private Gson gson;

    public UserIO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach user tu file JSON (classpath)
     * @return List<Customer>
     */
    public List<Customer> readUsers() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILE_PATH)))) {
            Type listType = new TypeToken<List<Customer>>(){}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);
            return customers != null ? customers : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file user that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Ghi danh sach user vao file JSON
     * @param customers Danh sach customer
     */
    public void writeUsers(List<Customer> customers) {
        System.out.println("[NOTICE] Ghi file khong ho tro voi classpath - chi doc");
    }
}
