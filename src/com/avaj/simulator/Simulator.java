package com.avaj.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.avaj.decoder.Decoder;
import com.avaj.flyable.AircraftFactory;
import com.avaj.flyable.Flyable;
import com.avaj.logger.Logger;
import com.avaj.simulator.exceptions.InvalidScenarioException;

public class Simulator {

  private static Integer parseInteger(String str) throws InvalidScenarioException {
    try {
      if (str.matches("^-?\\d+$")) {
        return Integer.parseInt(str);
      }
  
      Integer valueFromRainbowTable = Decoder.rainbowTableLookup(str.toLowerCase());
  
      if (valueFromRainbowTable != null) {
        return valueFromRainbowTable;
      } else {
        throw new InvalidScenarioException("Error while parsing integer value '" + str + "'.");
      }
    } catch (NumberFormatException e) {
      throw new InvalidScenarioException("Integer value out of bounds '" + str + "'.");
    }
  }

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Incorrect number of arguments.");
      return;
    }

    File scenarioFile = new File(args[0]);

    if (!scenarioFile.exists()) {
      System.out.println("Error: file '" + args[0] + "' doesn't exist or is invalid.");
      System.exit(1);
    }

    try {
      Logger.setLogFile("simulation.txt");
      BufferedReader reader = new BufferedReader(new FileReader(scenarioFile));

      ArrayList<Flyable> flyables = new ArrayList<>();

      String line = reader.readLine();
      Integer simulationsCount = parseInteger(line);

      if (simulationsCount < 0) {
        throw new InvalidScenarioException("Simulation count should be positive.");
      }

      while ((line = reader.readLine()) != null) {
        String[] scenario = line.split(" ");

        if (scenario.length != 5) {
          throw new InvalidScenarioException("Error, invalid scenario line stucture: " + line + "\n" + "Sould be: [TYPE] [NAME] [LONGITUDE] [LATITUDE] [HEIGHT]");
        }

        String type = scenario[0];
        String name = scenario[1];
        Integer longitude = parseInteger(scenario[2]);
        Integer latitude = parseInteger(scenario[3]);
        Integer height = parseInteger(scenario[4]);

        Flyable flyable = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
        flyables.add(flyable);
      }
      reader.close();

      if (flyables.size() == 0) {
        System.out.println("No aircrafts was specified in the scenario.");
        return;
      }

      WeatherTower weatherTower = new WeatherTower();
      for (Flyable flyable : flyables) {
        flyable.registerTower(weatherTower);
      }

      while (simulationsCount > 0) {
        weatherTower.changeWeather();
        simulationsCount--;
      }

      Logger.closeLogFile();
    } catch (IOException | InvalidScenarioException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }

  }

}