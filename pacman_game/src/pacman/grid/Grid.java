package pacman.grid;

import javax.swing.*;

public class Grid extends JPanel implements DefaultGridValues {
    Integer width;
    Integer height;
    MazeData maze;
    int screenData[];

    public Grid() {
        this(WIDTH_DEFAULT, HEIGHT_DEFAULT);
    }

    public Grid(Integer width, Integer height) {
        this.width = width;
        this.height = height;
        screenData = new int[width * height];
        maze = new MazeData();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean noMorePoints() {
        return true;
    }
}
