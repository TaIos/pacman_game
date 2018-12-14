package pacman.entity;

import pacman.grid.Position;

import java.awt.*;
import java.io.IOException;

public abstract class AbstractEntity {
    protected Image image;
    protected Position position;
    protected int speed;

    public abstract void move();

    protected abstract void loadImages() throws IOException;

    public abstract Image getImage();

    public Position getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }
}
