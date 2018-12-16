package pacman.game;

import pacman.entity.Ghost;
import pacman.entity.GhostAI;
import pacman.entity.Pacman;
import pacman.graphics.GraphicsController;
import pacman.grid.Grid;
import pacman.grid.MazeMaps.PJVMaze;
import pacman.grid.Position;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements DefaultGameValues, KeyListener {
    private Pacman pacman;
    private Ghost[] ghosts;
    private Grid grid;
    private GraphicsController graphicsController;
    private JFrame frame;
    private JLabel infoLabel;
    private boolean pauseFlag, inGameFlag;

    public Game() {
        grid = new Grid(new PJVMaze());
        pacman = new Pacman();
        initFlags();
        initGhosts();
        initGraphics();
    }

    public void start() {
        try {
            run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() throws InterruptedException {
        waitScreen();
        tickComponent();
        resetGame();
        run();
    }

    private void tickComponent() throws InterruptedException {
        while (true) {
            Thread.sleep(DEFAULT_DELAY_MILISECONDS);
            if (pauseFlag)
                pause();

            if (collision()) {
                pacman.decLives();
                graphicsController.drawScene();
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

    private void waitScreen() throws InterruptedException {
        infoLabel.setText("Press SPACE to start");
        while (!inGameFlag)
            Thread.sleep(10);
        infoLabel.setText("");
    }

    private void won() throws InterruptedException {
        inGameFlag = false;
        infoLabel.setText("WON !");
        Thread.sleep(5000);
        infoLabel.setText("");
    }

    private void lost() throws InterruptedException {
        inGameFlag = false;
        infoLabel.setText("LOST !");
        Thread.sleep(5000);
        infoLabel.setText("");
    }

    private void pause() throws InterruptedException {
        infoLabel.setText("PAUSE");
        while (pauseFlag)
            Thread.sleep(10);
        infoLabel.setText("");
    }

    private void resetGrid() {
        initFlags();
        pacman.reset();
        initGhosts();
        resetGraphics();
    }

    private void resetGame() {
        grid = new Grid(new PJVMaze());
        pacman = new Pacman();
        initFlags();
        initGhosts();
        resetGraphics();
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

        infoLabel = new JLabel("");
        infoLabel.setFont(new Font("Serif", Font.BOLD, 23));
        infoLabel.setForeground(Color.GREEN);
        infoLabel.setPreferredSize(new Dimension(grid.getWidthInPixels(), grid.getHeightInPixels()));
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setVerticalAlignment(JLabel.CENTER);
        graphicsController.add(infoLabel);
    }

    private void initGhosts() {
        if (DEFAULT_GHOST_CNT != 6)
            throw new Error("Ghost count is not equal to 6");

        int blkSIze = grid.getBlockSize();

        ghosts = new Ghost[DEFAULT_GHOST_CNT];

        ghosts[0] = new Ghost("ghost_policeman_right.png");
        ghosts[0].setPosition(new Position(10 * blkSIze, 5 * blkSIze));

        ghosts[1] = new Ghost("ghost_princess_right.png");
        ghosts[1].setPosition(new Position(10 * blkSIze, 3 * blkSIze));

        ghosts[2] = new Ghost("ghost_soldier_right.png");
        ghosts[2].setPosition(new Position(12 * blkSIze, 5 * blkSIze));

        ghosts[3] = new Ghost("ghost_viking_right.png");
        ghosts[3].setPosition(new Position(12 * blkSIze, 3 * blkSIze));

        ghosts[4] = new Ghost("ghost_pirate_right.png");
        ghosts[4].setPosition(new Position(11 * blkSIze, 4 * blkSIze));


        ghosts[5] = new GhostAI("ghost_blue_right.png", grid, pacman.getPosition());
        //ghosts[5] = new GhostAI("vagner.png", grid, pacman.getPosition());
        ghosts[5].setPosition(new Position(13 * blkSIze, 13 * blkSIze));
    }

    private void initFlags() {
        pauseFlag = false;
        inGameFlag = false;
    }

    private void resetGraphics() {
        graphicsController.setPacman(pacman);
        graphicsController.setGhosts(ghosts);
        graphicsController.setGrid(grid);
        infoLabel.setText("");
        graphicsController.drawScene();
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
                pauseFlag = !pauseFlag;
                break;
            case KeyEvent.VK_SPACE:
                inGameFlag = true;
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
