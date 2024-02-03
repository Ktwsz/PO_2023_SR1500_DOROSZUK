package agh.ics.oop.model;

public interface WorldElement {
    String toString();
    boolean equals(Object o);
    int hashCode();
    Vector2d getPosition();

    String getTexture();

}
