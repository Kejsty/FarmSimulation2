package com.kejsty.farmsimulator2;

import com.kejsty.farmsimulator2.com.kejsty.farmsimulator2.util.Results;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FarmSimulatorApp {

    public static void main(String[] args) {

        long totalTime = System.currentTimeMillis();
        SimulationRunner runFarmSimulation = new SimulationRunner(200, 670, 1000);
        runFarmSimulation.runSimulation(150);
        long totalTime2 = System.currentTimeMillis();
        System.out.println("Simulation took: " + (totalTime2 - totalTime));
        //generateCsvFile("/Users/Kejsty/IdeaProjects/FarmSimulator/test.csv",runFarmSimulation.getAllResults());
    }
    private static void generateCsvFile(String sFileName,List<Results> allResults){
        try {
            FileWriter writer = new FileWriter(sFileName);
            writer.append(allResults.toString());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
