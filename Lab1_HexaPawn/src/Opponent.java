
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jakepalmer
 */
public class Opponent {

    //Member Vars               
    Learner theLearner;
       

   //Constructor-------------------------------       
    public Opponent() {
        theLearner = new Learner();              
        
    }
    //------------------------------------------

    //Methods
    public void move(Gameboard gameBoard) throws CloneNotSupportedException {                           
            selectRandomMoveAndLearn(gameBoard);                   
    }                    

    public Move selectRandomMove(Gameboard theBoard) {
        MoveList list = theBoard.listLegalMoves(theBoard, theBoard.getCurrentTurn());
        int listSize = list.size();
        int r = rand(listSize);
        return list.get(r);
    }
    
    public Move selectRandomMove(MoveList list, Gameboard someBoard) {
        
        int listSize = list.size();
        int r = rand(listSize);
        return list.get(r);
    }
    
    public Move selectRandomMoveAndLearn(Gameboard theBoard) throws CloneNotSupportedException {
        MoveList list = theBoard.listLegalMoves(theBoard, theBoard.getCurrentTurn());
        theLearner.Trim(list, theBoard); // <------- Second: trim losing moves
        int r = rand(list.size());
        if (list.isEmpty()) {
            theLearner.YouAreDoomed(); 
            
            return selectRandomMove(theBoard);    // <-------- First: remember our last move
        } else {
            Move m = selectRandomMove(list, theBoard);
            theLearner.setLastMove(theBoard, m);
            return m;
        }
        //return list.get(r);
    }
          

   
    
    public void recordLoss(){
        theLearner.YouAreDoomed();
    }
    
    public int rand(int r){        
        double randomDouble = (Math.random() * 10);                
        
        if (r > 1) {
            Random rand = new Random();
            return rand.nextInt(r-1);
            
        } else {
            return 0;
        }
    }
    
    
    

}
