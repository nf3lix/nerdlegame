package de.dhbw.nerdlegame.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private final JFrame window = new JFrame("NerdleGame");

    public MainWindow() {
        window.setSize(800, 600);
        window.setLayout(new BorderLayout());
        window.add(new CalculationField().getPanel(), BorderLayout.CENTER);
        window.add(new GameLog().getPanel(), BorderLayout.SOUTH);
        final JPanel west = new JPanel(new GridLayout(5, 1));
        final JButton button = new JButton("Find game...");
        west.add(button);
        window.add(west, BorderLayout.WEST);
        window.setResizable(false);
        window.setVisible(true);
    }

}
