package com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.domain;

import java.util.List;
import java.util.Random;

/**
 * Created by Kejsty on 13/02/15.
 */
public class Farm {

    public static final Random random = new Random();

    public static int sizeOfFarm; // shouldn't be public and even static
    public final FarmCell[][] farm; // shouldn't be public
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

        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                FarmCell currentFarmCell = this.farm[row][column];
                //wolfs are getting hungry and eating sheep
                List<Animal> wolfs = currentFarmCell.getWolfs();
                for (int counter = 0; counter < wolfs.size(); counter++) {
                    wolfs.get(counter).increaseLevelOfHungry();
                    if (wolfs.get(counter).isHungryToDeath()) {
                        wolfs.remove(counter);
                        counter--;
                        this.increaseDiedWolfsInCycle();
                    }
                }
                List<Animal> sheeps = currentFarmCell.getSheeps();
                for (Animal wolf : wolfs) {
                    if (sheeps.size() > 0) {
                        //System.out.println("on FarmCell " + this.farm[row][column].getPositionX() + "x" + this.farm[row][column].getPositionY());
                        int randomSheep = random.nextInt(sheeps.size());
                        sheeps.remove(randomSheep);
                        this.increaseDiedSheepsInCycle();
                        wolf.eraseLevelOfHungry();
                        this.increaseNumberOfSheepsEaten();
                    }
                }
                //sheeps are also getting hungry
                for (int counter = 0; counter < sheeps.size(); counter++) {
                    sheeps.get(counter).increaseLevelOfHungry();
                    if (sheeps.get(counter).isHungryToDeath()) {
                        sheeps.remove(counter);
                        counter--;
                        this.increaseDiedSheepsInCycle();
                    }
                }
                //and they are eastin grass

                if (currentFarmCell.getGrass()) {
                    if (currentFarmCell.sheeps.size() != 0) {
                        int randomSheep = random.nextInt(sheeps.size());
                        sheeps.get(randomSheep).eraseLevelOfHungry();
                        currentFarmCell.setGrass(false);
                        this.increaseNumberOfGrassEaten();
                    }
                } else {
                    currentFarmCell.increaseGrassCounter();
                    if (currentFarmCell.getGrassCounter() > 2){
                        currentFarmCell.setGrass(true);
                        currentFarmCell.deleteGrassCounter();
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
                FarmCell currentFarmCell = this.farm[row][column];
                if (currentFarmCell.sheeps.size() > 1) {
                    Animal animal = new Animal();
                    this.increaseNumberOfSheepsBorn();
                    animal.setPositionX(row);
                    animal.setPositionY(column);
                    currentFarmCell.sheeps.addLast(animal);
                }
            }
        for (int row = 0; row < sizeOfFarm; row++)
            for (int column = 0; column < sizeOfFarm; column++) {
                FarmCell currentFarmCell = this.farm[row][column];
                if (currentFarmCell.wolfs.size() > 1) {
                    Animal animal = new Animal();
                    this.increaseNumberOfWolfsBorn();
                    animal.setPositionX(row);
                    animal.setPositionY(column);
                    currentFarmCell.wolfs.addLast(animal);
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
