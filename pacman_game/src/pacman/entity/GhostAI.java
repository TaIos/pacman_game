package pacman.entity;

import java.io.File;
import java.util.Stack;

public class GhostAI extends Ghost {

    private Stack<Integer> path;

    public GhostAI(String imageName) {
        super(imageName);
        path = new Stack<>();
    }

    public GhostAI() {
        this(BASE_IMAGE_PATH + File.separator + DEFAULT_GHOST_IMAGE);
    }

}
