package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.calculation.CalculationDigit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalculationRow {

    private final JPanel panel = new JPanel();

    public CalculationRow() {
        panel.setLayout(new GridLayout(1, Calculation.NUMBER_OF_DIGITS + 1));
        for(int i = 0; i < Calculation.NUMBER_OF_DIGITS; i++) {
            final JTextField textField = new JTextField();
            final Font font = new Font("SansSerif", Font.BOLD, 20);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setFont(font);
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(final KeyEvent e) {
                    try {
                        CalculationDigit.getDigit(e.getKeyChar());
                        super.keyTyped(e);
                    } catch (IllegalArgumentException ignored) {
                        e.consume();
                    }
                }
            });
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
