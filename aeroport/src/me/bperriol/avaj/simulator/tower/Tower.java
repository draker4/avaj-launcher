package me.bperriol.avaj.simulator.tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.bperriol.avaj.simulator.exceptions.CustomException;
import me.bperriol.avaj.simulator.flyable.Flyable;

public class Tower {
	
	private List<Flyable> observers = new ArrayList<>();
	private List<Flyable> toRemove = new ArrayList<>();

	public void register(Flyable p_flyable) {
		if (p_flyable != null)
			observers.add(p_flyable);
	}

	public void unregister(Flyable p_flyable) {
		toRemove.add(p_flyable);
	}

	protected void conditionChanged() throws CustomException{
		Iterator<Flyable> it = observers.iterator();

		while (it.hasNext()) {
			Flyable test = it.next();
			test.updateConditions();
		}

		observers.removeAll(toRemove);
		toRemove.clear();
	}
}
