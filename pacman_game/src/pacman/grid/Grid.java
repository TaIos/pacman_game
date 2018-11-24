package pacman.grid;

import pacman.graphics.Image;

public class Grid implements DefaultGridValues {
    Integer width;
    Integer height;
    Image backround;

    public Grid() {
        this(WIDTH_DEFAULT, HEIGHT_DEFAULT);
    }

    public Grid(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }
}
