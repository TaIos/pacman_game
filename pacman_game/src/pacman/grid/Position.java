package pacman.grid;

import java.util.Objects;

// TODO validate input
public class Position implements DefaultGridValues {
    private int x, dx, y, dy;
    boolean left, right, down, up;

    public Position() {
        this(X_DEFAULT, Y_DEFAULT);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = DX_DEFAULT;
        this.dy = DY_DEFAULT;
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

    public void setGoingLeft(boolean left) {
        this.left = left;
        right = up = down = false;
    }

    public void setGoingRight(boolean right) {
        this.right = right;
        left = up = down = false;
    }

    public void setGoingDown(boolean down) {
        this.down = down;
        left = right = up = false;
    }

    public void setUp(boolean up) {
        this.up = up;
        left = right = down = false;
    }

    public void setStop() {
        left = right = up = down = false;
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
