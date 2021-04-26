package com.myPack;

/**
 * This is a subclass of the disk class.
 * It is a hint disk which shows the available places to put
 * a disk.
 *
 */
public class HintDisk extends Disk {
    //This field will give the hint disk a value to use in single player
    private int score;
    /**
     * the main constructor of the class.
     *
     * @param diskType the type of the disk
     * @param xRow     the x coordinate
     * @param yCol     the y coordinate
     */
    public HintDisk(int diskType, int xRow, int yCol, int score) {
        super(diskType, xRow, yCol);
        this.score = score;
    }

    /**
     * A setter for the score.
     *
     * @param score the total value of this disk
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * A getter method to get the score of this disk.
     *
     * @return the value of this disk
     */
    public int getScore() {
        return score;
    }
}
