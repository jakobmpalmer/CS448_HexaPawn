
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jakepalmer
 */
public class Move implements Cloneable{
    
    private int fromRow;
    private int fromColumn;
    private int toRow;
    private int toColumn;
    
    public Move(int y, int x, int toR, int toC){
        fromRow = y;
        fromColumn = x; 
        toRow = toR;
        toColumn = toC;                                
    }   

    public int getFromRow() {
        return fromRow;
    }

    public int getFromColumn() {
        return fromColumn;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToColumn() {
        return toColumn;
    }
    
    
    public boolean equalsMove(Move m){
        return (m.fromRow == fromRow) && (m.toColumn == toColumn) && (m.fromColumn == fromColumn) && (m.toRow == toRow);
    }
    
    
    public String toString(){
        return "From X" + fromColumn + "From Y " + fromRow + "\nTo X" + toColumn + " To Y " + toRow + "\n"; 
    }
    
    @Override
    public Move clone() throws CloneNotSupportedException{
        return (Move)super.clone();
    }
    
            
}
