package com.kejsty.farmsimulator2;

import java.util.Random;

/**
 * Created by Kejsty on 13/02/15.
 */
public class Animal {
    private int positionX;
    private int positionY;
    private int levelOfHungry;

    public Animal() {
        levelOfHungry = 0;
        Random random = new Random();
        positionX = random.nextInt(Farm.sizeOfFarm);
        positionY = random.nextInt(Farm.sizeOfFarm);
    }

    public Animal moveAnimal() {
        Random random = new Random();;
        this.positionX += random.nextInt(3) - 1;
        if (this.positionX < 0) {
            this.positionX++;
        }
        if (this.positionX >= Farm.sizeOfFarm) {
            this.positionX--;
        }
        this.positionY += random.nextInt(3) - 1;
        if (this.positionY < 0) {
            this.positionY++;
        }
        if (this.positionY >= Farm.sizeOfFarm) {
            this.positionY--;
        }
        return this;
    }

    public Animal increaseLevelOfHungry() {
        this.levelOfHungry++;
        return this;
    }

    public Animal eraseLevelOfHungry() {
        this.levelOfHungry = 0;
        return this;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isHungryToDeath() {
        if (this.levelOfHungry < 10) return false;
        else return true;
    }
}
