package pacman.grid;

import java.util.Objects;

// TODO validate input
public class Position implements DefaultGridValues {
    private int x, dx, y, dy;

    public Position() {
        this(X_DEFAULT, Y_DEFAULT);
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.dx = DX_DEFAULT;
        this.dy = DY_DEFAULT;
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
