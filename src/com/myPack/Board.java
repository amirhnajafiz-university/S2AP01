package com.myPack;

import java.util.ArrayList;
import java.util.Random;

/**
 * this is the important class of the program which controls
 * every disk in the map and players status and checks the
 * game in every turn and also changes the turn.
 *
 */
public class Board {
    //This is where we keep the main board status in a 2D array
    private int[][] board;
    //The list of hint disks
    /*
        this disks will locate the places where the player is able to
        put a disk.
        this array list will become empty each turn and will locate
        the places in the board
     */
    private ArrayList<HintDisk> hintDisks;
    //The list of all disks which we keep
    /*
        this keeping is important cause of the
        locating the hint disks and to change
        the disks in each placing
     */
    private ArrayList<Disk> inBoardDisks;
    //The players list
    private ArrayList<Player> players;
    //This is for knowing the turn ( Even = 1st player / Odd = 2st player )
    private int playerTurn;
    //This is for single or multiPlayer
    private int numberOfPlayers;
    //This is for the difficulty level of the single player
    private int level;
    //This is for printing class to show different froms base of what we run the program in
    private int graphicType;

    /**
     * the main constructor of the class
     * for creating each fields value.
     *
     */
    public Board(int numberOfPlayers, int level, int graphicType){
        this.numberOfPlayers = numberOfPlayers;
        this.level = level;
        this.graphicType = graphicType;
        board = new int[8][8];
        hintDisks = new ArrayList<>();
        inBoardDisks = new ArrayList<>();
        players = new ArrayList<>();
        players.add(new Player(1));
        if(numberOfPlayers == 2)
            players.add(new Player(2));
        playerTurn = 1;
    }

