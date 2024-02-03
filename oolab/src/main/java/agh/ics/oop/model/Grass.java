package agh.ics.oop.model;

import java.util.Objects;

public class Grass implements WorldElement {
    private final Vector2d position;
    public Grass(Vector2d position) {
        this.position = position;
    }
    public Vector2d getPosition() {
        return position;
    }
    @Override
    public String toString() {
        return "*";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grass)) return false;
        Grass grass = (Grass) o;
        return position.equals(grass.position);
    }
    @Override
    public int hashCode() {
        return position.hashCode();
    }

    @Override
    public String getTexture() {
        return "textures/grass.png";
    }
}
