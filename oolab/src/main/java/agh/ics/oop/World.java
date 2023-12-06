package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
 public static void main(String[] args) {
     List<MoveDirection> directions;
     try {
          directions = OptionsParser.parse(args);
     } catch (IllegalArgumentException e) {
         System.out.println(e.getMessage());
         return;
     }
     var listener = new ConsoleMapDisplay();

     List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

     List <Simulation> simulations = new ArrayList<>();

     for (int i = 1; i <= 500; i++) {
         var map = new RectangularMap(5, 5, i);
         map.addListener(listener);
         simulations.add(new Simulation(positions, directions, map));
     }
     for (int i = 501; i <= 1000; i++) {
         var map = new GrassField(10, i);
         map.addListener(listener);
         simulations.add(new Simulation(positions, directions, map));
     }

     var simulationEngine = new SimulationEngine(simulations);

     //simulationEngine.runSync();
     //simulationEngine.runAsync();
     simulationEngine.runAsyncInThreadPool();

     System.out.println("System zakończył działanie");
 }

}
