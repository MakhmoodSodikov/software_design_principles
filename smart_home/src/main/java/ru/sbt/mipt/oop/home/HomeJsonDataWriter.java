package ru.sbt.mipt.oop.home;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HomeJsonDataWriter implements HomeDataWriter {
    private final Path path;
    private final String jsonString;

    public HomeJsonDataWriter(String path, String jsonString) {
        this.jsonString = jsonString;
        this.path = Paths.get(path);
    }

    public boolean writeData() {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
