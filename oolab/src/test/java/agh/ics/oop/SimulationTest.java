package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimulationTest {
    @Test
    void runTest() {
        List<MoveDirection> directions = OptionsParser.parse(new String[] {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        assertTrue(simulation.getAnimal(0).isAt(new Vector2d(3, 0)));
        assertTrue(simulation.getAnimal(0).isFacing(MapDirection.SOUTH));

        assertTrue(simulation.getAnimal(1).isAt(new Vector2d(2, 4)));
        assertTrue(simulation.getAnimal(1).isFacing(MapDirection.NORTH));

        directions = OptionsParser.parse(new String[] {"f", "f", "f", "f", "f", "f"});
        positions = List.of(new Vector2d(2, 2));
        simulation = new Simulation(positions, directions);
        simulation.run();

        assertTrue(simulation.getAnimal(0).isAt(new Vector2d(2, 4)));
        assertTrue(simulation.getAnimal(0).isFacing(MapDirection.NORTH));

        directions = OptionsParser.parse(new String[] {"f", "b", "l", "r", "f", "b", "l", "r", "f", "b"});
        positions = List.of(new Vector2d(2, 2), new Vector2d(0, 1), new Vector2d(1, 0), new Vector2d(3, 4), new Vector2d(3, 1));
        simulation = new Simulation(positions, directions);
        simulation.run();

        assertTrue(simulation.getAnimal(0).isAt(new Vector2d(2, 2)));
        assertTrue(simulation.getAnimal(0).isFacing(MapDirection.NORTH));

        assertTrue(simulation.getAnimal(1).isAt(new Vector2d(0, 0)));
        assertTrue(simulation.getAnimal(1).isFacing(MapDirection.WEST));

        assertTrue(simulation.getAnimal(2).isAt(new Vector2d(1, 0)));
        assertTrue(simulation.getAnimal(2).isFacing(MapDirection.NORTH));

        assertTrue(simulation.getAnimal(3).isAt(new Vector2d(4, 4)));
        assertTrue(simulation.getAnimal(3).isFacing(MapDirection.EAST));

        assertTrue(simulation.getAnimal(4).isAt(new Vector2d(3, 1)));
        assertTrue(simulation.getAnimal(4).isFacing(MapDirection.NORTH));
    }

}
