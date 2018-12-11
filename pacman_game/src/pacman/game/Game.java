package pacman.game;

import pacman.entity.Ghost;
import pacman.entity.Pacman;
import pacman.graphics.GraphicsController;
import pacman.grid.Grid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game implements DefaultGameValues, ActionListener {
    private Timer timer;
    private Pacman pacman;
    private Ghost[] ghosts;
    private Grid grid;
    private GraphicsController graphicsController;
    private JFrame frame;

    public Game() {
        pacman = new Pacman();
        ghosts = new Ghost[DEFAULT_GHOST_CNT];
        for (int i = 0; i < DEFAULT_GHOST_CNT; i++)
            ghosts[i] = new Ghost();

        loadImages();

        grid = new Grid();

        graphicsController = new GraphicsController(pacman, ghosts, grid);

        frame = new JFrame();

        // setting size
        frame.setSize(new Dimension(grid.getWidthInPixels(), grid.getHeightInPixels() + TOP_BAR_HEIGHT_PIXEL));
        frame.setResizable(false);

        frame.add(graphicsController);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        timer = new Timer(DEFAULT_DELAY_IN_SECONDS, this);
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

    private void movePacman() {

    }

    private void moveGhotsts() {
        for (Ghost g : ghosts)
            g.move();
    }

    void resetGrid() {

    }

    boolean collision() {

        return true;
    }


    private void won() {

    }

    private void lost() {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    private void loadImages() {

    }
}
