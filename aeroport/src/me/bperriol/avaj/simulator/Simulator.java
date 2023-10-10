package me.bperriol.avaj.simulator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

import me.bperriol.avaj.simulator.aircraft.AircraftFactory;
import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.flyable.Flyable;
import me.bperriol.avaj.simulator.tower.WeatherTower;
import me.bperriol.avaj.simulator.writeInFile.WriteInFile;

public class Simulator {
    public static void main(String[] args) throws Exception {
        try {

            // check number of arguments
            if (args.length != 1) {
                System.err.println("Please enter one scenario and one only!");
                return ;
            }

            String filename = args[0];
            int nbWeatherChanged = 0;

            // open file to write logs
            WriteInFile.getInstance().create("simulation.txt");

            // create main tower pbject
            WeatherTower tower = new WeatherTower();

            // read file in stream to keep memory
            try (Stream<String> lines = Files.lines(Paths.get(filename))) {
                Iterator<String> it = lines.iterator();

                // check first line for integer
                if (it.hasNext()) {
                    String number = it.next();
                    if (!number.matches("[0-9]+"))
                        throw new CustomException("First line should just contain digits!");
                    nbWeatherChanged = Integer.parseInt(number);
                }
                else
                    throw new CustomException("File empty!");

                // check if positive integer only
                if (nbWeatherChanged < 0)
                    throw new CustomException("Enter a positive number on the first line");

                // read flyables' coordinates
                while (it.hasNext()) {
                    final String line = it.next();
                    Flyable flyable = createAircraft(line);
                    if (flyable != null)
                        flyable.registerTower(tower);
                }
            }
            catch (Exception e) {
                throw new CustomException(e.getMessage());
            }

            while (nbWeatherChanged > 0) {
                tower.changeWeather();
                nbWeatherChanged--;
            }
        }
        catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    private static Flyable createAircraft(String str) throws CustomException{
        String[] split = str.split(" ");
        if (split.length != 5)
            throw new CustomException("a flyable have wrong data!");

        if (
            !split[2].matches("[0-9]+")
            || !split[3].matches("[0-9]+")
            || !split[4].matches("[0-9]+")
        )
            throw new CustomException("Latitude, longitude or height should just contain digits!");

        int longitude = Integer.parseInt(split[2]);
        int latitude = Integer.parseInt(split[3]);
        int height = Integer.parseInt(split[4]);

        if (height < 0 || height > 100)
            throw new CustomException("Height is not between 0 and 100 for " + split[1]);

        if (latitude < 0 || longitude < 0)
            throw new CustomException("Coordinates for " + split[1] + " need to be positive");

        if (height != 0)
            return AircraftFactory.newAircraftData(split[0], split[1], longitude, latitude, height);
        else
            return null;
    }
}
