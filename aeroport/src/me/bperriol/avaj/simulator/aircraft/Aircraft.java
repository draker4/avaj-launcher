package me.bperriol.avaj.simulator.aircraft;

import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.flyable.Flyable;
import me.bperriol.avaj.simulator.tower.WeatherTower;
import me.bperriol.avaj.simulator.writeInFile.WriteInFile;

public class Aircraft extends Flyable{
	
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	protected String type = "Default";

	protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		id = p_id;
		name = p_name;
		coordinates = p_coordinates;
	}

	@Override
	public void updateConditions() throws CustomException{}

	@Override
	public void registerTower(WeatherTower p_tower) throws CustomException{
		this.weatherTower = p_tower;
		p_tower.register(this);
		WriteInFile.getInstance().write("Tower says: " + type + "#" + name + "(" + id + ") registered to weather tower.");
	}

	protected boolean earth() throws CustomException {
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister(this);
			WriteInFile.getInstance().write(type + "#" + name + "(" + id + ") landing.");
			return false;
		}
		return true;
	}
}
