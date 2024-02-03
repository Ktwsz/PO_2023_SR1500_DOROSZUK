package agh.ics.oop.model;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class WorldElementBox {

    private final VBox cell;
    private final int CELL_HEIGHT = 20;
    private final int CELL_WIDTH = 20;
    public WorldElementBox(WorldElement worldElement) {
        var imageView = new ImageView(worldElement.getTexture());
        imageView.setFitHeight(CELL_HEIGHT);
        imageView.setFitWidth(CELL_WIDTH);

        var label = new Label(worldElement.getPosition().toString());

        cell = new VBox();
        cell.setAlignment(Pos.CENTER);
        cell.getChildren().add(imageView);
        cell.getChildren().add(label);
    }

    public VBox getCell() {
        return cell;
    }
}
