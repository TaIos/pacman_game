package pacman.entity;

import java.io.File;

public interface DefaultEntityValues {
    int DEFAULT_PACMAN_LIVES = 3;
    int DEFAULT_PACMAN_SPEED = 2; // valid values are 1, 2, 4, 8, 16, 32
    int DEFAULT_GHOST_SPEED = 2; // valid values are 1, 2, 4, 8, 16, 32
    int DEFAULT_SPEED = 2; // valid values are 1, 2, 4, 8, 16, 32
    int RND_MOVE = 9; // higher number lowers the chance of random ghost moves
    String BASE_IMAGE_PATH = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "pacman" + File.separator
            + "graphics" + File.separator + "Files" + File.separator + "images" + File.separator + "refactored";
    String DEFAULT_GHOST_IMAGE = "ghost_blue_right.png";

}
