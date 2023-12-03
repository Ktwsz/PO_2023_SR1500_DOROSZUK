package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap <Animal, Vector2d>{
    private final Map<Vector2d, Animal> animals = new HashMap<>();
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
        if (isOccupied(animal.getPosition())) return false;

        animals.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animal.equals(objectAt(animal.getPosition()))) return;

        if (direction == MoveDirection.LEFT || direction == MoveDirection.RIGHT) {
            animal.move(direction, this);
            return;
        }

        animals.remove(animal.getPosition());
        animal.move(direction, this);
        place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(LOWER_LEFT_VECTOR) && position.precedes(UPPER_RIGHT_VECTOR) && !isOccupied(position);
    }
}
