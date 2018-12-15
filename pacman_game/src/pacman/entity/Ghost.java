package pacman.entity;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Ghost extends AbstractEntity implements DefaultEntityValues {

    public Ghost(String imageName) {
        super();
        loadImages(BASE_IMAGE_PATH + File.separator + imageName);
    }

    public Ghost() {
        this(BASE_IMAGE_PATH + File.separator + DEFAULT_GHOST_IMAGE);
    }

    public void move() {

    }

    protected void loadImages(String imagePath) {
        try {
            File tmp = new File(imagePath);
            image = ImageIO.read(tmp);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
