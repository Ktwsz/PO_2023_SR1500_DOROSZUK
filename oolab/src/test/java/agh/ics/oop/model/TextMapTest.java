package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextMapTest {
    @Test
    public void moveTest() {
        TextMap map = new TextMap();
        map.place("sushi");
        map.place("tofu");
        map.place("makarena");

        map.move("sushi", MoveDirection.FORWARD);
        map.move("tofu", MoveDirection.FORWARD);
        map.move("makarena", MoveDirection.FORWARD);

        assertEquals("sushi", map.objectAt(0));
        assertEquals("tofu", map.objectAt(1));
        assertEquals("makarena", map.objectAt(2));

        map.move("sushi", MoveDirection.RIGHT);
        map.move("tofu", MoveDirection.RIGHT);
        map.move("makarena", MoveDirection.LEFT);

        map.move("sushi", MoveDirection.FORWARD);
        map.move("tofu", MoveDirection.BACKWARD);
        map.move("makarena", MoveDirection.BACKWARD);

        assertEquals("tofu", map.objectAt(0));
        assertEquals("sushi", map.objectAt(1));
        assertEquals("makarena", map.objectAt(2));
    }
}
