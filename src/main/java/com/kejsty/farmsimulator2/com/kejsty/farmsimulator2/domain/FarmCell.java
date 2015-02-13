package com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.domain;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Kejsty on 13/02/15.
 */
public class FarmCell {

    private static final Random random = new Random();

    private boolean grass;
    private int grassCounter;
    private int positionX;
    private int positionY;
    public final LinkedList<Animal> sheeps = new LinkedList<Animal>();
    public final LinkedList<Animal> wolfs = new LinkedList<Animal>();

    public FarmCell() {
        grass = random.nextBoolean();
    }

    public FarmCell(int positionX, int positionY) {
        this();
        this.positionX = positionX;
        this.positionY = positionY;
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

    public LinkedList<Animal> getSheeps() {
        return sheeps;
    }

    public LinkedList<Animal> getWolfs() {
        return wolfs;
    }


    public void moveAnimals() {
        for (Animal sheep : sheeps) {
            sheep.moveAnimal();

        }
        for (Animal wolf : wolfs) {
            wolf.moveAnimal();
        }
    }
}
