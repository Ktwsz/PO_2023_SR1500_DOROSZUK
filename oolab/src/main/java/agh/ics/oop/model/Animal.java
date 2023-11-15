package agh.ics.oop.model;

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
        return "pozycja: %s kierunek: %s".formatted(position, direction);
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
    public boolean isFacing(MapDirection direction) {
        return this.direction.equals(direction);
    }

    public void move(MoveDirection direction) {
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
        position = position.add(moveVector).lowerLeft(UPPER_RIGHT_VECTOR).upperRight(LOWER_LEFT_VECTOR);
    }
}
