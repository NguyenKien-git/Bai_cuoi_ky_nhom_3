package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Customer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserIO {
    private String filePath;
    private Gson gson;

    public UserIO() {
        String basePath = System.getProperty("user.dir");
        this.filePath = basePath + "/Backend/src/main/java/data/users.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public UserIO(String customPath) {
        this.filePath = customPath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach user tu file JSON
     * @return List<Customer>
     */
    public List<Customer> readUsers() {
        try (FileReader reader = new FileReader(filePath)) {
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
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(customers, writer);
            System.out.println("Ghi file user thanh cong!");
        } catch (IOException e) {
            System.out.println("Ghi file user that bai: " + e.getMessage());
        }
    }
}
