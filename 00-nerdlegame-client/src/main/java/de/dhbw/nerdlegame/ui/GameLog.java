package de.dhbw.nerdlegame.ui;

import javax.swing.*;
import java.awt.*;

public class GameLog {

    private final JPanel panel = new JPanel();

    private final JTextArea textArea = new JTextArea();
    public GameLog() {
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(500, 200));
        final JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
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
