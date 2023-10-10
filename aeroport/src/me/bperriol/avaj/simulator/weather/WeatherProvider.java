package me.bperriol.avaj.simulator.weather;

import me.bperriol.avaj.simulator.aircraft.Coordinates;

public class WeatherProvider {
	
	private static final WeatherProvider INSTANCE = new WeatherProvider();
	private String weather[] = {"SUN", "FOG", "RAIN", "SNOW"};

	private WeatherProvider() {}

	public static WeatherProvider getInstance() {
		return INSTANCE;
	}

	public String getCurrentWeather(Coordinates p_coordinates) {
		int total = p_coordinates.getHeight() + p_coordinates.getLatitude() + p_coordinates.getLongitude();
		return weather[total % 4];
	}
}
