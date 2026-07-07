package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.object.Ticker;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TickerIO {
    private static final String FILE_PATH = "data/tickers.json";
    private Gson gson;

    public TickerIO() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Doc danh sach ticket tu file JSON (classpath)
     * @return List<Ticker>
     */
    public List<Ticker> readTickers() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(FILE_PATH)))) {
            Type listType = new TypeToken<List<Ticker>>(){}.getType();
            List<Ticker> tickers = gson.fromJson(reader, listType);
            return tickers != null ? tickers : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Doc file ticket that bai: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void writeTickers(List<Ticker> tickers) {
        System.out.println("[NOTICE] Ghi file khong ho tro voi classpath - chi doc");
    }
}
