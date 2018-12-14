package pacman.grid.MazeMaps;

import pacman.grid.DefaultGridValues;

/**
 * 1 -> left wall
 * 2 -> top wall
 * 4 -> right wall
 * 8 -> bottom wall
 * 16 -> point
 * These numbers added together represent one block in a maze,
 * which is bellow
 */
public abstract class AbstractMazeMap implements DefaultGridValues {
    private int data[][];

    protected void setData(int data[][]) {
        this.data = data;
    }

    public int[][] getData() {
        return data;
    }
}
