package me.bperriol.avaj.simulator.aircraft;

import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.flyable.Flyable;

public class AircraftFactory {

	private static long id = 1;

	public static Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws CustomException{
		switch (p_type) {
			case "Baloon":
				return new Baloon(id++, p_name, p_coordinates);
			case "JetPlane":
				return new JetPlane(id++, p_name, p_coordinates);
			case "Helicopter":
				return new Helicopter(id++, p_name, p_coordinates);
		}
		throw new CustomException("No " + p_type + " type exists!");
	}

	public static Flyable newAircraftData(String p_type, String p_name, int p_longitude, int p_latitude, int p_height) throws CustomException{
		Coordinates p_coordinates = new Coordinates(p_longitude, p_latitude, p_height);
		return AircraftFactory.newAircraft(p_type, p_name, p_coordinates);
	}
}
