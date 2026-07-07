package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Payment;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PaymentIO {
    private String filePath;
    private Gson gson;

    public PaymentIO() {
        String basePath = System.getProperty("user.dir");
        this.filePath = basePath + "/Backend/src/main/java/data/payments.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public PaymentIO(String customPath) {
        this.filePath = customPath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach payment tu file JSON
     * @return List<Payment>
     */
    public List<Payment> readPayments() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Payment>>(){}.getType();
            List<Payment> payments = gson.fromJson(reader, listType);
            return payments != null ? payments : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file payment that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Ghi danh sach payment vao file JSON
     * @param payments Danh sach payment
     */
    public void writePayments(List<Payment> payments) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(payments, writer);
            System.out.println("Ghi file payment thanh cong!");
        } catch (IOException e) {
            System.out.println("Ghi file payment that bai: " + e.getMessage());
        }
    }
}
