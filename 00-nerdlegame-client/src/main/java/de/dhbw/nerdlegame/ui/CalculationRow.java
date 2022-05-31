package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.GuessDigitResult;
import de.dhbw.nerdlegame.guess.GuessResult;
import de.dhbw.nerdlegame.resource.DigitResultTypeResource;

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
    private final JButton guessButton;

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
            textField.setBackground(Color.WHITE);
            textField.setForeground(Color.BLACK);
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
        this.guessButton = new JButton("Guess");
        guessButton.setPreferredSize(new Dimension(200, 100));
        panel.add(guessButton);
    }

    public JPanel getPanel() {
        return panel;
    }

    JButton getGuessButton() {
        return guessButton;
    }

    public String getText() throws IllegalArgumentException {
        final StringBuilder stringBuilder = new StringBuilder();
        textFields.forEach(textField -> stringBuilder.append(textField.getText()));
        return stringBuilder.toString();
    }

    public void displayGuessResult(final GuessResult guessResult) {
        final GuessDigitResult[] results = guessResult.getDigitResults();
        for(int index = 0; index < Calculation.NUMBER_OF_DIGITS; index++) {
            final DigitResultTypeResource type = DigitResultTypeResource.fromDigitResult(results[index]);
            textFields.get(index).setBackground(type.backgroundColor());
        }
    }

    void enable() {
        textFields.forEach(textField -> textField.setEnabled(true));
        guessButton.setVisible(true);
    }

    void disable() {
        textFields.forEach(textFields -> textFields.setEnabled(false));
        guessButton.setVisible(false);
    }

    void resetRow() {
        guessButton.setVisible(false);
        enable();
        textFields.forEach(textField -> {
            textField.setText("");
            textField.setBackground(Color.WHITE);
        });
        disable();
    }

}
