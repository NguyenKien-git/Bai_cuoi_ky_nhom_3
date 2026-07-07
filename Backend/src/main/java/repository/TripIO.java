package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Trip;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TripIO {
    private static final String FILE_PATH = "data/trips.json";
    private Gson gson;

    public TripIO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach trip tu file JSON (classpath)
     * @return List<Trip>
     */
    public List<Trip> readTrips() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILE_PATH)))) {
            Type listType = new TypeToken<List<Trip>>(){}.getType();
            List<Trip> trips = gson.fromJson(reader, listType);
            return trips != null ? trips : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file trip that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void writeTrips(List<Trip> trips) {
        System.out.println("[NOTICE] Ghi file khong ho tro voi classpath - chi doc");
    }
}
