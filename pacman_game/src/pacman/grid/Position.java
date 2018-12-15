package pacman.grid;

import java.util.Objects;

// TODO validate input
public class Position implements DefaultGridValues {
    private int x, y;
    private short delta;
    boolean left, right, down, up;

    public Position() {
        this(X_DEFAULT, Y_DEFAULT);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        left = right = down = up = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isGoingLeft() {
        return left;
    }

    public boolean isGoingRight() {
        return right;
    }

    public boolean isGoingUp() {
        return up;
    }

    public boolean isGoingDown() {
        return down;
    }

    public void setGoingLeft() {
        left = true;
        right = up = down = false;
    }

    public void setGoingRight() {
        right = true;
        left = up = down = false;
    }

    public void setGoingDown() {
        down = true;
        left = right = up = false;
    }

    public void setGoingUp() {
        up = true;
        left = right = down = false;
    }

    public void setStop() {
        left = right = up = down = false;
    }

    public boolean canGoLeft(int tile) {
        return (tile & 1) == 0;
    }

    public boolean canGoUp(int tile) {
        return (tile & 2) == 0;
    }

    public boolean canGoRight(int tile) {
        return (tile & 4) == 0;
    }

    public boolean canGoDown(int tile) {
        return (tile & 8) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
