package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{

    private final List <Animal> animalsList;
    private final List <MoveDirection> movesList;

    private final WorldMap map;
    public Simulation(List <Vector2d> startPositions, List <MoveDirection> moves, WorldMap map) {
        movesList = moves;

        this.map = map;

        animalsList = new ArrayList<>();
        for (Vector2d start : startPositions) {
            var anim = new Animal(start);
            animalsList.add(anim);
            try {
                map.place(anim);
            } catch (PositionAlreadyOccupiedException e) {
                animalsList.remove(animalsList.size() - 1);
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        int animalIx = 0;
        for (MoveDirection move : movesList) {
                var animal = animalsList.get(animalIx);

                map.move(animal, move);

                animalIx = (animalIx + 1) % animalsList.size();
        }
    }

    public Animal getAnimal(int ix) {
        return animalsList.get(ix);
    }
}
