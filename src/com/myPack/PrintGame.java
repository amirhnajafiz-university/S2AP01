package com.myPack;

import java.util.ArrayList;

/**
 * this class will print the method in our cmd page
 * and in general it makes the output.
 *
 */
public class PrintGame {
    /*
        this method will get the array of the board
        and the array of the disks and will print the
        board and the scores for us
     */
    //We got the array of board
    private int[][] board;
    //We got the array of disks
    private ArrayList<Disk> disks;
    //If == 1 it means we are in CMD and we show the map without colors
    private int graphicType;

    /**
     * the main constructor of the class for getting two collections.
     *
     * @param board the 2D array of board
     * @param disks the array list of disks
     */
    public PrintGame(int[][] board, ArrayList<Disk> disks, int graphicType){
        this.board = board;
        this.disks = disks;
        this.graphicType = graphicType;
    }

    /**
     * this method will count the disks for each player
     * base on the type of the disks in the disks list.
     *
     */
    public void calculateScore(){
        int player1 = 0;
        int player2 = 0;
        for(Disk i : disks) {
            if (i.getDiskType() == 1) {
                player1++;
            } else {
                if (i.getDiskType() == 2)
                    player2++;
            }
        }
        System.out.println("1st Player " + player1 + " : 2nd Player " + player2);
    }

    /**
     * this method has a nested loop to go through the 2D array and will
     * print the output based on the type of the board house.
     *
     */
    public void printThePage(){
        System.out.println("     " + " A " + "   " + " B " + "   " + " C " + "   " + " D " + "   " + " E " + "   " + " F " + "   " + " G " + "   " + " H ");
        System.out.println("     " + "---" + " - " + "---" + " - " + "---" + " - " + "---" + " - " + "---" + " - " + "---" + " - " + "---" + " - " + "---");
        for(int y = 0; y < 8; y++){
            System.out.print(" " + ( y + 1 ) + " | ");
            for(int x = 0; x < 8; x++){
                switch (board[y][x]){
                    case -1:
                        if(graphicType == 1)
                            System.out.print(" * ");
                        else
                            System.out.print("\u001B[42m" + "   " + "\u001B[0m");
                        break;
                    case 0:
                        if(graphicType == 1)
                            System.out.print(" O ");
                        else
                            System.out.print("\u001B[43m" + " O " + "\u001B[0m");
                        break;
                    case 1:
                        if(graphicType == 1)
                            System.out.print(" W ");
                        else
                            System.out.print("\u001B[47m" + " W " + "\u001B[0m");
                        break;
                    case 2:
                        System.out.print(" B ");
                    default:
                        break;
                }
                System.out.print("   ");
            }
            System.out.print("\n\n");
        }
        System.out.print("\n\n");
        calculateScore();
        System.out.print("\n\n");
        System.out.println("-------------------------------");
    }
}
