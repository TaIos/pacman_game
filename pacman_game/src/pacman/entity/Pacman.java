package pacman.entity;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pacman extends AbstractEntity
        implements DefaultEntityValues {
    private Image pacmLeftIm, pacmRightIm, pacmUpIm, pacmDownIm;

    private int lives;
    private int score;

    public Pacman() {
        super();
        loadImages();
        lives = DEFAULT_PACMAN_LIVES;
        speed = DEFAULT_PACMAN_SPEED;
        score = 0;
    }

    protected void loadImages() {
        String pacmLeftImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_left.png";
        String pacmanRightImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_right.png";
        String pacmanUpImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_up.png";
        String pacmanDownImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_down.png";

        try {
            File tmp = new File(pacmLeftImagePath);
            pacmLeftIm = ImageIO.read(tmp);

            tmp = new File(pacmanRightImagePath);
            pacmRightIm = ImageIO.read(tmp);

            tmp = new File(pacmanUpImagePath);
            pacmUpIm = ImageIO.read(tmp);

            tmp = new File(pacmanDownImagePath);
            pacmDownIm = ImageIO.read(tmp);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean isAlive() {
        return getLives() > 0;
    }

    public boolean isDead() {
        return !isAlive();
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void incScore() {
        score++;
    }

    public void decLives() {
        lives--;
    }

    @Override
    public Image getImage() {
        if (position.isGoingLeft())
            return pacmLeftIm;
        if (position.isGoingDown())
            return pacmDownIm;
        if (position.isGoingUp())
            return pacmUpIm;
        return pacmRightIm;
    }

    @Override
    public void move(int tile, boolean isAtCentre) {

    }

    public void setLives(int lives) {
        this.lives = lives;
    }

}
