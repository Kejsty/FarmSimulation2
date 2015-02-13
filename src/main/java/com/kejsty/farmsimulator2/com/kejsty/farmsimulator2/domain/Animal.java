package com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.domain;

import java.util.Random;

/**
 * Created by Kejsty on 13/02/15.
 */
public class Animal {
    public static Random random = new Random();

    private int positionX;
    private int positionY;
    private int levelOfHungry;

    public Animal() {
        positionX = random.nextInt(Farm.sizeOfFarm);
        positionY = random.nextInt(Farm.sizeOfFarm);
    }

    public Animal moveAnimal() {
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
        return this.levelOfHungry >= 10;
    }
}
