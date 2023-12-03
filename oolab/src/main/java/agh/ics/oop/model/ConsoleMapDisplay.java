package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updates = 0;
    @Override
    public void mapChanged(WorldMap map, String message) {
        updates++;
        System.out.println(message);
        System.out.println(map);
        System.out.println("Number of updates: " + updates);
    }
}
