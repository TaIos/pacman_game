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

    public int getScore() {
        return 1;
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

}
