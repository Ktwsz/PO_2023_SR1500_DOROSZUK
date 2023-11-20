package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List <Animal> animalsList;
    private final List <MoveDirection> movesList;

    private final WorldMap <Animal, Vector2d> map;
    public Simulation(List <Vector2d> startPositions, List <MoveDirection> moves, WorldMap <Animal, Vector2d> map) {
        movesList = moves;

        this.map = map;

        animalsList = new ArrayList<>();
        for (Vector2d start : startPositions) {
            var anim = new Animal(start);
            animalsList.add(anim);
            map.place(anim);
        }
    }

    public void run() {
        int animalIx = 0;
        for (MoveDirection move : movesList) {
                var animal = animalsList.get(animalIx);

                map.move(animal, move);

                animalIx++;
                if (animalIx == animalsList.size()) {
                    animalIx = 0;
                    System.out.println(map);
            }
        }
    }

    public Animal getAnimal(int ix) {
        return animalsList.get(ix);
    }
}
