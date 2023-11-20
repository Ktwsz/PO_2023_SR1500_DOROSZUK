package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextMap implements WorldMap <String, Integer> {
    private final List <String> strings = new ArrayList<>();
    private final List <MapDirection> stringsOrient = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < strings.size(); i++)
            out.append("index: %d | value: %s | orientation: %s\n".formatted(i, strings.get(i), stringsOrient.get(i)));
        return out.toString();
    }

    @Override
    public void move(String value, MoveDirection direction) {
        int position = strings.indexOf(value);

        if (position == -1) return;

        MapDirection orientation = stringsOrient.get(position);

        if (direction == MoveDirection.LEFT) {
            stringsOrient.set(position, orientation.previous());
            return;
        }
        if (direction == MoveDirection.RIGHT) {
            stringsOrient.set(position, orientation.next());
            return;
        }

        if (orientation != MapDirection.EAST && orientation != MapDirection.WEST) return;

        int newPosition = position + ((direction == MoveDirection.FORWARD)?
                                        orientation.toUnitVector()
                                        : orientation.toUnitVector().opposite()).getX();
        if (!canMoveTo(newPosition)) return;

        Collections.swap(strings, position, newPosition);
        Collections.swap(stringsOrient, position, newPosition);
    }
    @Override
    public boolean place(String value) {
        strings.add(value);
        stringsOrient.add(MapDirection.NORTH);
        return true;
    }
    @Override
    public boolean isOccupied(Integer position) {
        return true;
    }
    @Override
    public String objectAt(Integer position) {
        return strings.get(position);
    }
    @Override
    public boolean canMoveTo(Integer position) {
        return 0 <= position && position < strings.size();
    }
}
