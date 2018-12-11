package pacman.grid;

import javax.swing.*;
import javax.xml.bind.annotation.XmlType;

public class Grid extends JPanel implements DefaultGridValues {
    int width, height, blockSize;
    MazeData maze;

    public Grid() {
        this(DEFAULT_WIDTH_PIXEL, DefaultGridValues.DEFAULT_HEIGHT_PIXEL, DEFAULT_BLOCK_SIZE);
    }

    public Grid(int width, int height, int blockSize) {
        this.width = width;
        this.height = height;
        this.blockSize = blockSize;
        maze = new MazeData();
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
