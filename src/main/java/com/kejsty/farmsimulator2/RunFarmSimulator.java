package com.kejsty.farmsimulator2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kejsty on 13/02/15.
 */
public class RunFarmSimulator {
    private List<Results> allResults = new ArrayList<Results>();

    RunFarmSimulator(int numberOfRepeatings, int sizeOfFarm, int numberOfSheeps, int numberOfWolfs) {
        this.allResults = new ArrayList<Results>();
        Farm farm = new Farm(sizeOfFarm);

        for (int i = 0; i < numberOfSheeps; i++) {
            Animal sheep = new Animal();
            farm.farm[sheep.getPositionX()][sheep.getPositionY()].sheeps.add(sheep);
        }
        for (int i = 0; i < numberOfWolfs; i++) {
            Animal wolf = new Animal();
            farm.farm[wolf.getPositionX()][wolf.getPositionY()].wolfs.add(wolf);
        }
        System.out.println("Beginning: /n You have Farm " + sizeOfFarm + "x" + sizeOfFarm + " big");
        System.out.println("At the Farm you have " + numberOfSheeps + " sheeps, " + numberOfWolfs + " wolfs and " + farm.getNumberOfGrass() + " cells of Grass");
        System.out.println("After setting the animals on farm u have " + farm.getNumberOfSheeps() + " sheeps, " + farm.getNumberOfWolfs() + " wolfs and " + farm.getNumberOfGrass() + " cells of Grass");
        for (int repeating = 0; repeating < numberOfRepeatings; repeating++) {
            farm.simulationOfEating();
            farm.simulationOfLife();
            farm.moveAnimals();
            Results result = new Results();
            this.allResults.add(result.addResults(repeating, farm.getNumberOfSheeps(), farm.getNumberOfWolfs(), farm.getNumberOfGrass()));
        }

          /*  System.out.println("After cycle nr." + repeating + ":");
            System.out.println("You have " + farm.getNumberOfSheeps() + " sheeps, " + farm.getNumberOfWolfs() + " wolfs and " + farm.getNumberOfGrass() + " cells of Grass");
            System.out.println("Statistic:  Eaten Grass: " + farm.getNumberOfGrassEaten() + " Eaten Sheeps " + farm.getNumberOfSheepsEaten()
            + " \n Dead Sheeps: " + farm.getDiedSheepsInCycle() + " Dead Wolfs: " + farm.getDiedWolfsInCycle()
            + " \n new borned Sheeps: " + farm.getNumberOfSheepsBorn() + " new borned Wolfs " + farm.getNumberOfWolfsBorn());
            System.out.println("natural growth increment of Sheeps " + ((float )farm.getNumberOfSheepsBorn()*100/(float )farm.getNumberOfSheeps())+ "%");
            System.out.println("natural growth increment of Wolfs " + ((float )farm.getNumberOfWolfsBorn()*100/(float )farm.getNumberOfWolfs()) + "%");
            System.out.println();
            */

        System.out.println(this.allResults.toString());
    }

    public List<Results> getAllResults(){
        return this.allResults;
    }
}
