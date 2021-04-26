package com.myPack;

import java.util.Scanner;

/**
 * Just a test for the game.
 *
 */
public class RunningTest {

    public static void main(String[] args){
        //Creating the scanner
        Scanner scanner = new Scanner(System.in);
        System.out.print("If You Are Running In CMD Please Enter 1 > ");
        int graphic = scanner.nextInt();
        //Showing the menu
        System.out.print("Enter 1 For Single Player And 2 For Multi Player > ");
        //Getting the input
        int input = scanner.nextInt();
        int level = 1;
        if(input == 1){
            System.out.print("Enter 1 For Easy Or 2 for Hard > ");
            level = scanner.nextInt();
        }
        //Creating the game
	    Othello playGames = new Othello(input, level, graphic);
        while(true) {
            System.out.print("\nEnter 1 For Play A Round Or 2 For Exit > ");
            int order = scanner.nextInt();
            if(order == 2)
                break;
            else if(order == 1)
                playGames.playGame();
            else
                System.out.println("Wrong Input");
        }
        System.out.println("Thanks For Playing.");
    }
}