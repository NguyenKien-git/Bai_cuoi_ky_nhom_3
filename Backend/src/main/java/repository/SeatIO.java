package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Seat;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SeatIO {
    private String filePath;
    private Gson gson;

    public SeatIO() {
        String basePath = System.getProperty("user.dir");
        this.filePath = basePath + "/Backend/src/main/java/data/seats.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public SeatIO(String customPath) {
        this.filePath = customPath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach seat tu file JSON
     * @return List<Seat>
     */
    public List<Seat> readSeats() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Seat>>(){}.getType();
            List<Seat> seats = gson.fromJson(reader, listType);
            return seats != null ? seats : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file seat that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Ghi danh sach seat vao file JSON
     * @param seats Danh sach seat
     */
    public void writeSeats(List<Seat> seats) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(seats, writer);
            System.out.println("Ghi file seat thanh cong!");
        } catch (IOException e) {
            System.out.println("Ghi file seat that bai: " + e.getMessage());
        }
    }
}
