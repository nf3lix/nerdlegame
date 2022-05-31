package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.calculation.Calculation;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
            textField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    int offset;
                    if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        offset = 1;
                    } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                        offset = -1;
                    }
                    else return;
                    try {
                        final JTextField tf = textFields.get(count + offset);
                        tf.requestFocus();
                        super.keyReleased(e);
                    } catch (IndexOutOfBoundsException ignored) { }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE && textField.getText().equals("")) {
                        try {
                            final JTextField tf = textFields.get(count - 1);
                            tf.requestFocus();
                            super.keyReleased(e);
                        } catch (IndexOutOfBoundsException ignored) { }
                    }
                    super.keyPressed(e);
                }
            });
            PlainDocument doc = (PlainDocument) textField.getDocument();
            final CalculationDigitFilter filter = new CalculationDigitFilter();
            filter.addDigitTypedListener(() -> {
                try {
                    final JTextField tf = textFields.get(count + 1);
                    tf.requestFocus();
                } catch (IndexOutOfBoundsException ignored) { }
            });
            doc.setDocumentFilter(filter);
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
