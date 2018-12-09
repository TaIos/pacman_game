package pacman.entity;

import java.io.File;

public interface DefaultEntityValues {
    Integer DEFAULT_PACMAN_LIVES = 3;
    Integer DEFAULT_PACMAN_SPEED = 5;
    Integer DEFAULT_GHOST_SPEED = 5;
    String BASE_IMAGE_PATH = System.getProperty("user.dir") + File.separator
            + "pjv_semestral_work_saframa6" + File.separator + "src" + File.separator + "pacman" + File.separator
            + "graphics" + File.separator + "Files" + File.separator + "images" + File.separator + "refactored";
}
