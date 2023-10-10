package me.bperriol.avaj.simulator.flyable;

import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.tower.WeatherTower;

public abstract class Flyable {
	
	protected WeatherTower weatherTower;

	public abstract void updateConditions() throws CustomException;

	public void registerTower(WeatherTower p_tower) throws CustomException {
		this.weatherTower = p_tower;
		p_tower.register(this);
	}
}
