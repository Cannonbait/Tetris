/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb.pong.model;

import cb.pong.model.utils.Point;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Ivar
 */
public class Board {

    private final int width = 10;
    private final int height = 22;
    private final int droprate = 500;
    private boolean blocks[][] = new boolean[width][height];
    private final Point startPos;
    private Pieces activePiece;
    private Pieces nextPiece;
    private long lastDrop;

    public Board() {
        startPos = new Point(width / 2, height - 12);
        activePiece = Pieces.getRandomPiece(startPos);
        nextPiece = Pieces.getRandomPiece(startPos);
        lastDrop = System.currentTimeMillis();
        
        for(int column = 0; column < width; column++){
            blocks[column][0] = true;
        }
    }

    public void update() {
        if (System.currentTimeMillis() - lastDrop > droprate) {
            lastDrop = System.currentTimeMillis();
            if (collision()) {
                place(activePiece);
                activePiece = nextPiece;
                nextPiece = Pieces.getRandomPiece(startPos);
            } else {
                activePiece.moveDown();
            }
        }

    }

    public void render() {
        renderBlocks();
        activePiece.render();
    }

    private void renderBlocks() {
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (blocks[x][y]) {
                    shapeRenderer.rect(x * 20, y * 20, 18, 18);
                }
            }
        }
        shapeRenderer.end();
    }

    private boolean collision() {
        for (Point block : activePiece.getBlocks()) {
            //Check if there are blocks in the way
            if (block.getY() == 0) {
                return true;
            } //Check if any of the blocks have reached the end of the play area
            else if (blocks[block.getX()][block.getY() - 1]) {
                return true;
            }
        }
        return false;
    }

    private void place(Pieces piece) {
        for (Point block : activePiece.getBlocks()) {
            blocks[block.getX()][block.getY()] = true;
        }
        removeRows();
    }

    private void removeRows() {
        int offset = 0;
        boolean newBlocks[][] = new boolean[width][height];
        //Loop through every row from bottom
        for (int row = 0; row < height-1; row++) {
            //By default we do not want to keep it
            boolean keep = false;
            for (int column = 0; column < width && !keep; column++) {
                //If we find a block space on that row without a block we want to keep it
                if (!blocks[column][row]) {
                    keep = true;
                }
            }
            //If we keep the row
            if (keep) {
                //Loop through all the columns and place them in the new matrice
                for (int column = 0; column < width; column++) {
                    newBlocks[column][row-offset] = blocks[column][row];
                }
            }
            else {
                //All coming rows should drop an additional one step
                offset++;
            }

        }
        blocks = newBlocks;
    }

    public void moveLeft() {
        if (activePiece.getLeftBlockPos() > 0) {
            activePiece.moveLeft();
        }
    }

    public void moveRight() {
        if (activePiece.getRightBlockPos() < width - 1) {
            activePiece.moveRight();
        }
    }

    public void rotateCounter() {
        activePiece.rotateCounterClockwise();
    }

    public void rotateClock() {
        activePiece.rotateClockwise();
    }

}
