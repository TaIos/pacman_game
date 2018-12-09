package pacman.graphics;

import java.awt.*;
import java.io.File;

public interface DefaultGraphicsValues {
    Color DEFAULT_WALL_COLOR = new Color(5, 100, 5);
    String BASE_IMAGE_PATH = System.getProperty("user.dir") + File.separator
            + "pjv_semestral_work_saframa6" + File.separator + "src" + File.separator + "pacman" + File.separator
            + "graphics" + File.separator + "Files" + File.separator + "images" + File.separator + "refactored";
}
