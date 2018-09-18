
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
public class MoveList extends ArrayList<Move> {

    //MoveList moveList = new MoveList();

    public String toString() {
        String str = "";
        System.out.println("ArrayList<MOVE> = Size: " + size());
            for (int i = 0; i < size(); i++) {                
                str += "\n---------------------\nMove" + i + "\n---------------------\nFrom Column: " + get(i).getFromColumn() + "From Row: " + get(i).getFromRow() +
                        "\n To Column: " + get(i).getToColumn() + " To Row: " + get(i).getToRow() + "\n";
                        
                
            }

        return str;

    }
}
