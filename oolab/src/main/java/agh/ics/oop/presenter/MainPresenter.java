package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

public class MainPresenter {
    @FXML
    private TextField inputTextField;

    private final SimulationEngine simulationEngine = new SimulationEngine();

    private void configureStage(Stage simulationStage, BorderPane viewSimulation) {
        var scene = new Scene(viewSimulation);

        simulationStage.setScene(scene);
        simulationStage.setTitle("Simulation");

        simulationStage.minWidthProperty().bind(viewSimulation.minWidthProperty());
        simulationStage.minHeightProperty().bind(viewSimulation.minHeightProperty());
    }

    private Simulation configureSimulation(SimulationPresenter presenter) throws IllegalArgumentException {
        List<MoveDirection> directions = getDirections();

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap worldMap = new GrassField(10, 1);

        worldMap.addListener((map, message) -> System.out.println(LocalDateTime.now().toString() + " " + message));

        presenter.setWorldMap(worldMap);

        return new Simulation(positions, directions, worldMap);
    }

    public void onSimulationStartClicked() {
        var loader = new FXMLLoader(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewSimulation;
        try {
            viewSimulation = loader.load();
        } catch (Exception e)  {
            e.printStackTrace();
            return;
        }
        var stage = new Stage();

        configureStage(stage, viewSimulation);

        Simulation simulation;
        try {
            simulation = configureSimulation(loader.getController());
        } catch (IllegalArgumentException e) {
            return;
        }

        stage.show();
        simulationEngine.addAndRun(simulation);
    }

    private List<MoveDirection> getDirections() throws IllegalArgumentException {
        String[] args = inputTextField.getText().split(" ");

        return OptionsParser.parse(args);
    }
}
