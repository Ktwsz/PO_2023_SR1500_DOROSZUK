package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomPositionGenerator implements Iterator<Vector2d>, Iterable <Vector2d> {

    private final ArrayList <Vector2d> generatedPositions = new ArrayList<>();
    private int iteratorIx = 0;

    RandomPositionGenerator(int width, int height, int grassCount) {
        this(width, height, grassCount, null);
    }

    RandomPositionGenerator(int width, int height, int grassCount, Integer seed) {
        var generator = (seed != null)? new Random(seed) : new Random();

        int remaining = width * height;
        for (int x = 0, y = 0;
             x < width && y < height && grassCount > 0;
             y = (x + 1 == width)? y + 1 : y, x = (x + 1) % width) {
            double probability = generator.nextDouble();
            if (probability < ((double) grassCount / (double) remaining)) {
                grassCount--;
                generatedPositions.add(new Vector2d(x, y));
            }

            remaining--;
        }
    }

    @Override
    public boolean hasNext() {
        return iteratorIx < generatedPositions.size();
    }

    @Override
    public Vector2d next() {
        if (!hasNext()) throw new NoSuchElementException();

        iteratorIx++;
        return generatedPositions.get(iteratorIx-1);
    }

    @Override
    public Iterator <Vector2d> iterator() {
        return this;
    }
}
