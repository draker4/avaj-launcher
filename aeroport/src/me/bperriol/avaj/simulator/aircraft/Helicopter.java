package me.bperriol.avaj.simulator.aircraft;

import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.weather.WeatherProvider;
import me.bperriol.avaj.simulator.writeInFile.WriteInFile;

public class Helicopter extends Aircraft{

	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		this.type = "Helicopter";
	}

	@Override
	public void updateConditions() throws CustomException{
		String weather = WeatherProvider.getInstance().getCurrentWeather(coordinates);

		switch (weather) {
			case "SUN":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude() + 10,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() + 2 > 100 ? 100 : this.coordinates.getHeight() + 2
				);
				WriteInFile.getInstance().write(
					type + "#" + name + "(" + id + "): This is hot."
				);
				break;
			case "RAIN":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude() + 5,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight()
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): I love rain."
					);
				break;
			case "FOG":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude() + 1,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight()
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): Nice grey."
					);
				break;
			case "SNOW":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 12
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): My rotor is going to freeze!"
					);
				break;
		}
	}
}
