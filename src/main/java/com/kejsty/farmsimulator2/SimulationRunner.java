package com.kejsty.farmsimulator2;

import com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.domain.Animal;
import com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.domain.Farm;
import com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.util.ReportMaker;
import com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.util.Results;
import com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.util.Timer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kejsty on 13/02/15.
 */
public class SimulationRunner {

    private List<Results> allResults;
    private final Farm farm;

    SimulationRunner(int sizeOfFarm, int numberOfSheep, int numberOfWolfs) {
        this.allResults = new ArrayList<Results>();
        farm = new Farm(sizeOfFarm);

        for (int i = 0; i < numberOfSheep; i++) {
            Animal sheep = new Animal();
            farm.farm[sheep.getPositionX()][sheep.getPositionY()].sheeps.add(sheep);
        }
        for (int i = 0; i < numberOfWolfs; i++) {
            Animal wolf = new Animal();
            farm.farm[wolf.getPositionX()][wolf.getPositionY()].wolfs.add(wolf);
        }
    }

    public void runSimulation(int iterations) {
        System.out.println("Beginning: /n You have Farm " + farm.sizeOfFarm + "x" + farm.sizeOfFarm + " big");
        System.out.println("At the Farm you have " + farm.getNumberOfSheeps() + " sheeps, " + farm.getNumberOfSheeps() + " wolfs and " + farm.getNumberOfGrass() + " cells of Grass");
        System.out.println("After setting the animals on farm u have " + farm.getNumberOfSheeps() + " sheeps, " + farm.getNumberOfWolfs() + " wolfs and " + farm.getNumberOfGrass() + " cells of Grass");

        Timer timer = new Timer();
        for (int repeating = 0; repeating < iterations; repeating++) {
            timer.startTimer();
            farm.simulationOfEating();
            farm.simulationOfLife();
            farm.moveAnimals();
            timer.stopTimer();
            this.allResults.add(new Results(repeating, farm.getNumberOfSheeps(), farm.getNumberOfWolfs(), farm.getNumberOfGrass(), timer.getTotalTime()));
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
        ReportMaker.chart(new File("OutputFile.png"), allResults, "Simulation over " + iterations + " iterations");
    }

    public List<Results> getAllResults(){
        return this.allResults;
    }
}
