package de.dhbw.nerdlegame.game;

import de.dhbw.nerdlegame.CalculationGenerator;
import de.dhbw.nerdlegame.calculation.Calculation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalculationGeneratorImpl implements CalculationGenerator {

    private final List<String> validCalculations = new ArrayList<>();

    public CalculationGeneratorImpl() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final URL url = classLoader.getResource("valid_calculations.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(url.getPath()))) {
            String line;
            while((line = br.readLine()) != null) {
                validCalculations.add(line);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Calculation nextCalculation() {
        Random random = new Random();
        final String calculation = validCalculations.get(random.nextInt(validCalculations.size()));
        return new Calculation(calculation);
    }

}
