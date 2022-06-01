package de.dhbw.nerdlegame.ui;

import de.dhbw.nerdlegame.CommandObservable;
import de.dhbw.nerdlegame.CommandObserver;
import de.dhbw.nerdlegame.NerdleGame;
import de.dhbw.nerdlegame.calculation.Calculation;
import de.dhbw.nerdlegame.guess.GuessResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class MainWindow implements CommandObservable {

    private final Set<CommandObserver> observers = new HashSet<>();
    private final JButton findGameButton = new JButton("Find game...");
    private final JFrame window = new JFrame("NerdleGame");
    private final CalculationField calculationField = new CalculationField();
    private final GameLog gameLog = new GameLog();

    public MainWindow() {
        window.setSize(800, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.add(calculationField.getPanel(), BorderLayout.CENTER);
        window.add(gameLog.getPanel(), BorderLayout.SOUTH);
        final JPanel west = new JPanel(new GridLayout(5, 1));
        west.add(findGameButton);
        window.add(west, BorderLayout.WEST);
        window.setResizable(false);
        window.setVisible(true);
        for(int i = 0; i < NerdleGame.MAX_GUESSES; i++) {
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

    public void resetGame() {
        calculationField.resetField();
        gameLog.resetLog();
    }

    public void displayGuessResult(final int index, final GuessResult guessResult) {
        this.calculationField.getRow(index).displayGuessResult(guessResult);
    }

    public void enableRow(final int index) {
        this.calculationField.getRow(index).enable();
    }

    public void disableRow(final int index) {
        this.calculationField.getRow(index).disable();
    }

    public void disableAllRows() {
        for(int i = 0; i < NerdleGame.MAX_GUESSES; i++) {
            this.calculationField.getRow(i).disable();
        }
    }

    public void addLogMessage(final String message) {
        gameLog.addLogMessage(message);
    }

    public void addFindGameButtonClickListener(final ActionListener listener) {
        findGameButton.addActionListener(listener);
    }

    @Override
    public void addCommandListener(CommandObserver observer) {
        this.observers.add(observer);
    }

    public JFrame frame() {
        return window;
    }

}
