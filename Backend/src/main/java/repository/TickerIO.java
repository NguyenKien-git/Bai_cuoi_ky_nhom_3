package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Ticker;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TickerIO {
    private String filePath;
    private Gson gson;

    public TickerIO() {
        String basePath = System.getProperty("user.dir");
        this.filePath = basePath + "/Backend/src/main/java/data/tickers.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public TickerIO(String customPath) {
        this.filePath = customPath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach ticket tu file JSON
     * @return List<Ticker>
     */
    public List<Ticker> readTickers() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Ticker>>(){}.getType();
            List<Ticker> tickers = gson.fromJson(reader, listType);
            return tickers != null ? tickers : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file ticket that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Ghi danh sach ticket vao file JSON
     * @param tickers Danh sach ticket
     */
    public void writeTickers(List<Ticker> tickers) {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(tickers, writer);
            System.out.println("Ghi file ticket thanh cong!");
        } catch (IOException e) {
            System.out.println("Ghi file ticket that bai: " + e.getMessage());
        }
    }
}
