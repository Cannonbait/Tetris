/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cb.pong.model.utils;

/**
 *
 * @author Ivar
 */
public class Point {
    int x, y;
    
    
    
    public Point (int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Point (Point point){
        this.x = point.getX();
        this.y = point.getY();
    }
    
    public int getX (){
        return x;
    }
    
    public int getY (){
        return y;
    }
    
    public void incX (int amount){
        x = x + amount;
    }
    
    public void decX (int amount){
        x = x-amount;
    }
    
    public void decY (int amount){
        y = y - amount;
    }
    
    public String toString(){
        return "Point: " +x +", "+y;
    }
    
    
}
