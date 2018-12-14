package pacman.entity;


import pacman.grid.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pacman extends AbstractEntity
        implements DefaultEntityValues {
    private Image pacmLeftIm, pacmRightIm, pacmUpIm, pacmDownIm;

    private Position position;
    private int lives;
    private int score;

    public Pacman() {
        lives = DEFAULT_PACMAN_LIVES;
        speed = DEFAULT_PACMAN_SPEED;
        position = new Position();
        score = 0;

        try {
            loadImages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {

    }

    @Override
    protected void loadImages() throws IOException {
        String pacmLeftImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_left.png";
        String pacmanRightImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_right.png";
        String pacmanUpImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_up.png";
        String pacmanDownImagePath = BASE_IMAGE_PATH + File.separator + "pacman_open_down.png";

        File tmp = new File(pacmLeftImagePath);
        pacmLeftIm = ImageIO.read(tmp);

        tmp = new File(pacmanRightImagePath);
        pacmRightIm = ImageIO.read(tmp);

        tmp = new File(pacmanUpImagePath);
        pacmUpIm = ImageIO.read(tmp);

        tmp = new File(pacmanDownImagePath);
        pacmDownIm = ImageIO.read(tmp);
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

    public Position getPosition() {
        return position;
    }

    public Image getImage() {
        if (position.isGoingLeft())
            return pacmLeftIm;
        if (position.isGoingDown())
            return pacmDownIm;
        if (position.isGoingUp())
            return pacmUpIm;
        return pacmRightIm;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
