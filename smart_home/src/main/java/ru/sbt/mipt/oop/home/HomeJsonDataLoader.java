package ru.sbt.mipt.oop.home;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeJsonDataLoader implements HomeDataLoader {
    private String filePath;

    public HomeJsonDataLoader(String filePath){
        this.filePath = filePath;
    }

    public SmartHome loadData() {
        Gson gson = new Gson();
        String json = null;

        try {
            json = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gson.fromJson(json, SmartHome.class);
    }
}
