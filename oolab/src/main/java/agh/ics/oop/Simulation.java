package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List <Animal> animalsList;
    private final List <MoveDirection> movesList;
    public Simulation(List <Vector2d> startPositions, List <MoveDirection> moves) {
        movesList = moves;

        animalsList = new ArrayList<>();
        for (Vector2d start : startPositions) animalsList.add(new Animal(start));
    }

    public void run() {
        int animalIx = 0;
        for (MoveDirection move : movesList) {
                var animal = animalsList.get(animalIx);

                animal.move(move);
                System.out.printf("Zwierzę %d : %s%n", animalIx, animal);

                animalIx = (animalIx+1)%animalsList.size();
        }
    }

    public Animal getAnimal(int ix) {
        return animalsList.get(ix);
    }
}
