package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Payment;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PaymentIO {
    private static final String FILE_PATH = "data/payments.json";
    private Gson gson;

    public PaymentIO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach payment tu file JSON (classpath)
     * @return List<Payment>
     */
    public List<Payment> readPayments() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILE_PATH)))) {
            Type listType = new TypeToken<List<Payment>>(){}.getType();
            List<Payment> payments = gson.fromJson(reader, listType);
            return payments != null ? payments : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file payment that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void writePayments(List<Payment> payments) {
        System.out.println("[NOTICE] Ghi file khong ho tro voi classpath - chi doc");
    }
}
