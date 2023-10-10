package me.bperriol.avaj.simulator.tower;

import me.bperriol.avaj.simulator.aircraft.Coordinates;
import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower {
	public String getWeather(Coordinates p_coordinates) {
		return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
	}

	public void changeWeather() throws CustomException{
		this.conditionChanged();
	}
}
