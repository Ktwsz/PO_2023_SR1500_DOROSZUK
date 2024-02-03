package agh.ics.oop.model;

import java.io.*;

public class FileMapDisplay implements MapChangeListener {
    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        String fileName = "map_%d.log".formatted(worldMap.getId());
        if (!fileExists(fileName)) createLogFile(fileName);

        writeLog(fileName, message);
    }

    private boolean fileExists(String fileName) {
        var f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }

    private void createLogFile(String fileName) {
        var file = new File(fileName);
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLog(String fileName, String message) {
        try (var fr = new FileWriter(fileName, true)) {
            var writer = new BufferedWriter(fr);
            writer.append(message);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
