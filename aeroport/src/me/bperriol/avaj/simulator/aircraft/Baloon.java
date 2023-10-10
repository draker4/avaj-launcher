package me.bperriol.avaj.simulator.aircraft;

import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.weather.WeatherProvider;
import me.bperriol.avaj.simulator.writeInFile.WriteInFile;

public class Baloon extends Aircraft{

	public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		this.type = "Baloon";
	}

	@Override
	public void updateConditions() throws CustomException{

		String weather = WeatherProvider.getInstance().getCurrentWeather(coordinates);

		switch (weather) {
			case "SUN":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude() + 2,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() + 4 > 100 ? 100 : this.coordinates.getHeight() + 4
				);
				WriteInFile.getInstance().write(
					type + "#" + name + "(" + id + "): Let's enjoy the good weather and take some pics."
				);
				break;
			case "RAIN":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 5
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "):  Damn you rain! You messed up my baloon."
					);
				break;
			case "FOG":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 3
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): I cannot see anything."
					);
				break;
			case "SNOW":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 15
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): It's snowing. We're gonna crash."
					);
				break;
		}
	}
}
