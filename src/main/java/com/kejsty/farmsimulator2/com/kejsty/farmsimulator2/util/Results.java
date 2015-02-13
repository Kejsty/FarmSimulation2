package com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.util;

/**
 * Created by Kejsty on 13/02/15.
 */
public class Results {

    private int numberOfIteration;
    private int numberOfSheep;
    private int numberOfWolfs;
    private int numberOfGrass;
    private long time;

    public Results(int numberOfIteration, int numberOfSheep,int numberOfWolfs, int numberOfGrass, long time) {
        this.numberOfIteration = numberOfIteration;
        this.numberOfSheep = numberOfSheep;
        this.numberOfWolfs = numberOfWolfs;
        this.numberOfGrass = numberOfGrass;
        this.time = time;
    }

    public int getNumberOfIteration() {
        return numberOfIteration;
    }

    public int getNumberOfSheep() {
        return numberOfSheep;
    }

    public int getNumberOfWolfs() {
        return numberOfWolfs;
    }

    public int getNumberOfGrass() {
        return numberOfGrass;
    }

    public long getTime() {
        return time;
    }

    public String getRestults() {
        String resultString = "";
        resultString += "\n" + this.numberOfIteration + "," + this.numberOfSheep + "," + this.numberOfWolfs + "," + numberOfGrass + "," + time;
        return resultString;
    }

    @Override
    public String toString() {
        return this.getRestults();
    }

}
