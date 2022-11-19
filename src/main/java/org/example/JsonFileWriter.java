package org.example;
import java.io.FileWriter;
import java.io.IOException;

public class JsonFileWriter {
    public void outputJson(String jsonObject) {
        try (FileWriter file = new FileWriter("cart.json", false)) {
            file.write(jsonObject);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
