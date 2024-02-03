package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    private static MoveDirection parseArg(String arg) {
        return switch (arg) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> MoveDirection.NONE;
        };
    }
    public static List<MoveDirection> parse(String[] args) throws IllegalArgumentException {
        var result = new ArrayList<MoveDirection>();

        for (String arg: args) {
            MoveDirection dir = parseArg(arg);

            if (dir == MoveDirection.NONE)
                throw new IllegalArgumentException(arg + " is not legal move specification");

            result.add(dir);
        }

        return result;
    }
}
