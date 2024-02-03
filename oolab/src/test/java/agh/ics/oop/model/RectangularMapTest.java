package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    void placeTest() {
        var map = new RectangularMap(4, 5, 1);
        List <Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)));
        assertDoesNotThrow(() -> map.place(animals.get(0)));
        assertDoesNotThrow(() -> map.place(animals.get(1)));
        assertDoesNotThrow(() -> map.place(animals.get(2)));

        assertEquals(animals.get(0), map.objectAt(new Vector2d(1, 1)).get());
        assertEquals(animals.get(1), map.objectAt(new Vector2d(0, 0)).get());
        assertEquals(animals.get(2), map.objectAt(new Vector2d(0, 4)).get());

        assertTrue(map.isOccupied(animals.get(0).getPosition()));
        assertTrue(map.isOccupied(animals.get(1).getPosition()));
        assertTrue(map.isOccupied(animals.get(2).getPosition()));

        assertFalse(map.isOccupied(new Vector2d(0, 1)));
        assertFalse(map.isOccupied(new Vector2d(1, 0)));
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void moveTest() {
        var map = new RectangularMap(4, 5, 1);
        List <Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)));
        assertDoesNotThrow(() -> map.place(animals.get(0)));
        assertDoesNotThrow(() -> map.place(animals.get(1)));
        assertDoesNotThrow(() -> map.place(animals.get(2)));

        map.move(animals.get(1), MoveDirection.FORWARD);
        map.move(animals.get(0), MoveDirection.LEFT);
        map.move(animals.get(0), MoveDirection.FORWARD);

        map.move(animals.get(2), MoveDirection.FORWARD);
        map.move(animals.get(2), MoveDirection.FORWARD);

        assertTrue(map.isOccupied(new Vector2d(0, 1)));
        assertTrue(map.isOccupied(new Vector2d(0, 4)));
        assertTrue(map.isOccupied(new Vector2d(0, 1)));

        assertEquals(animals.get(1), map.objectAt(new Vector2d(0, 1)).get());
        assertEquals(animals.get(0), map.objectAt(new Vector2d(1, 1)).get());
        assertEquals(animals.get(2), map.objectAt(new Vector2d(0, 4)).get());

        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }
}
