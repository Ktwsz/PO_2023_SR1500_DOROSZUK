package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d, Grass> grassMap = new HashMap<>();

    private final Vector2d LOWER_LEFT_VECTOR = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private final Vector2d UPPER_RIGHT_VECTOR = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    private Vector2d maxObjectPos;
    private Vector2d minObjectPos;

    public GrassField(int grassCount, Integer randomSeed) {
        var generator = (randomSeed != null)? new Random(randomSeed) : new Random();
        int upperBound = (int)Math.ceil(Math.sqrt(grassCount * 10));

        for (int i = 0; i < grassCount; ++i) {
            Vector2d newPos;
            do {
                int randX = generator.nextInt(upperBound), randY = generator.nextInt(upperBound);
                newPos = new Vector2d(randX, randY);
            } while (super.isOccupied(newPos));

            grassMap.put(newPos, new Grass(newPos));
            updateMapBound(newPos);
        }
    }

    public GrassField(int grassCount) {
        this(grassCount, null);
    }

    private void updateMapBound(Vector2d pos) {
        maxObjectPos = (maxObjectPos == null)? pos : maxObjectPos.upperRight(pos);
        minObjectPos = (minObjectPos == null)? pos : minObjectPos.lowerLeft(pos);
    }

    @Override
    public boolean place(Animal animal) {
        if (objectAt(animal.getPosition()) instanceof Animal) return false;

        updateMapBound(animal.getPosition());

        return super.place(animal);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return (animalMap.containsKey(position))? animalMap.get(position) : grassMap.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(LOWER_LEFT_VECTOR) && position.precedes(UPPER_RIGHT_VECTOR) && !(objectAt(position) instanceof Animal);
    }

    @Override
    public String toString() {
        var mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(minObjectPos, maxObjectPos);
    }

    @Override
    public Collection <WorldElement> getElements() {
        var elements = super.getElements();
        for (Map.Entry <Vector2d, Grass> entry: grassMap.entrySet())
            elements.add(entry.getValue());

        return elements;
    }

}
