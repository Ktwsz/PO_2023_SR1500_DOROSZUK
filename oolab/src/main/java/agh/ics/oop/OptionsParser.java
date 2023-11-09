package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    private static MoveDirection parseArg(String arg) {
        return switch (arg) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "r" -> MoveDirection.RIGHT;
            case "l" -> MoveDirection.LEFT;
            default -> MoveDirection.NONE;
        };
    }
    public static List<MoveDirection> parse(String[] args) {
        var result = new ArrayList<MoveDirection>();

        for (String arg: args) {
            MoveDirection dir = parseArg(arg);

            if (dir == MoveDirection.NONE) continue;
            result.add(dir);
        }

        return result;
    }
}
