package de.dhbw.nerdlegame.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow {

    private final JButton findGameButton = new JButton("Find game...");
    private final JFrame window = new JFrame("NerdleGame");

    public MainWindow() {
        window.setSize(800, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(new CalculationField().getPanel(), BorderLayout.CENTER);
        window.add(new GameLog().getPanel(), BorderLayout.SOUTH);
        final JPanel west = new JPanel(new GridLayout(5, 1));
        west.add(findGameButton);
        window.add(west, BorderLayout.WEST);
        window.setResizable(false);
        window.setVisible(true);
    }

    public void addFindGameButtonClickListener(final ActionListener listener) {
        findGameButton.addActionListener(listener);
    }

}
