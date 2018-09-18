
import java.awt.Color;
import java.awt.Graphics;

//import java.util.ArrayList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jakepalmer
 */
public class Gameboard implements java.lang.Cloneable{

    private boolean DEBUG = true;
    private int[][] theBoard;
    private int currentTurn = 1;
    private int boardDimensions;
    private static final int X = -1;
    private static final int O = 1;
    private int isEmpty = 0;
    private boolean gameOver = false;
    private int winnerInt = 0;

    //Draw Vars
    int pressX = 0;
    int pressY = 0;
    int releaseX = 0;
    int releaseY = 0;
    int mouseX = 0;
    int mouseY = 0;
    boolean selected = false;
    int pieceR = 80;
    int tileWidth = 200;

    //CONSTRUCTORS---------------------------------
    public Gameboard() {
    }

    public Gameboard(int dimensions) {
        theBoard = new int[dimensions][dimensions];

        boardDimensions = dimensions;

        for (int x = 0; x < boardDimensions; x++) {
            System.out.println("x = " + x);
            theBoard[x][0] = O;
            theBoard[x][boardDimensions - 1] = X;

        }
    }
//-------------------------------------------------

    public char[][] boardToChar() {

        char[][] charBoardArray = new char[boardDimensions][boardDimensions];

        for (int i = 0; i < boardDimensions; i++) {
            for (int j = 0; j < boardDimensions; j++) {

                //System.out.println("GameBoard Input:" + theBoard[i][j]);
                if (theBoard[i][j] == 1) {
                    charBoardArray[i][j] = 'o';
                } else if (theBoard[i][j] == -1) {
                    charBoardArray[i][j] = 'x';
                } else {
                    //System.out.println(theBoard[i][j]);
                    charBoardArray[i][j] = '-';
                }

                //System.out.println(intBoardArray[i][j] + "\n");
            }
        }
        return charBoardArray;
    }


    

    public boolean makeMove(Move m) {
        MoveList theList = listLegalMoves(this, currentTurn);
        int fromX = m.getFromColumn();
        int fromY = m.getFromRow();
        int toX = m.getToColumn();
        int toY = m.getToRow();

        for (int i = 0; i < theList.size(); i++) {

            if (m.equalsMove(theList.get(i))) {

                theBoard[toX][toY] = theBoard[fromX][fromY];
                theBoard[fromX][fromY] = 0;
                if (checkWin(currentTurn)) {
                    System.out.println("Game Over! Winner is " + winnerInt);
                }

                currentTurn = -currentTurn;

                return true;

            }
        }

        return false;
    }

//    public MoveList getLegalMoves() {
//
//        MoveList moveList = new MoveList();
//
//        for (int i = 0; i < getBoardDimensions(); i++) { //CHECK EACH PIECE
//            for (int j = 0; j < getBoardDimensions(); j++) {
//                //I J SCOPE-----------------------------------------------------
//
//                if (theBoard[i][j] == -1) {          //IF Opponent Piece
//                    System.out.println("That i = " + i);
//                    System.out.println("j = " + j);
//
//                    if (i + 1 <= theBoard.length && (theBoard[i + 1][j] == 0)) { //If forward is open
//                        System.out.println("This i = " + i);
//                        System.out.println("j = " + j);
//                        moveList.add(new Move(i, j, i + 1, j));
//
//                        if (DEBUG) {
//                            System.out.println("Take i = " + i);
//                            System.out.println("Take j = " + j);
//                            System.out.println("Board Length: " + theBoard.length);
//                        }
//
//                        if ((j + 1 < theBoard.length) && (theBoard[i + 1][j + 1] == 1)) { //IF take is open
//
//                            moveList.add(new Move(i, j, i + 1, j + 1));//Take right
//
//                        } else if (j - 1 > -1 && theBoard[i + 1][j - 1] == 1) {
//                            moveList.add(new Move(i, j, i + 1, j - 1)); //takeLeft
//                        } else {
//                            System.out.println("No Legal Moves");
//                        }
//
//                    }
//
//                }
//
//            }//-----------------------------------------------------------------
//        }
//
//        return moveList;
//    }
    public boolean isLegalMove(Move m, int currentPlayer) {

        int fromX = m.getFromColumn();
        int fromY = m.getFromRow();
        int toX = m.getToColumn();
        int toY = m.getToRow();
        if ((fromY < boardDimensions) && (fromY >= 0) && (toY < boardDimensions)
                && (toY >= 0) && (fromX < boardDimensions) && (fromX >= 0) && (toX < boardDimensions)
                && (toX >= 0)) { //Board Bounds

            if (theBoard[fromX][fromY] == currentPlayer) {
                if (toY - fromY == currentPlayer) {
                    if (fromX == toX) { //Forward Movement
                        return theBoard[toX][toY] == isEmpty;
                    } else if ((fromX == toX + 1) || (fromX == toX - 1)) { //Take
                        return theBoard[toX][toY] == -currentTurn; //Check if the space has an opponent
                    }

                }

            }
        }
        return false;
    }

