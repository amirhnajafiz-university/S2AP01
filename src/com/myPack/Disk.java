package com.myPack;

import java.util.Objects;

/**
 * This is the Disk class where we keep a single
 * disk information like x value and y value in the
 * board and the type of the disk which can be
 * normal 1st or 2nd player disk or a hint disk.
 *
 */
public class Disk {
    //The kind of the disk 1 = 1st player / 2 = 2nd player / 0 = Hint disk
    private int diskType;
    //The x coordinate of the disk
    private int xRow;
    //The y coordinate of the disk
    private int yCol;

    /**
     * The main constructor of the class.
     *
     * @param diskType the type of the disk
     * @param xRow the x coordinate
     * @param yCol the y coordinate
     */
    public Disk(int diskType, int xRow, int yCol){
        this.diskType = diskType;
        this.xRow = xRow;
        this.yCol = yCol;
    }

    /**
     * To get the x coordinate.
     *
     * @return the x value
     */
    public int getxRow() {
        return xRow;
    }

    /**
     * To get the y coordinate.
     *
     * @return the y value
     */
    public int getyCol() {
        return yCol;
    }

    /**
     * To get the type of the disk.
     *
     * @return the type 0 or 1 or 2
     */
    public int getDiskType() {
        return diskType;
    }


    /**
     * To set the type of the disk.
     *
     * @param diskType 0 = hint disk / 1 = 1st player / 2 = 2nd player
     */
    public void setDiskType(int diskType) {
        this.diskType = diskType;
    }

    /**
     * This override of equals will check the x and y coordinates
     * for a disk and of the values where the same then we have this
     * disk in that place of map.
     *
     * @param o the Compare disk
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disk disk = (Disk) o;
        return xRow == disk.xRow &&
                yCol == disk.yCol;
    }

    /**
     * An override of hashcode.
     *
     * @return the key in the hash table
     */
    @Override
    public int hashCode() {
        return Objects.hash(xRow, yCol);
    }
}
