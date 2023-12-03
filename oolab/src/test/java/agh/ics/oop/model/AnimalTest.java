package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    class TestMoveValidator implements MoveValidator {
        @Override
        public boolean canMoveTo(Vector2d pos) {
            return true;
        }
    }
    @Test
    void toStringTest() {
        var moveValidator = new TestMoveValidator();
        var anim = new Animal();
        assertEquals("^", anim.toString());
        anim.move(MoveDirection.LEFT, moveValidator);
        assertEquals("<", anim.toString());
        anim.move(MoveDirection.LEFT, moveValidator);
        assertEquals("v", anim.toString());
        anim.move(MoveDirection.LEFT, moveValidator);
        assertEquals(">", anim.toString());
    }

    @Test
    void testEquals() {
        var anim = new Animal();
        var anim1 = new Animal();
        var anim2 = new Animal(new Vector2d(1, 1));
        var anim3 = new Animal();
        anim3.move(MoveDirection.LEFT, new TestMoveValidator());

        assertEquals(anim, anim1);
        assertNotEquals(anim, anim2);
        assertNotEquals(anim, anim3);
        assertNotEquals(anim, null);
        assertNotEquals(anim, "testy testy");
    }

    @Test
    void testIsAt() {
        var anim = new Animal();
        assertTrue(anim.isAt(new Vector2d(2, 2)));
        assertFalse(anim.isAt(new Vector2d(1, 1)));
    }

    @Test
    void testIsFacing() {
        var anim = new Animal();
        assertTrue(anim.isFacing(MapDirection.NORTH));
        assertFalse(anim.isFacing(MapDirection.SOUTH));
    }

    @Test
    void moveTest() {
        var anim = new Animal();
        var moveValidator = new TestMoveValidator();

        anim.move(MoveDirection.FORWARD, moveValidator);
        anim.move(MoveDirection.FORWARD, moveValidator);
        anim.move(MoveDirection.FORWARD, moveValidator);
        anim.move(MoveDirection.RIGHT, moveValidator);
        anim.move(MoveDirection.BACKWARD, moveValidator);
        anim.move(MoveDirection.BACKWARD, moveValidator);

        assertEquals(new Vector2d(0, 5), anim.getPosition());
        assertEquals(MapDirection.EAST, anim.getDirection());
    }
}
