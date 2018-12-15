package pacman.game;

import javafx.geometry.Pos;
import pacman.entity.Ghost;
import pacman.entity.Pacman;
import pacman.graphics.GraphicsController;
import pacman.grid.Grid;
import pacman.grid.MazeMaps.EmptyMaze;
import pacman.grid.MazeMaps.PJVMaze;
import pacman.grid.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game implements DefaultGameValues, KeyListener {
    private Pacman pacman;
    private Ghost[] ghosts;
    private Grid grid;
    private GraphicsController graphicsController;
    private JFrame frame;
    Random random;
    private boolean pauseFlag, leftKey, rightKey, upKey, downKey;

    public Game() {
        //grid = new Grid(new EmptyMaze());
        grid = new Grid(new PJVMaze());
        pacman = new Pacman();
        random = new Random();
        initFlags();
        loadImages();
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
            moveGhotsts();
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
        initGhosts();
        initGraphics();
        pacman.setPosition(new Position());
    }

    private void movePacman() {
        int data[][], x, y, blockSize, currBlock;

        blockSize = grid.getBlockSize();
        Position pos = pacman.getPosition();
        data = grid.getData();
        x = pacman.getPosition().getX();
        y = pacman.getPosition().getY();
        currBlock = data[y / blockSize][x / blockSize];
        int delta = pacman.getSpeed();

        // can change horizontal direction immediately
        if (pos.isGoingRight() && leftKey)
            pos.setGoingLeft();
        else if (pos.isGoingLeft() && rightKey)
            pos.setGoingRight();
        else if (pos.isGoingUp() && downKey)
            pos.setGoingDown();
        else if (pos.isGoingDown() && upKey)
            pos.setGoindUp();
        else {
            // perpendicular movement change to current movement
            if (x % blockSize == 0 && y % blockSize == 0) {
                if ((currBlock & 16) != 0) {
                    data[y / blockSize][x / blockSize] = (currBlock & 15);
                    pacman.incScore();
                }
                if (leftKey) {
                    if ((currBlock & 1) == 0) {
                        pos.setGoingLeft();
                    } else if (!((((currBlock & 2) == 0) && pos.isGoingUp())
                            || (((currBlock & 8) == 0)) && pos.isGoingDown()))
                        delta = 0;
                } else if (upKey) {
                    if ((currBlock & 2) == 0) {
                        pos.setGoindUp();
                    } else if (!((((currBlock & 1) == 0) && pos.isGoingLeft())
                            || ((currBlock & 4) == 0) && pos.isGoingRight()))
                        delta = 0;
                } else if (rightKey) {
                    if ((currBlock & 4) == 0) {
                        pos.setGoingRight();
                    } else if (!((((currBlock & 2) == 0) && pos.isGoingUp())
                            || ((currBlock & 8) == 0) && pos.isGoingDown()))
                        delta = 0;
                } else if (downKey) {
                    if ((currBlock & 8) == 0) {
                        pos.setGoingDown();
                    } else if (!((((currBlock & 1) == 0) && pos.isGoingLeft())
                            || ((currBlock & 4) == 0) && pos.isGoingRight()))
                        delta = 0;
                }
            }
        }


        // make the move
        if (pos.isGoingLeft()) {
            pos.setX(x - delta);
        } else if (pos.isGoingUp()) {
            pos.setY(y - delta);
        } else if (pos.isGoingRight()) {
            pos.setX(x + delta);
        } else if (pos.isGoingDown()) {
            pos.setY(y + delta);
        }

    }

    private void moveGhotsts() {
        int x, y, blkSize, currBlock, data[][];
        Position pos;
        blkSize = grid.getBlockSize();
        data = grid.getData();

        for (Ghost g : ghosts) {
            pos = g.getPosition();
            x = pos.getX();
            y = pos.getY();
            currBlock = data[y / blkSize][x / blkSize];

            if (x % blkSize == 0 && y % blkSize == 0) {
                do {
                    int rng = random.nextInt(8);
                    // check if can continue moving one direction
                    if (((pos.isGoingLeft() && ((currBlock & 1) == 0))
                            || (pos.isGoingUp() && ((currBlock & 2) == 0))
                            || (pos.isGoingRight() && ((currBlock & 4) == 0))
                            || (pos.isGoingDown() && ((currBlock & 8) == 0)))
                            && rng != 0)
                        break;

                    int direction = random.nextInt(4);
                    if ((direction == 0)  // left
                            && ((currBlock & 1) == 0)) {
                        pos.setGoingLeft();
                        break;
                    } else if ((direction == 1) // right
                            && ((currBlock & 4) == 0)) {
                        pos.setGoingRight();
                        break;
                    } else if ((direction == 2) // up
                            && ((currBlock & 2) == 0)) {
                        pos.setGoindUp();
                        break;
                    } else if ((direction == 3) // down
                            && ((currBlock & 8) == 0)) {
                        pos.setGoingDown();
                        break;
                    }
                } while (true);
            }

            if (pos.isGoingLeft()) {
                pos.setX(x - g.getSpeed());
            } else if (pos.isGoingUp()) {
                pos.setY(y - g.getSpeed());
            } else if (pos.isGoingRight()) {
                pos.setX(x + g.getSpeed());
            } else if (pos.isGoingDown()) {
                pos.setY(y + g.getSpeed());
            }
        }
    }

    private boolean collision() {
        int x, y, gx, gy, blkSize;
        Position pos = pacman.getPosition();
        x = pos.getX();
        y = pos.getY();
        blkSize = grid.getBlockSize();

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
        ghosts[0].setPosition(new Position(7 * blkSIze, 7 * blkSIze));

        ghosts[1] = new Ghost("ghost_princess_right.png");
        ghosts[1].setPosition(new Position(7 * blkSIze, 8 * blkSIze));

        ghosts[2] = new Ghost("ghost_soldier_right.png");
        ghosts[2].setPosition(new Position(7 * blkSIze, 9 * blkSIze));

        ghosts[3] = new Ghost("ghost_blue_right.png");
        ghosts[3] = new Ghost("vagner.png");
        ghosts[3].setPosition(new Position(7 * blkSIze, 10 * blkSIze));
        ghosts[3].setAI(true);
    }

    private void loadImages() {

    }

    private void initFlags() {
        pauseFlag = leftKey = rightKey = upKey = downKey = false;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        switch (key) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_H:
                leftKey = true;
                rightKey = upKey = downKey = false;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
            case KeyEvent.VK_L:
                rightKey = true;
                leftKey = upKey = downKey = false;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_K:
                upKey = true;
                leftKey = rightKey = downKey = false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
            case KeyEvent.VK_J:
                downKey = true;
                leftKey = rightKey = upKey = false;
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
