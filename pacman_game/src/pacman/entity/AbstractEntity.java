package pacman.entity;

import pacman.grid.Position;

import java.awt.*;

public abstract class AbstractEntity implements DefaultEntityValues {
    protected Image image;
    protected Position position;
    protected int speed;

    public AbstractEntity() {
        position = new Position();
        speed = DEFAULT_SPEED;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
