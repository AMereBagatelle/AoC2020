package io.github.amerebagatelle.util.objects;

public class RotationHandler {
    private int rotation;

    public RotationHandler(int startingRotation) {
        rotation = startingRotation;
    }

    public void rotate(int angle) {
        rotation += angle;
        if (rotation < 0) {
            rotation += 360;
        } else if (rotation >= 360) {
            rotation -= 360;
        }
    }

    public int getRotation() {
        return rotation;
    }
}