    public MoveList listLegalMoves(Gameboard theBoard, int currentPlayer) {
        MoveList moveList = new MoveList();

        for (int x = 0; x < boardDimensions; x++) {
            for (int y = 0; y < boardDimensions; y++) {
                Move forwardMove = new Move(y, x, y + currentTurn, x);
                Move forwardMoveTakeR = new Move(y, x, y + currentTurn, x + 1);
                Move forwardMoveTakeL = new Move(y, x, y + currentTurn, x - 1);

                if (theBoard.getGameBoard()[x][y] != 0) {

                    if ((((theBoard.getGameBoard()[x][y] == O) && (y < boardDimensions - 1)) || ((X == theBoard.getGameBoard()[x][y]) && (y > 0)))) { //IF x is moving up or two the side, OR o is moving down or two the side
                        if (isLegalMove(forwardMove, currentPlayer)) {
                            moveList.add(forwardMove);
                        }
                        
                        
                        if ((x < boardDimensions -1) && isLegalMove(forwardMoveTakeR, currentPlayer)) {
                            
                            moveList.add(forwardMoveTakeR);
                        
                        }
                        if ((x > 0) && isLegalMove(forwardMoveTakeL, currentPlayer)) {
                            
                            moveList.add(forwardMoveTakeL);
                        
                        }
                        

                    }
                                        
                }
            }
        }
        //System.out.println("LEGAL MOVES");
        for (Move move : moveList) {
            System.out.println("  Move: " + move.getFromColumn() + ", " + move.getFromRow() + " | " + move.getToColumn() + ", " + move.getToRow());
        }
        return moveList;
    }

    public char[][] getCharBoardArray() {
        return boardToChar();
    }

    public int[][] getGameBoard() {
        return theBoard;
    }

    public int getBoardDimensions() {
        return boardDimensions;
    }

    public int getPlayerAt(int y, int x) {
        System.out.println("Player IS: " + getGameBoard()[y][x]);
        return getGameBoard()[y][x];
    }

    public String toString() {

        char[][] charArray = boardToChar();

        String gameBoard = "";

        for (int i = 0; i < getBoardDimensions(); i++) {
            for (int j = 0; j < getBoardDimensions(); j++) {

                gameBoard += charArray[j][i];

            }
            gameBoard += "\n";
        }
        return gameBoard;
    }

    public String toBoolString() {
        String str = "";
        for (int i = 0; i < getBoardDimensions(); i++) {
            for (int j = 0; j < getBoardDimensions(); j++) {
                str += theBoard[j][i];
            }
            str += "\n";
        }
        return str;
    }

