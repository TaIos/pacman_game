package pacman.grid;

import pacman.grid.MazeMaps.AbstractMazeMap;
import pacman.grid.MazeMaps.DefaultMaze;

import javax.swing.*;

public class Grid extends JPanel implements DefaultGridValues {
    int width, height, blockSize;
    AbstractMazeMap maze;

    public Grid(AbstractMazeMap maze) {
        this();
        this.maze = maze;
    }

    public Grid() {
        this(DEFAULT_WIDTH_PIXEL, DefaultGridValues.DEFAULT_HEIGHT_PIXEL, DEFAULT_BLOCK_SIZE);
    }

    public Grid(int width, int height, int blockSize) {
        this.width = width;
        this.height = height;
        this.blockSize = blockSize;
        maze = new DefaultMaze();
    }

    public boolean removePointIfPresent(int x, int y) {
        int block = blockAt(x, y);
        int data[][] = maze.getData();

        if ((block & 16) != 0) {
            data[y / blockSize][x / blockSize] = (block & 15);
            return true;
        }
        return false;
    }

    public int getScore() {
        int sum = 0;
        int data[][] = maze.getData();
        for (int i = 0; i < getHeightInBlocks(); i++)
            for (int j = 0; j < getWidthInBlocks(); j++) {
                if ((data[i][j] & 16) != 0)
                    sum++;
            }
        return sum;
    }

    public boolean noMorePoints() {
        return getScore() < 1;
    }

    public int getWidthInPixels() {
        return width;
    }

    public int getHeightInPixels() {
        return height;
    }

    public int getWidthInBlocks() {
        return width / DEFAULT_BLOCK_SIZE;
    }

    public int getHeightInBlocks() {
        return height / DEFAULT_BLOCK_SIZE;
    }

    public int getBlockSize() {
        return DEFAULT_BLOCK_SIZE;
    }

    public int[][] getData() {
        return maze.getData();
    }

    public int blockAt(int x, int y) {
        int data[][] = maze.getData();
        return data[y / blockSize][x / blockSize];
    }

    public boolean isAtCentre(int x, int y) {
        return (x % blockSize == 0) && (y % blockSize == 0);
    }

}
