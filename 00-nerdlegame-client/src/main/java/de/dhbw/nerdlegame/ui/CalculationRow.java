package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.calculation.Calculation;

import javax.swing.*;
import java.awt.*;

public class CalculationRow {

    private final JPanel panel = new JPanel();

    public CalculationRow() {
        panel.setLayout(new GridLayout(1, Calculation.NUMBER_OF_DIGITS + 1));
        for(int i = 0; i < Calculation.NUMBER_OF_DIGITS; i++) {
            final JTextField textField = new JTextField();
            final Font font = new Font("SansSerif", Font.BOLD, 20);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setFont(font);
            panel.add(textField);
        }
        JButton button = new JButton("Guess");
        button.setPreferredSize(new Dimension(200, 100));
        panel.add(button);
    }

    public JPanel getPanel() {
        return panel;
    }

}
