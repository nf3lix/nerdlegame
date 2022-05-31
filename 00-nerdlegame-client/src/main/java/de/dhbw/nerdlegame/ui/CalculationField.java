package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.NerdleGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationField {

    private final JPanel panel = new JPanel();
    private final List<JButton> guessButtons = new ArrayList<>();
    private final List<CalculationRow> rows = new ArrayList<>();

    public CalculationField() {
        panel.setLayout(new GridLayout(NerdleGame.MAX_GUESSES, 1));
        for(int i = 0; i < NerdleGame.MAX_GUESSES; i++) {
            final CalculationRow row = new CalculationRow();
            rows.add(row);
            guessButtons.add(row.getGuessButton());
            row.disable();
            panel.add(row.getPanel());
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    JButton guessButtonAt(final int index) {
        return guessButtons.get(index);
    }

    CalculationRow getRow(final int index) {
        return rows.get(index);
    }

    void resetField() {
        rows.forEach(CalculationRow::resetRow);
    }

}
