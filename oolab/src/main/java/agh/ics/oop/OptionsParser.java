package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int result_length = 0;
        for (String arg: args) {
            if (arg != "f" && arg != "b" && arg != "l" && arg != "r") result_length++;
        }
        MoveDirection[] result = new MoveDirection[result_length];

        int idx = 0;
        for (String arg: args) {
            MoveDirection dir = switch (arg) {
                case "f" -> MoveDirection.FORWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                case "b" -> MoveDirection.BACKWARD;
                default -> MoveDirection.NONE;
            };

            if (dir != MoveDirection.NONE) {
                result[idx] = dir;
                idx++;
            }
        }

        return result;
    }
}
