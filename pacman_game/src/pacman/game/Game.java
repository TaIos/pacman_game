package pacman.game;

import pacman.entity.Ghost;
import pacman.entity.Pacman;
import pacman.graphics.GraphicsController;
import pacman.grid.Grid;
import pacman.grid.Position;
import sun.java2d.loops.GeneralRenderer;

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
                //Thread.sleep(1000 / DEFAULT_FPS);
                Thread.sleep(200);
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
            //moveGhotsts();

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
        int data[][], x, y, blockSize,
                blockLeft, blockRight, blockTop, blockDown;

        blockSize = grid.getBlockSize();
        Position pos = pacman.getPosition();
        data = grid.getData();
        x = pacman.getPosition().getX();
        y = pacman.getPosition().getY();

        /*
        int a, b, c, d, e, f, g;
        a = (x + blockSize / 2) / blockSize;
        c = (x + blockSize / 2) / blockSize;
        d = (x + blockSize) / blockSize;

        e = (y + blockSize / 2) / blockSize;
        f = (y + blockSize / 2) / blockSize;
        g = (x + blockSize / 2) / blockSize;

        System.out.println("blockLeft " + a + ", " + c);
        System.out.println("blockRight " + a + ", " + d);
        System.out.println("blockTop " + e + ", " + g);
        System.out.println("blockDown " + f + ", " + g);

        blockLeft = data[a][c];
        blockRight = data[a][d];
        blockTop = data[e][g];
        blockDown = data[f][g];
        */

        blockDown = blockLeft = blockTop = blockRight = data[y / blockSize][x / blockSize];


        if ((blockLeft & 16) != 0) {
            data[y / blockSize][x / blockSize] = (blockLeft & 15);
            pacman.incScore();
        }

        /*
        // take point if there is any
        if ((blockLeft & 16) != 0) {
            data[a][c] = (blockLeft & 15);
            pacman.incScore();
        }
        if ((blockRight & 16) != 0) {
            data[a][d] = (blockRight & 15);
            pacman.incScore();
        }
        if ((blockTop & 16) != 0) {
            data[e][g] = (blockTop & 15);
            pacman.incScore();
        }
        if ((blockDown & 16) != 0) {
            data[f][g] = (blockDown & 15);
            pacman.incScore();
        }
        */

        int delta = 32;

        if (pos.isGoingLeft()) {
            if ((blockLeft & 1) == 0)
                pos.setX(x - delta);
        } else if (pos.isGoingUp()) {
            if ((blockTop & 2) == 0)
                pos.setY(y - delta);
        } else if (pos.isGoingRight()) {
            if ((blockRight & 4) == 0)
                pos.setX(x + delta);
        } else if (pos.isGoingDown()) {
            if ((blockDown & 8) == 0)
                pos.setY(y + delta);
        }
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
        // create 4 ghosts
        ghosts = new Ghost[4];

        //ghosts[0].ge
    }

    private void loadImages() {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_H:
                pacman.getPosition().setGoingLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_L:
                pacman.getPosition().setGoingRight();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_K:
                pacman.getPosition().setGoindUp();
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
            case KeyEvent.VK_J:
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
