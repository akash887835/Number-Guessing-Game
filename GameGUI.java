import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GameGUI extends JFrame implements ActionListener {

    private Random rand = new Random();
    private int number, attempts, maxAttempts = 7, score = 0;

    private JTextField inputField;
    private JLabel messageLabel, attemptsLabel, scoreLabel;
    private JButton guessButton, newGameButton;

    public GameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        // Components
        messageLabel = new JLabel("Guess a number between 1 and 100!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        inputField = new JTextField();
        attemptsLabel = new JLabel("Attempts left: " + maxAttempts, SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);

        guessButton = new JButton("Guess");
        newGameButton = new JButton("New Game");

        guessButton.addActionListener(this);
        newGameButton.addActionListener(e -> startNewGame());

        // Add components
        add(messageLabel);
        add(inputField);
        add(attemptsLabel);
        add(scoreLabel);
        add(guessButton);
        add(newGameButton);

        startNewGame();
        setVisible(true);
    }

    private void startNewGame() {
        number = rand.nextInt(100) + 1;
        attempts = maxAttempts;
        attemptsLabel.setText("Attempts left: " + attempts);
        messageLabel.setText("New game started! Guess a number 1–100.");
        inputField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int guess = Integer.parseInt(inputField.getText());
            attempts--;

            if (guess == number) {
                messageLabel.setText(" Correct! You guessed it!");
                score++;
                scoreLabel.setText("Score: " + score);
            } else if (guess > number) {
                messageLabel.setText("Too High! Try again.");
            } else {
                messageLabel.setText("Too Low! Try again.");
            }

            attemptsLabel.setText("Attempts left: " + attempts);

            if (attempts == 0 && guess != number) {
                messageLabel.setText(" Out of attempts! Number was: " + number);
            }

        } catch (Exception ex) {
            messageLabel.setText("Invalid input! Enter a number.");
        }
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
