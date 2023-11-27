package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void placeTest() {
        var map = new GrassField(10, 1);
        List<Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)));
        map.place(animals.get(0));
        map.place(animals.get(1));
        map.place(animals.get(2));

        assertEquals(animals.get(0), map.objectAt(new Vector2d(1, 1)));
        assertEquals(animals.get(1), map.objectAt(new Vector2d(0, 0)));
        assertEquals(animals.get(2), map.objectAt(new Vector2d(0, 4)));

        assertTrue(map.isOccupied(animals.get(0).getPosition()));
        assertTrue(map.isOccupied(animals.get(1).getPosition()));
        assertTrue(map.isOccupied(animals.get(2).getPosition()));

        assertEquals(new Grass(new Vector2d(5, 0)), map.objectAt(new Vector2d(5, 0)));
        assertEquals(new Grass(new Vector2d(2, 3)), map.objectAt(new Vector2d(2, 3)));
        assertEquals(new Grass(new Vector2d(2, 2)), map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    void moveTest() {
        var map = new GrassField(10, 1);
        List<Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)));

        map.place(animals.get(0));
        map.place(animals.get(1));
        map.place(animals.get(2));

        map.move(animals.get(1), MoveDirection.FORWARD);
        map.move(animals.get(0), MoveDirection.LEFT);
        map.move(animals.get(0), MoveDirection.FORWARD);

        map.move(animals.get(2), MoveDirection.FORWARD);
        map.move(animals.get(2), MoveDirection.FORWARD);

        assertTrue(map.isOccupied(new Vector2d(0, 1)));
        assertTrue(map.isOccupied(new Vector2d(0, 6)));
        assertTrue(map.isOccupied(new Vector2d(0, 1)));

        assertEquals(animals.get(1), map.objectAt(new Vector2d(0, 1)));
        assertEquals(animals.get(0), map.objectAt(new Vector2d(1, 1)));
        assertEquals(animals.get(2), map.objectAt(new Vector2d(0, 6)));

        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }
}
