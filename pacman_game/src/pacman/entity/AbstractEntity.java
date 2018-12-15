package pacman.entity;

import pacman.grid.Position;

import java.awt.*;
import java.util.Random;

public abstract class AbstractEntity implements DefaultEntityValues {
    protected Image image;
    protected Position position;
    protected int speed;
    protected Random random;

    public AbstractEntity() {
        position = new Position();
        speed = DEFAULT_SPEED;
        random = new Random();
    }

    public abstract void move(int tile, boolean isAtCentre);

    protected boolean canContinueMoving(int tile) {
        return ((position.isGoingLeft() && position.canGoLeft(tile))
                || (position.isGoingUp() && position.canGoUp(tile))
                || (position.isGoingRight() && position.canGoRight(tile))
                || (position.isGoingDown() && position.canGoDown(tile)));
    }

    protected void setRandomValidDirection(int tile) {
        int direction;
        while (true) {
            direction = random.nextInt(4);
            if ((direction == 0)  // left
                    && position.canGoLeft(tile)) {
                position.setGoingLeft();
                break;
            } else if ((direction == 1) // right
                    && position.canGoRight(tile)) {
                position.setGoingRight();
                break;
            } else if ((direction == 2) // up
                    && position.canGoUp(tile)) {
                position.setGoingUp();
                break;
            } else if ((direction == 3) // down
                    && position.canGoDown(tile)) {
                position.setGoingDown();
                break;
            }
        }
    }

    protected void writeMove() {
        int x, y;
        x = position.getX();
        y = position.getY();

        if (position.isGoingLeft()) {
            position.setX(x - speed);
        } else if (position.isGoingUp()) {
            position.setY(y - speed);
        } else if (position.isGoingRight()) {
            position.setX(x + speed);
        } else if (position.isGoingDown()) {
            position.setY(y + speed);
        }
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
