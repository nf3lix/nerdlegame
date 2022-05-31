package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.calculation.CalculationDigit;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalculationRow {

    private final JPanel panel = new JPanel();
    private final List<JTextField> textFields = new LinkedList<>();

    public CalculationRow() {
        panel.setLayout(new GridLayout(1, Calculation.NUMBER_OF_DIGITS + 1));
        for(int i = 0; i < Calculation.NUMBER_OF_DIGITS; i++) {
            final int count = i;
            final JTextField textField = new JTextField();
            textFields.add(textField);
            final Font font = new Font("SansSerif", Font.BOLD, 20);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setFont(font);
            textField.setTransferHandler(null);
            PlainDocument doc = (PlainDocument) textField.getDocument();
            doc.setDocumentFilter(new CalculationDigitFilter());
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
