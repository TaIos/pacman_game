package pacman.entity;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pacman extends AbstractEntity implements DefaultEntityValues {

    private Image pacmLeftIm, pacmRightIm, pacmUpIm, pacmDownIm;
    private int lives;
    private int score;
    private boolean leftKeyPressed, rightKeyPressed, upKeyPressed, downKeyPressed;

    public Pacman() {
        super();
        loadImages();
        lives = DEFAULT_PACMAN_LIVES;
        speed = DEFAULT_PACMAN_SPEED;
        score = 0;
        leftKeyPressed = rightKeyPressed = upKeyPressed = downKeyPressed = false;
        position.setGoingRight();
    }

    private void loadImages() {
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
        image = pacmRightIm;
    }

    @Override
    public void move(int tile, boolean isAtCentre) {
        if (inputOppositeDirection()) {
            setDirectionFromKeyboard();
        } else if (isAtCentre) {
            if (leftKeyPressed && position.canGoLeft(tile)) {
                position.setGoingLeft();
            } else if (upKeyPressed && position.canGoUp(tile)) {
                position.setGoingUp();
            } else if (rightKeyPressed && position.canGoRight(tile)) {
                position.setGoingRight();
            } else if (downKeyPressed && position.canGoDown(tile)) {
                position.setGoingDown();
            } else if (!canContinueMoving(tile))
                position.setStop();
        }
        writeMove();
    }

    private void setDirectionFromKeyboard() {
        if (leftKeyPressed)
            position.setGoingLeft();
        else if (upKeyPressed)
            position.setGoingUp();
        else if (rightKeyPressed)
            position.setGoingRight();
        else if (downKeyPressed)
            position.setGoingDown();
    }

    // return true if pacman is moving one direction and keyboard input is opposite direction
    private boolean inputOppositeDirection() {
        return (position.isGoingLeft() && rightKeyPressed)
                || (position.isGoingRight() && leftKeyPressed)
                || (position.isGoingUp() && downKeyPressed)
                || (position.isGoingDown() && upKeyPressed);
    }

    public void reset() {
        image = pacmRightIm;
        position.setX(PACM_X);
        position.setY(PACM_Y);
        position.setStop();
        leftKeyPressed = rightKeyPressed = upKeyPressed = downKeyPressed = false;
    }

    public void incScore() {
        score++;
    }

    public void decLives() {
        lives--;
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

    @Override
    public Image getImage() {
        if (position.isGoingLeft())
            image = pacmLeftIm;
        else if (position.isGoingDown())
            image = pacmDownIm;
        else if (position.isGoingUp())
            image = pacmUpIm;
        else if (position.isGoingRight())
            image = pacmRightIm;

        return image;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setLeftKeyPressed() {
        leftKeyPressed = true;
        upKeyPressed = rightKeyPressed = downKeyPressed = false;
    }

    public void setRightKeyPressed() {
        rightKeyPressed = true;
        leftKeyPressed = upKeyPressed = downKeyPressed = false;
    }

    public void setUpKeyPressed() {
        upKeyPressed = true;
        leftKeyPressed = rightKeyPressed = downKeyPressed = false;
    }

    public void setDownKeyPressed() {
        downKeyPressed = true;
        leftKeyPressed = upKeyPressed = rightKeyPressed = false;
    }
}
