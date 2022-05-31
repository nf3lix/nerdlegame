package de.dhbw.nerdlegame.ui;

import javax.swing.*;
import java.awt.*;

public class GameLog {

    private final JPanel panel = new JPanel();

    private final JTextArea textArea = new JTextArea();
    public GameLog() {
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(500, 200));
        panel.add(textArea);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void addLogMessage(final String message) {
        this.textArea.append("\n" + message);
    }

    void resetLog() {
        textArea.setText("");
    }

}
