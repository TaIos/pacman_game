package pacman.graphics;

import pacman.entity.Ghost;
import pacman.entity.Pacman;
import pacman.grid.Grid;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GraphicsController extends JPanel implements DefaultGraphicsValues {
    private final Pacman pacman;
    private final Ghost ghosts[];
    private final Grid grid;

    private Graphics2D graphics2D;
    private Image backround;

    public GraphicsController(Pacman pacman, Ghost ghosts[], Grid grid) {
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.grid = grid;

        // load backround image
        try {
            File pathBackround = new File(BASE_IMAGE_PATH + File.separator + "background1.jpg");
            backround = ImageIO.read(pathBackround);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        Dimension dim = new Dimension(400, 400);
        setSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        */
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // erase whatever is currently drawn (JComponent superclass)
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(backround, 0, 0, null);
        drawMaze(g2d);
    }

    public void drawScene() {
        repaint();
    }

    private void drawMaze(Graphics2D g2d) {

    }

    public void showMenu() {

    }

}
