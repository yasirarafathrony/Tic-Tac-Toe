/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic.tac.toe;

/**
 *
 * @author Yasir Arafath
 * 
*/
import java.util.Scanner;
public class TicTacToe {
 
private static int randomizeStart(){
    int startNum = (int)(Math.random()*100)+1;// get a number between 1-100.
    int ret = (startNum % 2==0)? (1):(2);
    return ret;

}

private static void showBoard(int[][] gameBoard){
    int square = 1;
    
    final int cross = character('X');
    final int zero = character('O');
    
    for (int row = 0; row <gameBoard.length; ++row){
        for(int col = 0; col <gameBoard[row].length; ++col){
            if(gameBoard[row][col]== cross)
                System.out.print('X');// print X.
            else if (gameBoard[row][col]== zero)
                System.out.print('O');// print O.
            else
                System.out.print(square);// print square number.
            
            
            if (col == gameBoard[row].length-1)
                System.out.println();// Last number of the row printed, print a line change.
            else
                System.out.print('|');//after the first and second number of each row print | character.
            square++; //number of next sauare is one bigger than the previous.
        
        }
            if (row !=gameBoard.length -1)
                System.out.println("-+-+-");
            
            
            }
}   
    private static boolean saveMove(int[][] gameBoard, int pNo, int r){
        int square = 1; // index of the checked element.
        final int cross = character('X');
        final int zero = character('O');
        for (int row = 0; row < gameBoard.length; ++row){
            for (int col = 0; col <gameBoard[row].length; ++col){
                if (square == r){ //in the square player chose.
                    if (gameBoard[row][col]== cross || gameBoard[row][col]== zero){
                        return false; // choose place already has a cross or zero.
                    } else{
                        int character = (pNo == 1)? cross:zero;// place a cross or zero.
                        gameBoard[row][col] = character;
                        return true;
                        
                    }
                }
                square++; //move to next element.
            }
        }
        return false; // if execution reaches this point, saving the character was unsuccessesful and false is returned.
    }
    
    private static int checkWinner(int[][]gameBoard){
        final int threeCrosses = character('X')*character('X')*character('X');
        final int threeZeros = character('O')*character('O')*character('O');// calculates the squares of numbers presenting cross and zero.
        // check for horizontal lines, row multiplication
        
        for (int row = 0; row <gameBoard.length; ++row){
            int multi = gameBoard[row][0]*gameBoard[row][1]*gameBoard[row][2];
            if (multi == threeCrosses)// three crossess in a line.
                return 1; //player 1 won.
            if (multi == threeZeros)// three zeros in a line.
                return 2; // player 2 won.
             }
        
        // check for vertical lines, column multiplication
        for (int col = 0; col <gameBoard[0].length; ++col){
            int multi = gameBoard[0][col]* gameBoard[1][col]*gameBoard[2][col];
            if (multi == threeCrosses)// three crosses in a line.
                return 1; //player 1 won.
            if (multi == threeZeros) // three zeros in a line.
                return 2; // player 2 won
        
        }  
        // check for diagonal lines.
        int tlbr = gameBoard[0][0]*gameBoard[1][1]*gameBoard[2][2];// from top left to bottom right.
        int bltr = gameBoard[2][0]*gameBoard[1][1]*gameBoard[0][2];// from bottom left to top right.
        
        if (tlbr == threeCrosses || bltr == threeCrosses)
            return 1; // player 1 won.
        if (tlbr == threeZeros || bltr == threeZeros)
            return 2; // player 2 won.
        return 0; // if execution reaches this point, winner is not known.
        }
    
    
    private static int character(char m){
        if (m == 'X')
            return 1;
        else if (m == 'O')
            return 2;
        else
            return 0;
    }
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String greeting = "Welcome to play Tic-tac-toe!\n" + "Type names. \n"; // Assign greetins in the variable.
        String name1, name2;
        int [][] gameBoard = {
            {0,0,0},
            {0,0,0},
            {0,0,0},
        };
        int turn; // Payer in turn.
        int moves = 9; // moves left.
        int winner = 0; // winning player, tie value 0.
        
        Scanner reader = new Scanner(System.in); // Object for reading input.
        
        System.out.println(greeting);
        
        System.out.print("Player 1 name (x): ");
        name1 = reader.nextLine();
        System.out.print("Player 2 name (o): ");
        name2 = reader.nextLine();
    
        turn = randomizeStart(); // who start first.
        
        showBoard(gameBoard); // show empty game board.
        
        do{
            System.out.print("Player "+ turn + ": ");
            int square= reader.nextInt(); // object for reading input
            boolean moveOK = saveMove(gameBoard, turn, square);
            
            if (moveOK){
            
                turn = (turn ==1) ? 2 :1 ; // change turn if move successful.
                moves--; // one less move left.
                showBoard(gameBoard);
                winner = checkWinner(gameBoard);
             } else {
                    System.out.println("Square you choose is not available!");// move invalid.
            }
           } while ((moves > 0)&& (winner == 0));
                if (winner != 0){ // Congratualate winner or report a tie.
                    System.out.print("Winner is ");
                    System.out.println((winner ==1)? (name1): (name2)+ "!");
                    System.out.println("Congratulations!");
                } else {
                    System.out.println("Game was a tie!");
                }
            
            
           
          
        
        
        
        
        }
        
    }
    

