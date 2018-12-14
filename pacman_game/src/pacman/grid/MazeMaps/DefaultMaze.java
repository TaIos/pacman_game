package pacman.grid.MazeMaps;

import pacman.grid.DefaultGridValues;

/**
 * Represents the maze, it't walls and points placement
 */
public class DefaultMaze extends AbstractMazeMap implements DefaultGridValues {

    /**
     * 1 -> left wall
     * 2 -> top wall
     * 4 -> right wall
     * 8 -> bottom wall
     * 16 -> point
     * These numbers added together represent one block in a maze,
     * which is bellow
     */

    public DefaultMaze() {
        int data[][] = {
                {19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22},
                {21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
                {21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
                {21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 16, 16, 16, 16, 20},
                {17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 20},
                {17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20},
                {25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21},
                {1, 17, 16, 20, 0, 0, 0, 0, 0, 0, 0, 17, 20, 0, 21},
                {1, 17, 16, 16, 18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21},
                {1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21},
                {1, 17, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21},
                {1, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21},
                {1, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21},
                {1, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20},
                {9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 25, 24, 24, 24, 28}
        };
        setData(data);
    }
}
