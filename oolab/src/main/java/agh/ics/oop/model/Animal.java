package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
    private final Vector2d UPPER_RIGHT_VECTOR = new Vector2d(4, 4);
    private final Vector2d LOWER_LEFT_VECTOR = new Vector2d(0, 0);
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d position;

    public Animal() {
        position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return switch (direction) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return direction == animal.direction && Objects.equals(position, animal.position);
    }
    @Override
    public int hashCode() {
        return Objects.hash(direction, position);
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    public boolean isFacing(MapDirection direction) {
        return this.direction.equals(direction);
    }

    public void move(MoveDirection direction, MoveValidator moveValidator) {
        if (direction == MoveDirection.LEFT) {
            this.direction = this.direction.previous();
            return;
        }
        if (direction == MoveDirection.RIGHT) {
            this.direction = this.direction.next();
            return;
        }

        Vector2d moveVector = this.direction.toUnitVector();

        if (direction == MoveDirection.BACKWARD) moveVector = moveVector.opposite();

        Vector2d newPosition = position.add(moveVector);

        if (moveValidator.canMoveTo(newPosition)) position = newPosition;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return direction;
    }
}
