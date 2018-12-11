package pacman.entity;


import pacman.entity.entity_attributes.Lives;
import pacman.grid.Position;

import java.awt.*;
import java.io.File;

public class Pacman extends AbstractEntity
        implements DefaultEntityValues {
    private Image pacmLeft, pacmRight, pacmUp, pacmDown;

    private Position position;
    private Lives lives;
    private int speed;
    private int score;

    public Pacman() {
        lives = new Lives(DEFAULT_PACMAN_LIVES);
        speed = DEFAULT_PACMAN_SPEED;
        position = new Position();
        score = 0;

        String pacmLeftImagePath = BASE_IMAGE_PATH + File.separator + "test1";
        String pacmanRightImagePath = BASE_IMAGE_PATH + File.separator + "test2";
        String pacmanUpImagePath = BASE_IMAGE_PATH + File.separator + "test3";
        String pacmanDownImagePath = BASE_IMAGE_PATH + File.separator + "test4";

        System.out.println(pacmLeftImagePath);
    }

    public void move() {

    }

    public boolean isAlive() {
        return getLives() > 0;
    }

    public boolean isDead() {
        return !isAlive();
    }

    public int getLives() {
        return lives.getLives();
    }

    public int getScore() {
        return score;
    }

    public void incScore() {
        score++;
    }
}
