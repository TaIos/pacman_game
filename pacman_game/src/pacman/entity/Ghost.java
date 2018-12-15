package pacman.entity;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Ghost extends AbstractEntity implements DefaultEntityValues {
    private boolean isAI;

    public Ghost(String imageName) {
        super();
        this.isAI = false;
        loadImages(BASE_IMAGE_PATH + File.separator + imageName);
    }

    public Ghost() {
        this(BASE_IMAGE_PATH + File.separator + DEFAULT_GHOST_IMAGE);
    }

    protected void loadImages(String imagePath) {
        try {
            File tmp = new File(imagePath);
            image = ImageIO.read(tmp);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }
}
