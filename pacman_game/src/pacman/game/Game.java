package pacman.game;

import pacman.entity.Ghost;
import pacman.entity.GhostAI;
import pacman.entity.Pacman;
import pacman.graphics.GraphicsController;
import pacman.grid.Grid;
import pacman.grid.MazeMaps.PJVMaze;
import pacman.grid.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements DefaultGameValues, KeyListener {
    private Pacman pacman;
    private Ghost[] ghosts;
    private Grid grid;
    private GraphicsController graphicsController;
    private JFrame frame;
    private boolean pauseFlag;

    public Game() {
        //grid = new Grid(new EmptyMaze());
        grid = new Grid(new PJVMaze());
        pacman = new Pacman();
        loadImages();
        initFlags();
        initGhosts();
        initGraphics();
    }

    public void start() {
        graphicsController.showMenu();
        try {
            tickComponent();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tickComponent() throws InterruptedException {
        while (true) {
            Thread.sleep(DEFAULT_DELAY_MILISECONDS);

            if (collision()) {
                pacman.decLives();
                Thread.sleep(COLLISION_DELAY_MILISECONDS);
                if (pacman.isDead()) {
                    lost();
                    break;
                } else {
                    resetGrid();
                }
            }

            if (grid.noMorePoints()) {
                won();
                break;
            }

            movePacman();
            moveGhosts();
            graphicsController.drawScene();
        }
    }

    private void won() {
        System.out.println("You have won the game!");
    }

    private void lost() {

    }

    private void pause() {
        pauseFlag = !pauseFlag;
    }

    private void resetGrid() {
        initFlags();
        pacman.reset();
        initGhosts();
        initGraphics();
    }

    private void movePacman() {
        int x, y;
        x = pacman.getPosition().getX();
        y = pacman.getPosition().getY();

        if (grid.removePointIfPresent(x, y))
            pacman.incScore();

        pacman.move(grid.blockAt(x, y), grid.isAtCentre(x, y));
    }

    private void moveGhosts() {
        int x, y;

        for (Ghost g : ghosts) {
            x = g.getPosition().getX();
            y = g.getPosition().getY();
            g.move(grid.blockAt(x, y), grid.isAtCentre(x, y));
        }
    }

    private boolean collision() {
        int x, y, gx, gy, blkSize;
        Position pos = pacman.getPosition();
        x = pos.getX();
        y = pos.getY();
        blkSize = grid.getBlockSize();

        // collision if centre of ghost is in pacman's square
        for (Ghost g : ghosts) {
            gx = g.getPosition().getX() + blkSize / 2;
            gy = g.getPosition().getY() + blkSize / 2;

            if ((gx >= x && gx <= x + blkSize
                    && (gy >= y && gy <= y + blkSize)))
                return true;
        }
        return false;
    }

    private void initGraphics() {

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        graphicsController = new GraphicsController(pacman, ghosts, grid);

        // setting size
        frame.setSize(new Dimension(grid.getWidthInPixels(), grid.getHeightInPixels() +
                TOP_BAR_HEIGHT_PIXEL + INFO_BAR_PIXEL_HEIGHT));
        frame.setResizable(false);

        frame.addKeyListener(this);

        frame.add(graphicsController);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void initGhosts() {
        if (DEFAULT_GHOST_CNT != 4)
            throw new Error("Ghost count is not equal to 4");

        int blkSIze = grid.getBlockSize();

        ghosts = new Ghost[DEFAULT_GHOST_CNT];

        ghosts[0] = new Ghost("ghost_policeman_right.png");
        ghosts[0].setPosition(new Position(9 * blkSIze, 7 * blkSIze));

        ghosts[1] = new Ghost("ghost_princess_right.png");
        ghosts[1].setPosition(new Position(11 * blkSIze, 7 * blkSIze));

        ghosts[2] = new Ghost("ghost_soldier_right.png");
        ghosts[2].setPosition(new Position(13 * blkSIze, 7 * blkSIze));

        //ghosts[3] = new GhostAI("ghost_blue_right.png", grid.getData());
        ghosts[3] = new GhostAI("vagner.png", grid, pacman.getPosition());
        ghosts[3].setPosition(new Position(14 * blkSIze, 14 * blkSIze));
    }

    private void loadImages() {

    }

    private void initFlags() {
        pauseFlag = false;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_H:
                pacman.setLeftKeyPressed();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_L:
                pacman.setRightKeyPressed();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_K:
                pacman.setUpKeyPressed();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
            case KeyEvent.VK_J:
                pacman.setDownKeyPressed();
                break;
            case KeyEvent.VK_PAUSE:
            case KeyEvent.VK_ESCAPE:
                pause();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
