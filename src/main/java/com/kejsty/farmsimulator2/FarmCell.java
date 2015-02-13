package com.kejsty.farmsimulator2;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Kejsty on 13/02/15.
 */
public class FarmCell {
    private boolean grass;
    private int grassCounter;
    private int positionX;
    private int positionY;
    public LinkedList<Animal> sheeps;
    public LinkedList<Animal> wolfs;

    public FarmCell() {
        grass = getRandomBoolean();
        sheeps = new LinkedList<Animal>();
        wolfs = new LinkedList<Animal>();
        positionX = 0;
        positionY = 0;

    }

    public FarmCell(int positionX, int positionY) {
        grass = getRandomBoolean();
        this.positionX = positionX;
        this.positionY = positionY;
        sheeps = new LinkedList<Animal>();
        wolfs = new LinkedList<Animal>();
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setGrass(boolean isGrass) {
        this.grass = isGrass;
    }

    public int getPositionX(){
        return this.positionX;
    }
    public int getPositionY(){
        return this.positionY;
    }
    public int getGrassCounter(){
        return this.grassCounter;
    }
    public void increaseGrassCounter(){
        this.grassCounter++;
    }
    public void deleteGrassCounter(){
        this.grassCounter = 0;
    }

    public boolean getGrass() {
        return this.grass;
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public void moveAnimals() {
        for (int counter = 0; counter < sheeps.size(); counter++) {
            sheeps.get(counter).moveAnimal();

        }
        for (int counter = 0; counter < wolfs.size(); counter++) {
            wolfs.get(counter).moveAnimal();
        }
    }
}
