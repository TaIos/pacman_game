package pacman.graphics;

import pacman.entity.Ghost;
import pacman.entity.Pacman;
import pacman.entity.entity_attributes.Lives;
import pacman.game.DefaultGameValues;
import pacman.grid.Grid;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsController extends JPanel implements DefaultGraphicsValues, DefaultGameValues {
    private final Pacman pacman;
    private final Ghost ghosts[];
    private final Grid grid;

    private Graphics2D graphics2D;
    private Image backround;

    public GraphicsController(Pacman pacman, Ghost ghosts[], Grid grid) {
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.grid = grid;

        loadImages();
    }

    private void loadImages() {

        // load backround image
        try {
            File pathBackground = new File(BASE_IMAGE_PATH + File.separator + "background1.jpg");
            backround = ImageIO.read(pathBackground);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Forces repainting of the screen
     */
    public void drawScene() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // erase whatever is currently drawn (JComponent superclass)
        g.drawImage(backround, 0, 0, null);
        Graphics2D g2d = (Graphics2D) g;
        drawMaze(g2d);
        drawPacman(g2d);
        drawGhosts(g2d);
        showScore(g2d);
    }

    private void drawMaze(Graphics2D g2d) {
        final int blkSize = grid.getBlockSize();
        final int[][] data = grid.getData();
        g2d.setStroke(new BasicStroke(DEFAULT_WALL_WIDTH));

        int x, y;
        for (int i = 0; i < grid.getHeightInBlocks(); i++) {
            y = i * blkSize;
            for (int j = 0; j < grid.getWidthInBlocks(); j++) {
                x = j * blkSize;
                g2d.setColor(DEFAULT_WALL_COLOR);

                if ((data[i][j] & 1) != 0) // left wall
                    g2d.drawLine(x, y, x, y + blkSize - 1);

                if ((data[i][j] & 2) != 0) // top wall
                    g2d.drawLine(x, y, x + blkSize - 1, y);

                if ((data[i][j] & 4) != 0) // right wall
                    g2d.drawLine(x + blkSize - 1, y, x + blkSize - 1, y + blkSize - 1);

                if ((data[i][j] & 8) != 0) // bottom wall
                    g2d.drawLine(x, y + blkSize - 1, x + blkSize - 1, y + blkSize - 1);

                if ((data[i][j] & 16) != 0) { // point
                    g2d.setColor(DEFAULT_DOT_COLOR);
                    //g2d.fillRect(x + blkSize / 2, y + blkSize / 2, DEFAULT_CIRCLE_DIAMETER, DEFAULT_CIRCLE_DIAMETER);
                    g2d.fillArc(x + blkSize / 2, y + blkSize / 2, DEFAULT_CIRCLE_DIAMETER, DEFAULT_CIRCLE_DIAMETER, 0, 360);
                }

            }
        }

    }

    private void drawPacman(Graphics2D g2d) {
        Image imPc = pacman.getImage();
        int x, y;
        x = pacman.getPosition().getX();
        y = pacman.getPosition().getY();
        g2d.drawImage(imPc, x, y, null);
    }

    private void drawGhosts(Graphics2D g2d) {

    }

    private void showScore(Graphics2D g2d) {
        String lives, points;
        lives = "Lives: " + pacman.getLives();
        points = "Score: " + pacman.getScore();

        g2d.setFont(DEFAULT_FONT);
        g2d.setColor(LIVES_COLOR);
        g2d.drawString(lives, 0, grid.getHeightInPixels() + TOP_BAR_HEIGHT_PIXEL / 2);

        g2d.drawString(points, grid.getWidthInPixels() - g2d.getFontMetrics().stringWidth(points),
                grid.getHeightInPixels() + TOP_BAR_HEIGHT_PIXEL / 2);
    }

    public void showMenu() {

    }
}
