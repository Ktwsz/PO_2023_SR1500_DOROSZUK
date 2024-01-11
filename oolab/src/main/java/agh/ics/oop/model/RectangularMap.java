package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

public class RectangularMap extends AbstractWorldMap{

    private final int width;
    private final int height;

    private final Vector2d LOWER_LEFT_VECTOR = new Vector2d(0, 0);
    private final Vector2d UPPER_RIGHT_VECTOR;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        UPPER_RIGHT_VECTOR = new Vector2d(width-1, height-1);
    }

    @Override
    public String toString() {
        var mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(LOWER_LEFT_VECTOR, UPPER_RIGHT_VECTOR);
    }

    @Override
    public boolean place(Animal animal) {
        if (super.isOccupied(animal.getPosition())) return false;

        return super.place(animal);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(LOWER_LEFT_VECTOR) && position.precedes(UPPER_RIGHT_VECTOR) && !super.isOccupied(position);
    }
}
