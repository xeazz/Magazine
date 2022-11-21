package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class ParserToJson implements Description {
    private final List<Basket> list = new ArrayList<>();

    public String parseJson(Basket basket) {
        list.clear();
        list.add(basket);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        return gson.toJson(list);
    }

    @Override
    public void description() {
        System.out.println("Формат JSON используется для упорядоченного " +
                "хранения данных в процессе обмена между веб-браузером и " +
                "клиентской частью приложения и сервером или между разными серверами.");
    }
}