    private boolean checkWin(int currentTurn) {
       // System.out.println("CHECK WIN");
        for (int x = 0; x < boardDimensions; x++) {

            int y = currentTurn == X ? 0 : boardDimensions - 1;
         //   System.out.println("y = " + y);
         //   System.out.println("Board X: " + theBoard[x][y]);
            if (theBoard[x][y] == currentTurn) {
         //       System.out.println("Current turn = " + currentTurn);
                winnerInt = currentTurn;
                gameOver = true;
                return true;
            }
        }

        MoveList opponentMoves = listLegalMoves(this, currentTurn);
        if (opponentMoves.isEmpty()) {
            winnerInt = currentTurn;
            gameOver = true;
            return true;
        }

        return false;
    }
    
    
    @Override
    public Gameboard clone() throws CloneNotSupportedException{
        Gameboard someBoard = (Gameboard) super.clone();
        int[][] intArray = new int[boardDimensions][boardDimensions];
            for (int i = 0; i < boardDimensions; i++) {
            System.arraycopy(theBoard[i], 0, intArray[i], 0, boardDimensions);
        }
            someBoard.setTheBoard(intArray);
            return someBoard;
    }
     
    
    
    public boolean equalsBoard(Gameboard aBoard){
        for (int i = 0; i < boardDimensions; i++) {
            for (int j = 0; j < boardDimensions; j++) {
                if (this.theBoard[j][i] != aBoard.getGameBoard()[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
    

    //PAINT STUFFF
    public void drawBoard(Graphics myGraphics) {

        int size = myGraphics.getClipBounds().width;
        
        int increment = size / getBoardDimensions();
        

        int xIncrement = increment;
        int yIncrement = increment;
        
        if (DEBUG) {
            System.out.println("DrawSize: " + size);
            System.out.println("boardDimensions = " + getBoardDimensions());    
            System.out.println("yIncrement = " + yIncrement);
            System.out.println("xIncrement = " + xIncrement);
        }

        
        
        for (int i = 0; i < boardDimensions; i++) {            
            System.out.println("i = " + i);
            myGraphics.fillRect(xIncrement, 0, 5, 600);
            xIncrement += xIncrement;
        }
        for (int i = 0; i < boardDimensions; i++) {
            System.out.println("i = " + i);
            myGraphics.fillRect(0, yIncrement, 600, 5);
            yIncrement += yIncrement;
        }

    }

    public void drawPieces(Graphics myGraphics) {
        for (int x = 0; x < boardDimensions; x++) {
            for (int y = 0; y < boardDimensions; y++) {

                
                if ((theBoard[x][y] != 0) && !((selected) && (x == pressX) && (y == pressY))) {
                    drawPiece(myGraphics, x, y);
                }

            }
        }
        if (selected) {
            drawFloatingPiece(myGraphics, pressX, pressY, mouseX, mouseY);
        }
    }

    public void paint(Graphics myGraphics) {
        drawBoard(myGraphics);
        drawPieces(myGraphics);
    }

    private void drawPiece(Graphics myGraphics, int x, int y) {

        if (theBoard[x][y] == X) {
            myGraphics.setColor(Color.red);
        } else {
            myGraphics.setColor(Color.BLACK);
        }

        myGraphics.fillOval((x * tileWidth) + tileWidth / 2, y * tileWidth + tileWidth / 2, pieceR, pieceR);

    }

    private void drawFloatingPiece(Graphics myGraphics, int pressX, int pressY, int mouseX, int mouseY) {

        if (theBoard[pressX][pressY] == X) {
            myGraphics.setColor(Color.red);
        } else {
            myGraphics.setColor(Color.BLACK);
        }

        myGraphics.fillOval(mouseX - pieceR / 2, mouseY - pieceR / 2, pieceR, pieceR);

    }

    public void setMouse(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    public void handlePressed(int x, int y) {
        System.out.println("Handle Pressed");
        pressX = mouseToBoard(x);
        pressY = mouseToBoard(y);

        if (pressX < boardDimensions && pressY < boardDimensions) {
            selected = true;
        }
    }

    public boolean handleRealesed(int x, int y) {
        System.out.println("Handle Released");
        releaseX = mouseToBoard(x);
        releaseY = mouseToBoard(y);
        selected = false;

        return makeMove(new Move(pressY, pressX, releaseY, releaseX));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int mouseToBoard(int x) {
        return x / tileWidth;
    }

    public void setTheBoard(int[][] theBoard) {
        this.theBoard = theBoard;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

}
