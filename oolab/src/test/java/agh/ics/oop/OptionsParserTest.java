package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class OptionsParserTest {
    @Test
    public void testParse() {
        assertArrayEquals(new MoveDirection[] {MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD},
                OptionsParser.parse(new String[] {"l", "tdss", "r", "asdasda", "f", "b", "ddd"}));
        
        assertArrayEquals(new MoveDirection[] {}, OptionsParser.parse(new String[] {"asdas", "sdfsf", "czxv", "ZXCxz", "SFDsfas"}));
    }

}
