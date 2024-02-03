package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void placeTest() {
        var map = new GrassField(10, 1, 1);
        List<Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)));

        assertDoesNotThrow(() -> map.place(animals.get(0)));
        assertDoesNotThrow(() -> map.place(animals.get(1)));
        assertDoesNotThrow(() -> map.place(animals.get(2)));

        assertEquals(animals.get(0), map.objectAt(new Vector2d(1, 1)).get());
        assertEquals(animals.get(1), map.objectAt(new Vector2d(0, 0)).get());
        assertEquals(animals.get(2), map.objectAt(new Vector2d(0, 4)).get());

        assertTrue(map.isOccupied(animals.get(0).getPosition()));
        assertTrue(map.isOccupied(animals.get(1).getPosition()));
        assertTrue(map.isOccupied(animals.get(2).getPosition()));

        assertEquals(new Grass(new Vector2d(2, 4)), map.objectAt(new Vector2d(2, 4)).get());
        assertEquals(new Grass(new Vector2d(4, 4)), map.objectAt(new Vector2d(4, 4)).get());
        assertEquals(new Grass(new Vector2d(2, 2)), map.objectAt(new Vector2d(2, 2)).get());
    }

    @Test
    void placeExceptionTest() {
        var map = new GrassField(10, 1, 1);
        List<Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(1, 1)));

        assertDoesNotThrow(() -> map.place(animals.get(0)));

        Exception exception1 = assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animals.get(1)));
        assertEquals("Position (1,1) is already occupied", exception1.getMessage());

        Exception exception2 = assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animals.get(2)));
        assertEquals("Position (1,1) is already occupied", exception2.getMessage());
    }

    @Test
    void moveTest() {
        var map = new GrassField(10, 1, 1);
        List<Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)));

        assertDoesNotThrow(() -> map.place(animals.get(0)));
        assertDoesNotThrow(() -> map.place(animals.get(1)));
        assertDoesNotThrow(() -> map.place(animals.get(2)));

        map.move(animals.get(1), MoveDirection.FORWARD);
        map.move(animals.get(0), MoveDirection.LEFT);
        map.move(animals.get(0), MoveDirection.FORWARD);

        map.move(animals.get(2), MoveDirection.FORWARD);
        map.move(animals.get(2), MoveDirection.FORWARD);

        assertTrue(map.isOccupied(new Vector2d(0, 1)));
        assertTrue(map.isOccupied(new Vector2d(0, 6)));
        assertTrue(map.isOccupied(new Vector2d(0, 1)));

        assertEquals(animals.get(1), map.objectAt(new Vector2d(0, 1)).get());
        assertEquals(animals.get(0), map.objectAt(new Vector2d(1, 1)).get());
        assertEquals(animals.get(2), map.objectAt(new Vector2d(0, 6)).get());

        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }

    @Test
    void getOrderedAnimalsTest() {
        var map = new GrassField(10, 1, 1);
        List<Animal> animals = List.of(new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)), new Animal(new Vector2d(5, 5)));

        assertDoesNotThrow(() -> map.place(animals.get(0)));
        assertDoesNotThrow(() -> map.place(animals.get(1)));
        assertDoesNotThrow(() -> map.place(animals.get(2)));
        assertDoesNotThrow(() -> map.place(animals.get(3)));

        assertEquals(List.of(new Animal(new Vector2d(0, 0)), new Animal(new Vector2d(0, 4)), new Animal(new Vector2d(1, 1)), new Animal(new Vector2d(5, 5))), map.getOrderedAnimals());
    }
}
