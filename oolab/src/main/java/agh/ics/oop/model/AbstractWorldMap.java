package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animalMap = new HashMap<>();
    protected List<MapChangeListener> listenersList = new ArrayList<>();
    protected Boundary bounds;
    private final int id;

    public AbstractWorldMap(int id) {
        this.id = id;
    }


    private void mapChanged(String message) {
        for (MapChangeListener listener : listenersList)
            listener.mapChanged(this, message);
    }

    @Override
    public void addListener(MapChangeListener listener) {
        listenersList.add(listener);
    }

    @Override
    public void removeListener(MapChangeListener listener) {
        listenersList.remove(listener);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        if (objectAt(animal.getPosition()) instanceof Animal) throw new PositionAlreadyOccupiedException(animal.getPosition());

        animalMap.put(animal.getPosition(), animal);
        mapChanged("Placed animal at %s".formatted(animal.getPosition()));
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (!animal.equals(objectAt(animal.getPosition()))) return;

        if (direction == MoveDirection.LEFT || direction == MoveDirection.RIGHT) {
            animal.move(direction, this);
            mapChanged("Animal %s is now facing %s".formatted(animal.getPosition(), animal.getDirection()));
            return;
        }

        Vector2d oldPosition = animal.getPosition();
        animalMap.remove(animal.getPosition());
        animal.move(direction, this);
        animalMap.put(animal.getPosition(), animal);
        if (!oldPosition.equals(animal.getPosition())) mapChanged("Animal moved from %s to %s".formatted(oldPosition, animal.getPosition()));
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

    @Override
    public Boundary getCurrentBounds() {
        return bounds;
    }

    @Override
    public String toString() {
        var mapVisualizer = new MapVisualizer(this);
        Boundary mapBoundary = getCurrentBounds();
        return mapVisualizer.draw(mapBoundary.lowerLeftVector(), mapBoundary.upperRightVector());
    }

    @Override
    public int getId() {
        return id;
    }
}
