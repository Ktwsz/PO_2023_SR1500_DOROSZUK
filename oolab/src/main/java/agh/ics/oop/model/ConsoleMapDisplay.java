package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updates = 0;
    /*
        Wersja race condition różni się tylko keywordem synchronized
        public void mapChanged(WorldMap map, String message)
    */
    @Override
    public synchronized void mapChanged(WorldMap map, String message) {
        updates++;
        System.out.println("Map nr " + map.getId());
        System.out.println(message);
        System.out.println(map);
        System.out.println("Number of updates: " + updates);
        System.out.println();
    }
}
