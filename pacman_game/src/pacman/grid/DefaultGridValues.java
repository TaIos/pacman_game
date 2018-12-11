package pacman.grid;

import java.awt.*;

public interface DefaultGridValues {
    int X_DEFAULT = 1;
    int Y_DEFAULT = 1;
    int DX_DEFAULT = 0;
    int DY_DEFAULT = 0;
    int DEFAULT_BLOCK_SIZE = 32;
    int DEFAULT_WIDTH_PIXEL = 480;
    int DEFAULT_HEIGHT_PIXEL = 480;
    int DEFAULT_WIDTH_BLOCK = DEFAULT_WIDTH_PIXEL / DEFAULT_BLOCK_SIZE;
    int DEFAULT_HEIGHT_BLOCK = DEFAULT_HEIGHT_PIXEL / DEFAULT_BLOCK_SIZE;
    int LEFT = 3;
    int RIGHT = 4;
    int UP = 2;
    int DOWN = 1;
}
