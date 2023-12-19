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

    public GrassField(int grassCount, Integer randomSeed, int id) {
        super(id);
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

    public GrassField(int grassCount, int id) {
        this(grassCount, null, id);
    }

    private void updateMapBound(Vector2d pos) {
        Vector2d maxObjectPos = (bounds == null)? pos : bounds.upperRightVector().upperRight(pos);
        Vector2d minObjectPos = (bounds == null)? pos : bounds.lowerLeftVector().lowerLeft(pos);

        bounds = new Boundary(minObjectPos, maxObjectPos);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        super.place(animal);
        updateMapBound(animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection moveDirection) {
        super.move(animal, moveDirection);
        updateMapBound(animal.getPosition());
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
    public Collection <WorldElement> getElements() {
        var elements = super.getElements();
        for (Map.Entry <Vector2d, Grass> entry: grassMap.entrySet())
            elements.add(entry.getValue());

        return elements;
    }

}
