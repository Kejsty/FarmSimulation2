package com.kejsty.farmsimulator2;

import java.util.Random;

/**
 * Created by Kejsty on 13/02/15.
 */
public class Farm {
    public static int sizeOfFarm;
    public FarmCell[][] farm;
    //statistic information
    private int diedWolfsInCycle;
    private int diedSheepsInCycle;
    private int numberOfGrassEaten;
    private int numberOfSheepsEaten;
    private int numberOfSheepsBorn;
    private int numberOfWolfsBorn;

    public Farm(int size) {
        sizeOfFarm = size;
        FarmCell[][] createdFarm = new FarmCell[sizeOfFarm][sizeOfFarm];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                FarmCell createdCell = new FarmCell(row, column);
                createdFarm[row][column] = createdCell;
            }
        }
        diedWolfsInCycle = 0;
        diedSheepsInCycle = 0;
        numberOfGrassEaten = 0;
        numberOfSheepsEaten = 0;
        numberOfSheepsBorn = 0;
        numberOfWolfsBorn = 0;
        this.farm = createdFarm;
    }

    public void eatGrass(int positionX, int positionY) {
        this.farm[positionX][positionY].setGrass(false);
    }

    public void growGrass(int positionX, int positionY) {
        this.farm[positionX][positionY].setGrass(true);
    }

    //Moving wolfs and sheeps
    public void moveAnimals() {
        for (int row = 0; row < sizeOfFarm; row++) {
            for (int column = 0; column < sizeOfFarm; column++) {
                for (int counter = 0; counter < this.farm[row][column].sheeps.size(); counter++)
                    this.farm[row][column].sheeps.get(counter).moveAnimal();
                for (int counter = 0; counter < this.farm[row][column].wolfs.size(); counter++)
                    this.farm[row][column].wolfs.get(counter).moveAnimal();
            }
        }
        //after moving removing from old LinkedList and adding to the new
        //LinkedList of the new FarmCell
        for (int row = 0; row < sizeOfFarm; row++) {
            for (int column = 0; column < sizeOfFarm; column++) {
                int thisSheeplSize = this.farm[row][column].sheeps.size();
                for (int counter = 0; counter < thisSheeplSize; counter++) {
                    this.farm[this.farm[row][column].sheeps.getFirst().getPositionX()]
                            [this.farm[row][column].sheeps.getFirst().getPositionY()].
                            sheeps.addLast(this.farm[row][column].sheeps.getFirst());
                    this.farm[row][column].sheeps.removeFirst();
                }
                int thisWolflSize = this.farm[row][column].wolfs.size();
                for (int counter = 0; counter < thisWolflSize; counter++){
                    this.farm[this.farm[row][column].wolfs.getFirst().getPositionX()]
                            [this.farm[row][column].wolfs.getFirst().getPositionY()].
                            wolfs.addLast(this.farm[row][column].wolfs.getFirst());
                    this.farm[row][column].wolfs.removeFirst();
                }
            }
        }

    }

    //Eating
    public void simulationOfEating() {
        this.diedWolfsInCycle = 0;
        this.diedSheepsInCycle = 0;
        this.numberOfGrassEaten = 0;
        this.numberOfSheepsEaten = 0;
        Random random = new Random();
        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                //wolfs are getting hungry and eating sheep
                for (int counter = 0; counter < this.farm[row][column].wolfs.size(); counter++) {
                    this.farm[row][column].wolfs.get(counter).increaseLevelOfHungry();
                    if (this.farm[row][column].wolfs.get(counter).isHungryToDeath()) {
                        this.farm[row][column].wolfs.remove(counter);
                        counter--;
                        this.increaseDiedWolfsInCycle();
                    }
                }
                for (int counter = 0; counter < this.farm[row][column].wolfs.size(); counter++) {
                    if (this.farm[row][column].sheeps.size() > 0) {
                        //System.out.println("on FarmCell " + this.farm[row][column].getPositionX() + "x" + this.farm[row][column].getPositionY());
                        int randomSheep = random.nextInt(this.farm[row][column].sheeps.size());
                        this.farm[row][column].sheeps.remove(randomSheep);
                        this.increaseDiedSheepsInCycle();
                        this.farm[row][column].wolfs.get(counter).eraseLevelOfHungry();
                        this.increaseNumberOfSheepsEaten();
                    }
                }
                //sheeps are also getting hungry
                for (int counter = 0; counter < this.farm[row][column].sheeps.size(); counter++) {
                    this.farm[row][column].sheeps.get(counter).increaseLevelOfHungry();
                    if (this.farm[row][column].sheeps.get(counter).isHungryToDeath()) {
                        this.farm[row][column].sheeps.remove(counter);
                        counter--;
                        this.increaseDiedSheepsInCycle();
                    }
                }
                //and they are eastin grass

                if (this.farm[row][column].getGrass()) {
                    if (this.farm[row][column].sheeps.size() != 0) {
                        int randomSheep = random.nextInt(this.farm[row][column].sheeps.size());
                        this.farm[row][column].sheeps.get(randomSheep).eraseLevelOfHungry();
                        this.farm[row][column].setGrass(false);
                        this.increaseNumberOfGrassEaten();
                    }
                } else {
                    this.farm[row][column].increaseGrassCounter();
                    if (this.farm[row][column].getGrassCounter() > 2){
                        this.farm[row][column].setGrass(true);
                        this.farm[row][column].deleteGrassCounter();
                    }
                }
            }
    }

    //born new animals
    public void simulationOfLife() {
        this.numberOfSheepsBorn = 0;
        this.numberOfWolfsBorn = 0;
        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                if (this.farm[row][column].sheeps.size() > 1) {
                    Animal animal = new Animal();
                    this.increaseNumberOfSheepsBorn();
                    animal.setPositionX(row);
                    animal.setPositionY(column);
                    this.farm[row][column].sheeps.addLast(animal);
                }
            }
        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                if (this.farm[row][column].wolfs.size() > 1) {
                    Animal animal = new Animal();
                    this.increaseNumberOfWolfsBorn();
                    animal.setPositionX(row);
                    animal.setPositionY(column);
                    this.farm[row][column].wolfs.addLast(animal);
                }
            }
    }

    public int getNumberOfSheeps() {
        int numberOfSheeps = 0;
        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                numberOfSheeps += this.farm[row][column].sheeps.size();
            }
        return numberOfSheeps;
    }

    public int getNumberOfWolfs() {
        int numberOfWolfs = 0;
        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                numberOfWolfs += this.farm[row][column].wolfs.size();
            }
        return numberOfWolfs;
    }

    public int getNumberOfGrass() {
        int numberOfGrass = 0;
        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                if (this.farm[row][column].getGrass()) numberOfGrass++;
            }
        return numberOfGrass;
    }
    //statistic information
    public void increaseDiedWolfsInCycle(){
        this.diedWolfsInCycle++;
    }
    public void increaseDiedSheepsInCycle(){
        this.diedSheepsInCycle++;
    }

    public void increaseNumberOfSheepsBorn(){
        this.numberOfSheepsBorn++;
    }
    public void increaseNumberOfWolfsBorn(){
        this.numberOfWolfsBorn++;
    }
    public void increaseNumberOfGrassEaten(){
        this.numberOfGrassEaten++;
    }
    public void increaseNumberOfSheepsEaten(){
        this.numberOfSheepsEaten++;
    }

    public int getDiedWolfsInCycle(){
        return this.diedWolfsInCycle;
    }
    public int getDiedSheepsInCycle(){
        return   this.diedSheepsInCycle;
    }

    public int getNumberOfSheepsBorn(){
        return this.numberOfSheepsBorn;
    }
    public int getNumberOfWolfsBorn(){
        return  this.numberOfWolfsBorn;
    }
    public int getNumberOfGrassEaten(){
        return this.numberOfGrassEaten;
    }
    public int getNumberOfSheepsEaten(){
        return this.numberOfSheepsEaten;
    }
}
