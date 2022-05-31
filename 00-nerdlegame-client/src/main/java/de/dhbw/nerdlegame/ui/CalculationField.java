package de.dhbw.nerdlegame.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalculationField {

    private final JPanel panel = new JPanel();
    private final List<JButton> guessButtons = new ArrayList<>();
    private final List<CalculationRow> rows = new ArrayList<>();

    public CalculationField() {
        panel.setLayout(new GridLayout(5, 1));
        for(int i = 0; i < 5; i++) {
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

    void enableRow(final int index) {
        this.rows.get(index);
    }

    void disableRow(final int index) {
        this.rows.get(index);
    }

}
