package com.myPack;


/**
 * this class saves the player information like the player number
 * ond the number of disks the player have.
 *
 */
public class Player {
    //The number of player which is important for us in turns
    private int playerNumber;
    //This is to see how many rounds this player has won
    private int roundsWon;

    /**
     * the main constructor of the class.
     *
     * @param playerNumber 1 or 2 for 1st and 2nd player
     */
    public Player(int playerNumber){
        this.playerNumber = playerNumber;
        this.roundsWon = 0;
    }

    /**
     * A getter method to see how many rounds has a player win.
     *
     * @return the number of wins
     */
    public int getRoundsWon() {
        return roundsWon;
    }

    /**
     * This is for adding a round won.
     *
     */
    public void addRound(){
        roundsWon++;
    }
}
