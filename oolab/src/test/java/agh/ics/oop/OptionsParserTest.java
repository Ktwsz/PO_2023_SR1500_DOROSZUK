package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class OptionsParserTest {
    @Test
    public void testParse() {
        assertIterableEquals(List.of(MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.BACKWARD),
                OptionsParser.parse(new String[] {"l", "tdss", "r", "asdasda", "f", "b", "ddd"}));
        
        assertIterableEquals(List.of(), OptionsParser.parse(new String[] {"asdas", "sdfsf", "czxv", "ZXCxz", "SFDsfas"}));
    }

}
