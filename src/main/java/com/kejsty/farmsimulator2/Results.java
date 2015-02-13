package com.kejsty.farmsimulator2;

/**
 * Created by Kejsty on 13/02/15.
 */
public class Results {
    private int numberOfIteration;
    private int numberOfSheeps;
    private int numberOfWolfs;
    private int numberOfGrass;

    public Results() {
        numberOfIteration = 0;
        numberOfSheeps = 0;
        numberOfWolfs = 0;
        numberOfGrass = 0;
    }
    public Results addResults(int numberOfIteration, int numberOfSheeps,int numberOfWolfs, int numberOfGrass){
        this.numberOfIteration = numberOfIteration;
        this.numberOfSheeps = numberOfSheeps;
        this.numberOfWolfs = numberOfWolfs;
        this.numberOfGrass = numberOfGrass;
        return this;
    }
    public String getRestults(){
        String resultString = new String();
        resultString += "\n" + this.numberOfIteration + "," + this.numberOfSheeps + "," + this.numberOfWolfs + "," + numberOfGrass;
        return resultString;
    }
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.getRestults());
        return result.toString();
    }

}
