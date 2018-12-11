package pacman.game;

import pacman.entity.Ghost;
import pacman.entity.Pacman;
import pacman.graphics.GraphicsController;
import pacman.grid.Grid;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Game implements DefaultGameValues, KeyListener {
    private Pacman pacman;
    private Ghost[] ghosts;
    private Grid grid;
    private GraphicsController graphicsController;
    private JFrame frame;
    private boolean pauseFlag = false;

    public Game() {
        loadImages();
        initGrid();
        initGhosts();
        initPacman();
        initGraphics();
    }

    public void start() {
        graphicsController.showMenu();
        tickComponent();
    }

    private void tickComponent() {
        while (true) {
            try {
                Thread.sleep(1000 / DEFAULT_FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (collision() && pacman.isDead()) {
                lost();
                break;
            } else
                resetGrid();

            if (grid.noMorePoints() && pacman.isAlive()) {
                won();
                break;
            }

            movePacman();
            moveGhotsts();

            graphicsController.drawScene();
        }
    }

    private void won() {

    }

    private void lost() {

    }

    private void pause() {
        pauseFlag = !pauseFlag;
    }

    void resetGrid() {

    }

    private void movePacman() {

    }

    private void moveGhotsts() {
        for (Ghost g : ghosts)
            g.move();
    }

    boolean collision() {

        return true;
    }

    private void initGrid() {
        grid = new Grid();
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

    private void initPacman() {
        pacman = new Pacman();
    }

    private void initGhosts() {
        ghosts = new Ghost[DEFAULT_GHOST_CNT];
        for (int i = 0; i < DEFAULT_GHOST_CNT; i++)
            ghosts[i] = new Ghost();
    }

    private void loadImages() {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                pacman.getPosition().setGoingLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                pacman.getPosition().setGoingRight();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                pacman.getPosition().setGoindUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                pacman.getPosition().setGoingDown();
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
