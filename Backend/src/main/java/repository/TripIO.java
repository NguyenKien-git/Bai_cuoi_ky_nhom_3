package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Trip;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TripIO {
    private String filePath;
    private Gson gson;

    public TripIO() {
        String basePath = System.getProperty("user.dir");
        this.filePath = basePath + "/Backend/src/main/java/data/trips.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public TripIO(String customPath) {
        this.filePath = customPath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach trip tu file JSON
     * @return List<Trip>
     */
    public List<Trip> readTrips() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Trip>>(){}.getType();
            List<Trip> trips = gson.fromJson(reader, listType);
            return trips != null ? trips : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file trip that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Ghi danh sach trip vao file JSON
     * @param trips Danh sach trip
     */
    public void writeTrips(List<Trip> trips) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(trips, writer);
            System.out.println("Ghi file trip thanh cong!");
        } catch (IOException e) {
            System.out.println("Ghi file trip that bai: " + e.getMessage());
        }
    }
}
