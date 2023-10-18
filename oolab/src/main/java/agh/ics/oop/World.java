package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
 public static void main(String[] args) {
     System.out.println("system wystartował");
     MoveDirection[] directions = OptionsParser.parse(args);
     run(directions);
     System.out.println("system zakończył działanie");
 }

 public static void run(MoveDirection[] directions) {
     for (MoveDirection dir: directions) {
         String instructionStr = "Zwierzak " + switch(dir) {
             case FORWARD -> "idzie do przodu";
             case BACKWARD -> "idzie do tyłu";
             case LEFT -> "skręca w lewo";
             case RIGHT -> "skręca w prawo";
             default -> "";
         };
         System.out.println(instructionStr);
     }
 }


}
