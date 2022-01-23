package entities.strategies;

import interfaces.RentalStrategyInterface;

public class RentalFactory {
	public static RentalStrategyInterface getRentalStrategy(int time) {
		if (time < 0) return null;
		if (time > 0 && time < 1440) {
			return new NormalRentalStrategy();
		} else {
			return null;
		}
	}
}
