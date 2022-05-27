package de.dhbw.nerdlegame;

import de.dhbw.nerdlegame.calculation.Calculation;

public class CalculationGeneratorImpl implements CalculationGenerator {

    @Override
    public Calculation nextCalculation() {
        return new Calculation("12+35=47"); // TODO: return random calculation
    }

}
