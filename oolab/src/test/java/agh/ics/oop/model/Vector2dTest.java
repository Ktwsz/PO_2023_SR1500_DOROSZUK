package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        var v1 = new Vector2d(11, 13);

        assertTrue(v1.equals(new Vector2d(11, 13)));
        assertTrue(v1.equals(v1));

        assertFalse(v1.equals(new Vector2d(0, 0)));
        assertFalse(v1.equals(new Vector2d(11, 0)));
        assertFalse(v1.equals(new Vector2d(0, 13)));
        assertFalse(v1.equals(new String("test")));
    }

    @Test
    public void testToString() {
        assertEquals("(2,1)", new Vector2d(2, 1).toString());
    }

    @Test
    public void testPrecedes() {
        var v1 = new Vector2d(2, 2);

        assertFalse(v1.precedes(new Vector2d(1, 1)));
        assertTrue(v1.precedes(new Vector2d(2, 3)));
    }

    @Test
    public void testFollows() {
        var v1 = new Vector2d(2, 2);

        assertTrue(v1.follows(new Vector2d(1, 1)));
        assertFalse(v1.follows(new Vector2d(2, 3)));
    }

    @Test
    public void testUpperRight() {
        var v1 = new Vector2d(2, 1);
        assertEquals(new Vector2d(2, 2), v1.upperRight(new Vector2d(1, 2)));
    }

    @Test
    public void testLowerLeft() {
        var v1 = new Vector2d(2, 1);
        assertEquals(new Vector2d(1, 1), v1.lowerLeft(new Vector2d(1, 2)));
    }

    @Test
    public void testAdd() {
        var v1 = new Vector2d(2, 1);
        assertEquals(new Vector2d(3, 3), v1.add(new Vector2d(1, 2)));
    }

    @Test
    public void testSubtract() {
        var v1 = new Vector2d(2, 1);
        assertEquals(new Vector2d(1, -1), v1.subtract(new Vector2d(1, 2)));
    }

    @Test
    public void testOpposite() {
        var v1 = new Vector2d(2, 1);
        assertEquals(new Vector2d(-2, -1), v1.opposite());
    }
}
