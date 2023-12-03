package agh.ics.oop;

import agh.ics.oop.model.*;

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

     var map = new GrassField(10, 1);
     map.addListener(listener);

     Simulation simulation = new Simulation(positions, directions, map);
     simulation.run();
 }

}
