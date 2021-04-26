package com.myPack;

import java.util.Scanner;

/**
 * this class is the program main execute part.
 * where we creat a board based on single or multiply game.
 * And we use out methods to play the game.
 *
 */
public class Othello {
    //We create an instance of Board
    private Board board;

    /**
     * the main constructor of the class.
     *
     * @param n the number of players which we chose
     * @param l the level of the single player computer
     */
    public Othello(int n, int l, int graphic){
        board = new Board(n, l, graphic);
    }

    /**
     * this is the main method of this class.
     * which has a loop and will let the user play the game.
     *
     */
    public void playGame(){
        //An instance of the scanner class
        Scanner scanner = new Scanner(System.in);
        //Getting the user input
        String input;
        /*
            first we build the board and let the first player plays then we go inside the loop
         */
        board.firstBuild();
        board.findHintHouses();
        board.printOutput();
        //Showing the menu
        System.out.println("Player 1 is White and Player 2 is Black.");
        System.out.print("Player" + board.getPlayerTurn() + " Input The Place > ");
        //Getting the input
        input = scanner.nextLine();
        //Put the disk
        board.changeInput(input);
        //Showing the board again
        board.printOutput();
        //This is the main loop of the program
        while(true){
            //First we check if the game is over or not
            if(board.checkEndGame() == true) {
                board.printTheScores();
                return;
            }
            //The we check the pass or not
            board.checkGame();
            board.findHintHouses();
            board.printOutput();
            //This part is for single player mood
            if(board.getNumberOfPlayers() == 1 && board.getPlayerTurn() == 2) {
                board.makeInput();
            } else {
                //Getting the user input
                System.out.print("Player" + board.getPlayerTurn() + " Input The Place > ");
                input = scanner.nextLine();
                //Put the disk inside the board
                board.changeInput(input);
            }
            //Showing the board
            board.printOutput();
        }
    }
}
