package me.bperriol.avaj.simulator.aircraft;

import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.weather.WeatherProvider;
import me.bperriol.avaj.simulator.writeInFile.WriteInFile;

public class JetPlane extends Aircraft{

	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
		this.type = "JetPlane";
	}

	@Override
	public void updateConditions() throws CustomException{
		String weather = WeatherProvider.getInstance().getCurrentWeather(coordinates);

		switch (weather) {
			case "SUN":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude() + 10,
					this.coordinates.getHeight() + 2 > 100 ? 100 : this.coordinates.getHeight() + 2
				);
				WriteInFile.getInstance().write(
					type + "#" + name + "(" + id + "): Sunny days are the best."
				);
				break;
			case "RAIN":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude() + 5,
					this.coordinates.getHeight()
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): It's raining. Better watch out for lightings."
					);
				break;
			case "FOG":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude() + 1,
					this.coordinates.getHeight()
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): Where are we?"
					);
				break;
			case "SNOW":
				this.coordinates = new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 7
				);
				if (this.earth())
					WriteInFile.getInstance().write(
						type + "#" + name + "(" + id + "): OMG! Winter is coming!"
					);
				break;
		}
	}
}
