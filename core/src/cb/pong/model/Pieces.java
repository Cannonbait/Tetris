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
public enum Pieces {

    I(new boolean[][]{
        {false, false, false, false},
        {true, true, true, true},
        {false, false, false, false},
        {false, false, false, false}
    }),
    O(new boolean[][]{
        {true, true},
        {true, true}
    }),
    T(new boolean[][]{
        {false, true, false},
        {true, true, true},
        {false, false, false}
    }),
    S(new boolean[][]{
        {false, true, true},
        {true, true, false},
        {false, false, false}
    });

    private boolean[][] piece;
    //Position is the top left corner
    private Point position;
    private ShapeRenderer shapeRenderer;
    private long moveTimer;

    private Pieces(boolean[][] shape) {
        this.piece = shape;
        position = new Point(0,0);
        shapeRenderer = new ShapeRenderer();
        moveTimer = System.currentTimeMillis();
    }

    public static Pieces getRandomPiece(Point startPos) {
        final Pieces[] representations = Pieces.values();
        Pieces piece = representations[(int) Math.floor(Math.random() * (representations.length - 1) + 0.5)];
        piece.setPosition(startPos);
        return piece;
    }
    
    public void render(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        for (Point pos:getBlocks()){
            shapeRenderer.rect(pos.getX()*20, pos.getY()*20, 18, 18);
        }
        shapeRenderer.end();
        
    }
    

    public void rotateCounterClockwise() {
        final int pieceHeight = piece.length;
        final int pieceWidth = piece[0].length;
        //Create an array with the inverse dimensions
        final boolean[][] rotatedPiece = new boolean[pieceWidth][pieceHeight];
        for (int column = 0; column < piece.length; column++) {
            for (int row = 0; row < piece[column].length; row++) {
                rotatedPiece[pieceWidth - row - 1][column] = piece[column][row];
            }
        }
        piece = rotatedPiece;
    }

    public void rotateClockwise() {
        final int pieceHeight = piece.length;
        final int pieceWidth = piece[0].length;
        //Create an array with the inverse dimensions
        final boolean[][] rotatedPiece = new boolean[pieceWidth][pieceHeight];
        for (int column = 0; column < piece.length; column++) {
            for (int row = 0; row < piece[column].length; row++) {
                rotatedPiece[row][pieceHeight - column - 1] = piece[column][row];
            }
        }
        piece = rotatedPiece;
    }

    public String toString() {
        StringBuilder stringSequence = new StringBuilder();
        for (int column = 0; column < piece.length; column++) {
            for (int row = 0; row < piece[column].length; row++) {
                stringSequence.append(piece[column][row] + ", ");
            }
            stringSequence.append("\n");
        }
        return stringSequence.toString();
    }

    public void moveDown() {
        position.decY(1);
    }
    
    public int getLeftBlockPos(){
        int pos = getBlocks()[0].getX();
        for (Point position:getBlocks()){
            if (position.getX() < pos){
                pos = position.getX();
            }
        }
        return pos;
    }
    
    public int getRightBlockPos(){
        int pos = getBlocks()[0].getX();
        for (Point position:getBlocks()){
            if (position.getX() > pos){
                pos = position.getX();
            }
        }
        return pos;
    }

    public void moveRight() {
        position.incX(1);
    }

    public void moveLeft() {
        position.decX(1);
    }

    public Point getPosition() {
        return position;
    }
    
    public void setPosition(Point position){
        this.position = new Point(position);
    }
    
    public Point[] getBlocks() {
        final Point[] blocks = new Point[4];
        int foundBlocks = 0;
        for (int row = 0; row < piece.length; row++){
            for (int column = 0; column < piece[0].length; column++){
                if (piece[row][column]){
                    blocks[foundBlocks] = new Point(position.getX()+column, position.getY()+row);
                    foundBlocks++;
                }
            }
        }
        return blocks;
    }

}
