package entities.strategies;

import interfaces.DepositStrategyInterface;

public class DepositFactory {
	public static DepositStrategyInterface getDepositStrategy(int time) {
		return new NormalDepositStrategy();
	}
}
