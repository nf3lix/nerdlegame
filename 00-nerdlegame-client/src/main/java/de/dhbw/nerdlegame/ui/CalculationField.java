package de.dhbw.nerdlegame.ui;

import javax.swing.*;
import java.awt.*;

public class CalculationField {

    private final JPanel panel = new JPanel();

    public CalculationField() {
        panel.setLayout(new GridLayout(5, 1));
        for(int i = 0; i < 5; i++) {
            panel.add(new CalculationRow().getPanel());
        }
    }

    public JPanel getPanel() {
        return panel;
    }

}
