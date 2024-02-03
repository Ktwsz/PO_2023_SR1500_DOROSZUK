package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        var illegalArg = Stream.of(args).filter(arg -> OptionsParser.parseArg(arg) == MoveDirection.NONE).findAny();
        if (illegalArg.isPresent()) throw new IllegalArgumentException(illegalArg + " is not legal move specification");

        return Stream.of(args)
                     .map(OptionsParser::parseArg)
                     .collect(Collectors.toList());
    }
}
