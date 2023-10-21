package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

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
    public static MoveDirection[] parse(String[] args) {
        int resultLength = 0;
        for (String arg: args) {
            if (parseArg(arg) != MoveDirection.NONE) resultLength++;
        }
        MoveDirection[] result = new MoveDirection[resultLength];

        int idx = 0;
        for (String arg: args) {
            MoveDirection dir = parseArg(arg);

            if (dir == MoveDirection.NONE) continue;
            result[idx] = dir;
            idx++;
        }

        return result;
    }
}
