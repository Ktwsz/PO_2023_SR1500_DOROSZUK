package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animalMap = new HashMap<>();

    @Override
    public boolean place(Animal animal) {
        animalMap.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animal.equals(objectAt(animal.getPosition()))) return;

        if (direction == MoveDirection.LEFT || direction == MoveDirection.RIGHT) {
            animal.move(direction, this);
            return;
        }

        animalMap.remove(animal.getPosition());
        animal.move(direction, this);
        place(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    @Override
    public Collection <WorldElement> getElements() {
        var elementCollection = new ArrayList<WorldElement>();
        for (Map.Entry <Vector2d, Animal> entry: animalMap.entrySet())
            elementCollection.add(entry.getValue());

        return elementCollection;
    }
}
