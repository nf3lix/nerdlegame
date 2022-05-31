package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.CommandObservable;
import de.dhbw.nerdlegame.CommandObserver;
import de.dhbw.nerdlegame.calculation.Calculation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class MainWindow implements CommandObservable {

    private final Set<CommandObserver> observers = new HashSet<>();
    private final JButton findGameButton = new JButton("Find game...");
    private final JFrame window = new JFrame("NerdleGame");

    public MainWindow() {
        window.setSize(800, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        final CalculationField calculationField = new CalculationField();
        window.add(calculationField.getPanel(), BorderLayout.CENTER);
        window.add(new GameLog().getPanel(), BorderLayout.SOUTH);
        final JPanel west = new JPanel(new GridLayout(5, 1));
        west.add(findGameButton);
        window.add(west, BorderLayout.WEST);
        window.setResizable(false);
        window.setVisible(true);
        for(int i = 0; i < 5; i++) {
            final int index = i;
            calculationField.guessButtonAt(i).addActionListener(actionEvent -> observers.forEach(observer -> {
                final String text = calculationField.getRow(index).getText();
                try {
                    new Calculation(text);
                    observer.onCommand("guess " + text);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(window, "Invalid calculation");
                }
            }));
        }
    }

    public void addFindGameButtonClickListener(final ActionListener listener) {
        findGameButton.addActionListener(listener);
    }

    @Override
    public void addCommandListener(CommandObserver observer) {
        this.observers.add(observer);
    }
}
