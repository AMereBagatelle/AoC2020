package io.github.amerebagatelle.util.objects;

import java.util.ArrayList;
import java.util.List;

public class Position {
    public int x, y;
    private final RotationHandler rotationHandler;

    private final List<Position> attached = new ArrayList<>();

    public Position() {
        this(0, 0, 0);
    }

    public Position(int x, int y) {
        this(x, y, 0);
    }

    public Position(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.rotationHandler = new RotationHandler(angle);
    }

    public void north(int distance) {
        this.y += distance;
    }

    public void south(int distance) {
        this.y -= distance;
    }

    public void east(int distance) {
        this.x += distance;
    }

    public void west(int distance) {
        this.x -= distance;
    }

    public void forward(int distance) {
        int rotation = rotationHandler.getRotation();
        switch (rotation) {
            case 0 -> north(distance);
            case 90 -> east(distance);
            case 180 -> south(distance);
            case 270 -> west(distance);
        }
    }

    public void rotateRight(int angle) {
        rotationHandler.rotate(angle);
    }

    public void rotateLeft(int angle) {
        rotationHandler.rotate(-angle);
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getRotation() {
        return rotationHandler.getRotation();
    }

    public int getManhattanFromStart() {
        return Math.abs(x) + Math.abs(y);
    }
}
