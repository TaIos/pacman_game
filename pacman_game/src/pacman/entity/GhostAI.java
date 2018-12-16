package pacman.entity;

import javafx.geometry.Pos;
import javafx.util.Pair;
import pacman.grid.Grid;
import pacman.grid.Position;

import java.util.*;

public class GhostAI extends Ghost {
    private final Grid grid;
    private final Position pacmanPosition;
    private Queue<Position> path;

    public GhostAI(String imageName, Grid grid, Position pacmanPosition) {
        super(imageName);
        this.grid = grid;
        this.pacmanPosition = pacmanPosition;
        path = new LinkedList<>();
    }

    @Override
    public void move(int tile, boolean isAtCentre) {
        if (path.isEmpty())
            generatePath();

        int x, y, dx, dy;
        x = position.getX();
        y = position.getY();
        dx = path.peek().getX();
        dy = path.peek().getY();

        // moving vertically
        if (y == dy) {
            if (dx - x > 0)
                position.setGoingRight();
            else if (dx - x < 0)
                position.setGoingLeft();
        } // moving horizontally
        else {
            if (dy - y > 0)
                position.setGoingDown();
            else if (dy - y < 0)
                position.setGoingUp();

        }

        if (x == dx && y == dy)
            path.poll();
        else
            writeMove();
    }

    private void generatePath() {
        int bfsData[][] = new int[grid.getWidthInBlocks()][grid.getHeightInBlocks()];
        int x, y;

        x = position.getX() / grid.getBlockSize();
        y = position.getY() / grid.getBlockSize();
        for (int arr[] : bfsData)
            Arrays.fill(arr, -1);
        bfsData[y][x] = GHS_MARK;
        bfsData[pacmanPosition.getY() / grid.getBlockSize()][pacmanPosition.getX() / grid.getBlockSize()] = PCM_MARK;

        BFS(bfsData, position);
        //printBFS(bfsData);
        createPathFromBFS(bfsData);
    }

    private void printBFS(int bfsData[][]) {
        int wait = 3000;
        System.out.println("Waiting for " + wait / 1000 + " seconds");
        for (int i = 0; i < grid.getHeightInBlocks(); i++) {
            System.out.println();
            for (int j = 0; j < grid.getWidthInBlocks(); j++) {
                System.out.print(String.format("%1$-3s", bfsData[i][j]));
            }
        }
        System.out.println();
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void BFS(int[][] bfsData, Position initPos) {
        Queue<Pair<Position, Integer>> neighbors = new LinkedList<>();
        int x, y, dx, dy, tile, blkSize, depth;
        blkSize = grid.getBlockSize();
        Pair<Position, Integer> pair;
        Position p;

        neighbors.add(new Pair<>(initPos, 0));

        while (!neighbors.isEmpty()) {
            pair = neighbors.poll();
            p = pair.getKey();
            dx = p.getX();
            dy = p.getY();
            x = dx / blkSize;
            y = dy / blkSize;
            tile = grid.blockAt(dx, dy);
            depth = pair.getValue();

            // if the Pacman has been reached
            if ((p.canGoLeft(tile) && bfsData[y][x - 1] == PCM_MARK)
                    || (p.canGoUp(tile) && bfsData[y - 1][x] == PCM_MARK)
                    || (p.canGoRight(tile) && bfsData[y][x + 1] == PCM_MARK)
                    || (p.canGoDown(tile) && bfsData[y + 1][x] == PCM_MARK))
                break;

            if (p.canGoLeft(tile) && bfsData[y][x - 1] == DEF_MARK) {
                neighbors.add(new Pair<>(new Position(dx - blkSize, dy), depth + 1));
                bfsData[y][x - 1] = depth + 1;
            }

            if (p.canGoUp(tile) && bfsData[y - 1][x] == DEF_MARK) {
                neighbors.add(new Pair<>(new Position(dx, dy - blkSize), depth + 1));
                bfsData[y - 1][x] = depth + 1;
            }

            if (p.canGoRight(tile) && bfsData[y][x + 1] == DEF_MARK) {
                neighbors.add(new Pair<>(new Position(dx + blkSize, dy), depth + 1));
                bfsData[y][x + 1] = depth + 1;
            }

            if (p.canGoDown(tile) && bfsData[y + 1][x] == DEF_MARK) {
                neighbors.add(new Pair<>(new Position(dx, dy + blkSize), depth + 1));
                bfsData[y + 1][x] = depth + 1;
            }
        }
    }

    private void createPathFromBFS(int bfsData[][]) {
        Stack<Position> res = new Stack<>();
        int x, y, dx, dy, tile, val, blkSize = grid.getBlockSize();

        dx = (pacmanPosition.getX() / blkSize) * blkSize;
        dy = (pacmanPosition.getY() / blkSize) * blkSize;

        res.push(new Position(dx, dy));

        while (true) {
            Position p = res.peek();
            dx = p.getX();
            dy = p.getY();
            x = dx / blkSize;
            y = dy / blkSize;
            tile = grid.blockAt(dx, dy);
            Pair<Position, Integer> best = new Pair<>(null, Integer.MAX_VALUE);

            if (p.canGoLeft(tile)) {
                val = bfsData[y][x - 1];
                if (val < best.getValue() && val != PCM_MARK && val != DEF_MARK)
                    best = new Pair<>(new Position(dx - blkSize, dy), val);
            }
            if (p.canGoUp(tile)) {
                val = bfsData[y - 1][x];
                if (val < best.getValue() && val != PCM_MARK && val != DEF_MARK)
                    best = new Pair<>(new Position(dx, dy - blkSize), val);
            }
            if (p.canGoRight(tile)) {
                val = bfsData[y][x + 1];
                if (val < best.getValue() && val != PCM_MARK && val != DEF_MARK)
                    best = new Pair<>(new Position(dx + blkSize, dy), val);
            }
            if (p.canGoDown(tile)) {
                val = bfsData[y + 1][x];
                if (val < best.getValue() && val != PCM_MARK && val != DEF_MARK)
                    best = new Pair<>(new Position(dx, dy + blkSize), val);
            }

            res.push(best.getKey());
            if (best.getValue() == GHS_MARK)
                break;
        }
        for (int i = 0; i < GHOST_AI_PATH_LEN + 1 && !res.empty(); i++)
            path.add(res.pop());
    }

}
