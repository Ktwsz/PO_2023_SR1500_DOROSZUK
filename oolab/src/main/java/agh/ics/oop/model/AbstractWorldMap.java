package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

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
        if (objectAt(animal.getPosition()).filter(Animal.class::isInstance).isPresent()) throw new PositionAlreadyOccupiedException(animal.getPosition());

        animalMap.put(animal.getPosition(), animal);
        mapChanged("Placed animal at %s".formatted(animal.getPosition()));
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        if (objectAt(animal.getPosition()).filter(animal::equals).isEmpty()) return;

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
        return objectAt(position).isPresent();
    }

    @Override
    public Optional <WorldElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animalMap.get(position));
    }

    @Override
    public Collection <WorldElement> getElements() {
        return animalMap.values()
                        .stream()
                        .map(WorldElement.class::cast)
                        .collect(Collectors.toList());
    }

    @Override
    public Collection <Animal> getOrderedAnimals() {
        return animalMap.values()
                        .stream()
                        .sorted((a1, a2) -> a1.getPosition().getX() != a2.getPosition().getX()? a1.getPosition().getX() - a2.getPosition().getX() : a1.getPosition().getY() - a2.getPosition().getY())
                        .collect(Collectors.toList());
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
