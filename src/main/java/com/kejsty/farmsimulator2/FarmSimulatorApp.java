package com.kejsty.farmsimulator2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FarmSimulatorApp {

    public static void main(String[] args) {

        RunFarmSimulator runFarmSimulation = new RunFarmSimulator(150 , 200, 670, 1000);
        generateCsvFile("/Users/Kejsty/IdeaProjects/FarmSimulator/test.csv",runFarmSimulation.getAllResults());
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