    /**
     * this is a getter method for getting the turn.
     *
     * @return the turn of the round
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * this is for getting the number of players and it is important
     * for single player mod.
     *
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    //From here we will have the methods of the game
    //--------------------------------------------------------------------------------------------

    /**
     * this method is for building the first plain board map
     * with the status of -1 for each house ( Means it is empty )
     * and the four middle house are full with black and white disks.
     *
     */
    public void firstBuild(){
        //A nested loop for checking each board house
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                //This will check three status
                if((x == 3 && y == 3) || (x == 4 && y == 4)){
                    //We put a white disk here
                    Disk tempDisk = new Disk(2, x, y);
                    inBoardDisks.add(tempDisk);
                    board[y][x] = 2;
                } else if((x == 4 && y == 3) || (x == 3 && y == 4)){
                    //We put a black disk here
                    Disk tempDisk = new Disk(1, x, y);
                    inBoardDisks.add(tempDisk);
                    board[y][x] = 1;
                } else {
                    //This house is empty
                    board[y][x] = -1;
                }
            }
        }
    }

    /**
     * this method will check all the same type disk of the
     * player turn disks.
     * And will locate the houses which we are able to put new Disks.
     *
     */
    public void findHintHouses(){
        //First we empty the last hint houses
        for(HintDisk i : hintDisks){
            board[i.getyCol()][i.getxRow()] = -1;
        }
        //Then we empty the list
        hintDisks.clear();

        for(Disk i : inBoardDisks){
            if(i.getDiskType() == playerTurn){
                for(int j = 1; j < 9; j++){
                    makeHintHouse(j, i.getDiskType(), i.getxRow(), i.getyCol(), 0, 0);
                }
            }
        }
    }

    /**
     * this method will get a special disk and will check all the 8 directions
     * and will locate the house which we are able to put our discs based on out turn.
     *
     * @param line on of 8 directions
     * @param diskType the type of the disk based on turn
     * @param xRow the x coordinate of the special disk
     * @param yCol the y coordinate of the special disk
     * @param valid this variable is used to make sure if the hint house is valid  ( don't remove it )
     */
    private void makeHintHouse(int line, int diskType, int xRow, int yCol, int valid, int count){
        int oppDiskType = 0;
        if(diskType == 1)
            oppDiskType = 2;
        if(diskType == 2)
            oppDiskType = 1;
        switch (line){
            case 1:
                //We go up
                yCol -= 1;
                break;
            case 2:
                //We go down
                yCol += 1;
                break;
            case 3:
                //We go right
                xRow += 1;
                break;
            case 4:
                //We go left
                xRow -= 1;
                break;
            case 5:
                //We go up-right
                yCol -= 1;
                xRow += 1;
                break;
            case 6:
                //We go down-left
                yCol += 1;
                xRow -= 1;
                break;
            case 7:
                //We go down-right
                yCol += 1;
                xRow += 1;
                break;
            case 8:
                //We go up-left
                yCol -= 1;
                xRow -= 1;
                break;
        }
        if(yCol < 0 || yCol > 7 || xRow < 0 || xRow > 7)
            return;
        if(board[yCol][xRow] == oppDiskType) {
            count++;
            makeHintHouse(line, diskType, xRow, yCol, 1, count);
        }
        else if(board[yCol][xRow] == -1 && valid == 1) {
            board[yCol][xRow] = 0;
            hintDisks.add(new HintDisk(0, xRow, yCol, count));
            makeHintHouse(line, diskType, xRow, yCol, 0, 0);
        }
    }

    /**
     * this method will get the input from the user and will change it to
     * what we want and then it calls the placeADisk method for putting it
     * into the board and do what is necessary.
     *
     * @param input the user input base on the map
     */
    public void changeInput(String input){
        int xRow;
        int yCol;
        switch (input.charAt(0)){
            case '1':
                yCol = 0;
                break;
            case '2':
                yCol = 1;
                break;
            case '3':
                yCol = 2;
                break;
            case '4':
                yCol = 3;
                break;
            case '5':
                yCol = 4;
                break;
            case '6':
                yCol = 5;
                break;
            case '7':
                yCol = 6;
                break;
            case '8':
                yCol = 7;
                break;
            default:
                System.out.println("Incorrect Input. Please Try Again.");
                return;
        }
        switch (input.charAt(1)){
            case 'A':
                xRow = 0;
                break;
            case 'B':
                xRow = 1;
                break;
            case 'C':
                xRow = 2;
                break;
            case 'D':
                xRow = 3;
                break;
            case 'E':
                xRow = 4;
                break;
            case 'F':
                xRow = 5;
                break;
            case 'G':
                xRow = 6;
                break;
            case 'H':
                xRow = 7;
                break;
            default:
                System.out.println("Incorrect Input. Please Try Again.");
                return;
        }
        placeADisk(xRow, yCol);
    }

    /**
     * this method will get the x and y coordinates after using
     * changeInput method and will check for the place of disk
     * and will place it or will give us and error
     * and then will change all of the disks that
     * needed a change by placing this disk.
     *
     * @param xRow the x coordinate
     * @param yCol the y coordinate
     */
    public void placeADisk(int xRow, int yCol){
        //First we build an instance of the disk
        Disk tempDisk = new Disk(playerTurn, xRow, yCol);
        //This is for checking if the disk was in the hint disks
        int validPlace = 0;
        for(HintDisk i : hintDisks){
            if(i.getxRow() == xRow && i.getyCol() == yCol)
                validPlace++;
        }
        if(validPlace == 0){
            System.out.println("Wrong Place For Disk. Try Again.");
            return;
        }
        //This is for clearing the hint disks off the board
        for(Disk i : hintDisks){
            board[i.getyCol()][i.getxRow()] = -1;
        }
        hintDisks.clear();
        //Then we put the disk in the map
        board[yCol][xRow] = tempDisk.getDiskType();
        //Then we will change the types of the disks between
        changeDisks(tempDisk);
        //And add it to the disk list
        inBoardDisks.add(tempDisk);
        //Then we change the turn
        if(playerTurn == 1)
            playerTurn = 2;
        else
            playerTurn = 1;
    }

    /**
     * this method will get a disk from the placeDisk method that had
     * a valid place.
     * Then we are going to make the changes in the board that this
     * disk made.
     * We a have a constant place then we check the others based on
     * that place.
     * This method will use the searchLine method too.
     *
     * @param tempDisk the disk we put
     */
    public void changeDisks(Disk tempDisk){
        //We locate the first place of the disk
        int xRow = tempDisk.getxRow();
        int yCol = tempDisk.getyCol();
        for(int i = 1; i < 9; i++){
            if(checkLine(i, tempDisk))
                searchLine(i, xRow, yCol, tempDisk);
        }
    }

    /**
     * this method will check if there is any line between the input disk and any other disk
     * in the map in the direction which we call it line.
     *
     * @param line each direction from our 8 directions
     * @param tempDisk the disk we put
     * @return true or false
     */
    public boolean checkLine(int line, Disk tempDisk){
        //First we check if this disk is making any lines ( The definition of the line is in the Question )
        for(Disk i : inBoardDisks) {
            if (i.getDiskType() == tempDisk.getDiskType()) {
                int valid = 0;
                switch (line) {
                    case 1:
                        //There is a disk in the up direction
                        if (i.getxRow() == tempDisk.getxRow() && i.getyCol() < tempDisk.getyCol())
                            valid = 1;
                        break;
                    case 2:
                        //There is a disk in the down direction
                        if (i.getxRow() == tempDisk.getxRow() && i.getyCol() > tempDisk.getyCol())
                            valid = 2;
                        break;
                    case 3:
                        //There is a disk in the right direction
                        if (i.getxRow() > tempDisk.getxRow() && i.getyCol() == tempDisk.getyCol())
                            valid = 3;
                        break;
                    case 4:
                        //There is a disk in the left direction
                        if (i.getxRow() < tempDisk.getxRow() && i.getyCol() == tempDisk.getyCol())
                            valid = 4;
                        break;
                    case 5:
                        //There is a disk in the up-right direction
                        if ( ( i.getxRow() - tempDisk.getxRow() == (-1) * (i.getyCol() - tempDisk.getyCol()) ) )
                            if ((i.getxRow() > tempDisk.getxRow()) && (i.getyCol() < tempDisk.getyCol()))
                                valid = 5;
                        break;
                    case 6:
                        //There is a disk in the down-left direction
                        if ( ( i.getxRow() - tempDisk.getxRow() == (-1) * (i.getyCol() - tempDisk.getyCol()) ) )
                            if ((i.getxRow() < tempDisk.getxRow()) && (i.getyCol() > tempDisk.getyCol()))
                                valid = 6;
                        break;
                    case 7:
                        //There is a disk in the down-right direction
                        if ( ( i.getxRow() - tempDisk.getxRow() == (i.getyCol() - tempDisk.getyCol()) ) )
                            if ((i.getxRow() > tempDisk.getxRow()) && (i.getyCol() > tempDisk.getyCol()))
                                valid = 7;
                        break;
                    case 8:
                        //There is a disk in the up-left direction
                        if ( ( i.getxRow() - tempDisk.getxRow() ==  (i.getyCol() - tempDisk.getyCol()) ) )
                            if ((i.getxRow() < tempDisk.getxRow()) && (i.getyCol() < tempDisk.getyCol()))
                                valid = 8;
                        break;
                }
                //Then we check the switch
                if (valid == line)
                    return true;
            }
        }
        //This means there is no line
        return false;
    }

    /**
     * This is a recursive function.
     * We have an algorithm to change the disks :
     * this will pick each 8 directions around the disk that we say it is a line
     * and will change the disks in this line till we reach
     * A same type disk.
     * Cause we check the line before we are sure that we are getting to a disk in this direction.
     *
     * @param line one direction from the 8 ( up / down / left / right / up-right / up-left / down-right / down-left )
     * @param xRow the x coordinate where we at
     * @param yCol the y coordinate where we at
     * @param tempDisk the disk we put
     */
    public void searchLine(int line, int xRow, int yCol, Disk tempDisk){
        //So this switch case will change our place based on the type of the line
        switch (line){
            case 1:
                //We go up
               yCol -= 1;
               break;
            case 2:
                //We go down
                yCol += 1;
                break;
            case 3:
                //We go right
                xRow += 1;
                break;
            case 4:
                //We go left
                xRow -= 1;
                break;
            case 5:
                //We go up-right
                yCol -= 1;
                xRow += 1;
                break;
            case 6:
                //We go down-left
                yCol += 1;
                xRow -= 1;
                break;
            case 7:
                //We go down-right
                yCol += 1;
                xRow += 1;
                break;
            case 8:
                //We go up-left
                yCol -= 1;
                xRow -= 1;
                break;
        }
        //First we check the boards
        if(yCol < 0 || yCol > 7 || xRow < 0 || xRow > 7)
            return;
        //We pass the empty houses
        if(board[yCol][xRow] == -1) {
            searchLine(line, xRow, yCol, tempDisk);
            return;
        }
        //Then we check of the house which we are in right now is not empty or same type
        if(board[yCol][xRow] == tempDisk.getDiskType())
            return;
        /*
            if we pass the two situations above that means
            the house where we are right now is a different type and we should change
            this house and look for the next house
         */
        //Then we change that house
        board[yCol][xRow] = tempDisk.getDiskType();
        for(Disk i : inBoardDisks){
            if(i.getxRow() == xRow && i.getyCol() == yCol)
                i.setDiskType(tempDisk.getDiskType());
        }
        //Then we go to the next house
        searchLine(line, xRow, yCol, tempDisk);
    }

    /**
     * this method will check for the Pass and the end game
     */
    public void checkGame(){
        //First we build the hint houses
        findHintHouses();
        //Then check the pass or not item
        if(hintDisks.size() == 0){
            System.out.println("Pass");
            playerTurn = (playerTurn == 1) ? 2 : 1;
        }
    }

    /**
     * this method is for checking if the game is finished or not
     * @return true or false
     */
    public boolean checkEndGame(){
        //First we check if the whole houses are full with disks
        int valid = 0;
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if(board[y][x] == -1)
                    valid++;
            }
        }
        //Then we check if there were some houses were free but there was no valid house to put disk
        findHintHouses();
        if(hintDisks.size() == 0){
            playerTurn = (playerTurn == 1) ? 2 : 1;
            findHintHouses();
            if(hintDisks.size() == 0) {
                playerTurn = (playerTurn == 1) ? 2 : 1;
                return true;
            }
            playerTurn = (playerTurn == 1) ? 2 : 1;
        }
        return valid == 0;
    }

    /**
     * in this method we will use our print class and we will
     * print the board in cmd
     */
    public void printOutput(){
        PrintGame printGame = new PrintGame(board, inBoardDisks, graphicType);
        printGame.printThePage();
    }

    public void printTheScores(){
        int player1 = 0;
        int player2 = 0;
        for(Disk i : inBoardDisks) {
            if (i.getDiskType() == 1) {
                player1++;
            } else {
                if (i.getDiskType() == 2)
                    player2++;
            }
        }
        if ((player1 > player2)) {
            players.get(0).addRound();
        } else {
            players.get(1).addRound();
        }
        System.out.println("So Far Player 1" + players.get(0).getRoundsWon() + " - Player 2 " + players.get(0).getRoundsWon());
    }

    /**
     * this is the mode when
     * we play against computer
     */
    /*
        this method will chose a random disk from the
        hint disks and then will replace it automatic
     */
    public void makeInput(){
        if(level == 1) {
            findHintHouses();
            Random random = new Random();
            int index = random.nextInt(hintDisks.size());
            showOutput(hintDisks.get(index).getxRow(), hintDisks.get(index).getyCol());
            placeADisk(hintDisks.get(index).getxRow(), hintDisks.get(index).getyCol());
        } else {
            findHintHouses();
            int index = foundBestHouse();
            showOutput(hintDisks.get(index).getxRow(), hintDisks.get(index).getyCol());
            placeADisk(hintDisks.get(index).getxRow(), hintDisks.get(index).getyCol());
        }
    }

    /**
     * This method will chose the best house for computer to
     * put the disk.
     *
     * @return the index of hint disk in the list
     */
    public int foundBestHouse(){
        int count = 0;
        int maximum = 0;
        int index = 0;
        for(HintDisk i : hintDisks){
            if(i.getScore() > maximum){
                index = count;
                maximum = i.getScore();
            }
            count++;
        }
        return index;
    }

    /**
     * This is for showing what has computer chose to play.
     *
     * @param xRow the x coordinate of board
     * @param yCol the y coordinate of board
     */
    public void showOutput(int xRow, int yCol){
        char row = 0;
        char col = 0;
        switch(xRow){
            case 0:
                row = 'A';
                break;
            case 1:
                row = 'B';
                break;
            case 2:
                row = 'C';
                break;
            case 3:
                row = 'D';
                break;
            case 4:
                row = 'E';
                break;
            case 5:
                row = 'F';
                break;
            case 6:
                row = 'G';
                break;
            case 7:
                row = 'H';
                break;
        }
        switch(yCol){
            case 0:
                col = '1';
                break;
            case 1:
                col = '2';
                break;
            case 2:
                col = '3';
                break;
            case 3:
                col = '4';
                break;
            case 4:
                col = '5';
                break;
            case 5:
                col = '6';
                break;
            case 6:
                col = '7';
                break;
            case 7:
                col = '8';
                break;
        }
        System.out.println("The computer choose " + col + row);
    }
}