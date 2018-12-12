package pacman.graphics;

import java.awt.*;
import java.io.File;

public interface DefaultGraphicsValues {
    Color DEFAULT_WALL_COLOR = new Color(76, 0, 153);
    Color DEFAULT_DOT_COLOR = new Color(255, 255, 0);
    Color LIVES_COLOR = new Color(255, 0, 0);
    Color POINTS_COLOR = new Color(238, 255, 136);
    int DEFAULT_CIRCLE_DIAMETER = 5;
    int DEFAULT_WALL_WIDTH = 4;
    String BASE_IMAGE_PATH = System.getProperty("user.dir") + File.separator
            + "src" + File.separator + "pacman" + File.separator
            + "graphics" + File.separator + "Files" + File.separator + "images" + File.separator + "refactored";
}
