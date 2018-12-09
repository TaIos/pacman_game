package pacman.entity.entity_attributes;

import pacman.entity.DefaultEntityValues;

import java.util.Objects;

public class Lives implements DefaultEntityValues {
    private Integer lives;

    public Lives() {
        this(DEFAULT_PACMAN_LIVES);
    }

    // TODO validate lives
    public Lives(Integer lives) {
        this.lives = lives;
    }

    boolean isAlive() {
        return lives > 0;
    }

    public Integer getLives() {
        return lives;
    }

    public void setLives(Integer lives) {
        this.lives = lives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lives lives1 = (Lives) o;
        return Objects.equals(lives, lives1.lives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lives);
    }

    @Override
    public String toString() {
        return lives.toString();
    }
}
