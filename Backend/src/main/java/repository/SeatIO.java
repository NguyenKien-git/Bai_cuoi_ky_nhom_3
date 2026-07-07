package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Seat;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SeatIO {
    private static final String FILE_PATH = "data/seats.json";
    private Gson gson;

    public SeatIO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach seat tu file JSON (classpath)
     * @return List<Seat>
     */
    public List<Seat> readSeats() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILE_PATH)))) {
            Type listType = new TypeToken<List<Seat>>(){}.getType();
            List<Seat> seats = gson.fromJson(reader, listType);
            return seats != null ? seats : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file seat that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void writeSeats(List<Seat> seats) {
        System.out.println("[NOTICE] Ghi file khong ho tro voi classpath - chi doc");
    }
}
