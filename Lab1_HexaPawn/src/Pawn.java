
import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
//import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jakepalmer
 */
public class Pawn {
    int pawnX;
    int pawnY;   
    int circleR = 80;
     
    
    //Constructors
    public Pawn(){    
    }
    
    public Pawn(int x, int y){
        Button pawnBtn = new Button();
        pawnX = x;
        pawnY = y;
        
        
    }
    //----------------------------------------
           
    public void DrawPiece(Graphics g){
        int r = 80;
                
        //g.drawOval(pawnX - circleR/2, pawnY - circleR/2, circleR, circleR);
        g.fillOval(pawnX - circleR/2, pawnY - circleR/2, circleR, circleR);
    }
    
    public int Forward(){
        return pawnX += 200;
    }
    
    public void PawnTakeLeft(){
        pawnX += 200;
        pawnY -= 200;
    }
    
    public void PawnTakeRight(){
        pawnX += 200;
        pawnY += 200;
    }
    
    

    public int getX() {
        return pawnX;
    }

    public int getY() {
        return pawnY;
    }
    
    public String toString(){
        return "This Pawn is at X: " + pawnX + " Y: " + pawnY;
    }
    
}
