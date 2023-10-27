package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

public class World {
 public static void main(String[] args) {
     System.out.println("system wystartował");
     MoveDirection[] directions = OptionsParser.parse(args);
     run(directions);

     Vector2d position1 = new Vector2d(1,2);
     System.out.println(position1);
     Vector2d position2 = new Vector2d(-2,1);
     System.out.println(position2);
     System.out.println(position1.add(position2));

     MapDirection dir = MapDirection.NORTH;
     System.out.println(dir);
     dir = dir.next();
     System.out.println(dir.toUnitVector());
     dir = dir.previous();
     System.out.println(dir);

     System.out.println("system zakończył działanie");
 }

 public static void run(MoveDirection[] directions) {
     for (MoveDirection dir: directions) {
         switch(dir) {
             case FORWARD -> System.out.println("Zwierzak idzie do przodu");
             case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
             case LEFT -> System.out.println("Zwierzak skręca w lewo");
             case RIGHT -> System.out.println("Zwierzak skręca w prawo");
         }
     }
 }


}
