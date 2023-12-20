package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private GridPane simulationGrid;

    @FXML
    private Label moveDescLabel;

    private WorldMap worldMap;

    private final double CELL_WIDTH = 50;
    private final double CELL_HEIGHT = 50;

    public void setWorldMap(WorldMap map) {
        worldMap = map;
        worldMap.addListener(this);
    }


    private int mapGridX(int x, Boundary bounds) {
        return x - bounds.lowerLeftVector().getX() + 1;
    }

    private int mapGridY(int y, Boundary bounds) {
        int height = bounds.upperRightVector().subtract(bounds.lowerLeftVector()).getY();
        return height - (y - bounds.lowerLeftVector().getY()) + 1;
    }

    private void addLabel(String text, int x, int y) {
        var y_x_label = new Label(text);
        y_x_label.setMinSize(CELL_WIDTH, CELL_HEIGHT);
        y_x_label.setAlignment(Pos.CENTER);
        simulationGrid.add(y_x_label, x, y);
    }

    public void drawMap(WorldMap map) {
        if (!simulationGrid.getChildren().isEmpty()) {
            simulationGrid.getChildren().retainAll(simulationGrid.getChildren().get(0)); // hack to retain visible grid lines
        }

        Boundary bounds = map.getCurrentBounds();
        IntConsumer addXLabel = x -> addLabel(Integer.toString(x), mapGridX(x, bounds), 0);

        IntConsumer addYLabel = y -> addLabel(Integer.toString(y), 0, mapGridY(y, bounds));
        addLabel("y \\ x", 0, 0);

        IntStream.range(bounds.lowerLeftVector().getX(), bounds.upperRightVector().getX() + 1)
                 .forEach(addXLabel);

        IntStream.range(bounds.lowerLeftVector().getY(), bounds.upperRightVector().getY() + 1)
                 .forEach(addYLabel);

        for (int x = bounds.lowerLeftVector().getX(); x <= bounds.upperRightVector().getX(); x++) {
            for (int y = bounds.lowerLeftVector().getY(); y <= bounds.upperRightVector().getY(); y++) {
                var optElem = map.objectAt(new Vector2d(x, y));
                if (optElem.isPresent()) {
                    var elem = optElem.get();
                    addLabel(elem.toString(), mapGridX(x, bounds), mapGridY(y, bounds));
                }
            }
        }
    }

    @Override
    public void mapChanged(WorldMap map, String message) {
        Platform.runLater(() -> {
            drawMap(map);
            updateMoveDesc(message);
        });
    }

    private void updateMoveDesc(String message) {
        moveDescLabel.setText(message);
    }
}
