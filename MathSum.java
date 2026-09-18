import java.awt.*;
import javax.swing.*;

public class MathSum extends JFrame {
    private final JTextField displayField = new JTextField();
    private final JButton[] numberButtons = new JButton[10];
    private final JButton addButton = new JButton("+");
    private final JButton subtractButton = new JButton("-");
    private final JButton multiplyButton = new JButton("×");
    private final JButton divideButton = new JButton("÷");
    private final JButton exponentButton = new JButton("^");
    private final JButton equalsButton = new JButton("=");
    private final JButton clearButton = new JButton("C");
    private final JButton decimalButton = new JButton(".");

    private double firstNumber = 0;
    private String operator = "+";
    private boolean startNewNumber = true;

    public MathSum() {
        setTitle("Classic Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(240, 240, 240));

        displayField.setEditable(false);
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setFont(new Font("SansSerif", Font.BOLD, 30));
        displayField.setBackground(new Color(255, 255, 255));
        displayField.setText("0");
        displayField.setPreferredSize(new Dimension(300, 70));
        mainPanel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 3, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        for (int i = 1; i <= 9; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            styleButton(numberButtons[i], new Color(245, 245, 245));
            buttonPanel.add(numberButtons[i]);
        }

        clearButton.setFont(new Font("SansSerif", Font.BOLD, 22));
        styleButton(clearButton, new Color(255, 190, 190));
        buttonPanel.add(clearButton);

        numberButtons[0] = new JButton("0");
        styleButton(numberButtons[0], new Color(245, 245, 245));
        buttonPanel.add(numberButtons[0]);

        decimalButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        styleButton(decimalButton, new Color(245, 245, 245));
        buttonPanel.add(decimalButton);

        addButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        styleButton(addButton, new Color(220, 230, 255));
        buttonPanel.add(addButton);

        subtractButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        styleButton(subtractButton, new Color(220, 230, 255));
        buttonPanel.add(subtractButton);

        multiplyButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        styleButton(multiplyButton, new Color(220, 230, 255));
        buttonPanel.add(multiplyButton);

        divideButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        styleButton(divideButton, new Color(220, 230, 255));
        buttonPanel.add(divideButton);

        exponentButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        styleButton(exponentButton, new Color(220, 230, 255));
        buttonPanel.add(exponentButton);

        equalsButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        styleButton(equalsButton, new Color(200, 255, 200));
        buttonPanel.add(equalsButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        attachNumberListeners();
        attachOperatorListeners();
        decimalButton.addActionListener(e -> appendDecimal());
        clearButton.addActionListener(e -> clearDisplay());
        equalsButton.addActionListener(e -> calculateResult());
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setPreferredSize(new Dimension(70, 70));
    }

    private void attachNumberListeners() {
        for (int i = 0; i <= 9; i++) {
            final int value = i;
            numberButtons[i].addActionListener(e -> {
                if (startNewNumber) {
                    displayField.setText("");
                    startNewNumber = false;
                }
                displayField.setText(displayField.getText() + value);
            });
        }
    }

    private void attachOperatorListeners() {
        addButton.addActionListener(e -> handleOperator("+"));
        subtractButton.addActionListener(e -> handleOperator("-"));
        multiplyButton.addActionListener(e -> handleOperator("*"));
        divideButton.addActionListener(e -> handleOperator("/"));
        exponentButton.addActionListener(e -> handleOperator("^"));
    }

    private void appendDecimal() {
        String text = displayField.getText();
        if (!text.contains(".")) {
            if (text.isEmpty() || startNewNumber) {
                displayField.setText("0.");
                startNewNumber = false;
            } else {
                displayField.setText(text + ".");
            }
        }
    }

    private void handleOperator(String selectedOperator) {
        if (startNewNumber && operator != null) {
            operator = selectedOperator;
            return;
        }

        double currentValue = Double.parseDouble(displayField.getText());
        if (!startNewNumber) {
            if (!tryCalculate(currentValue)) {
                return;
            }
        }

        operator = selectedOperator;
        startNewNumber = true;
    }

    private void calculateResult() {
        if (startNewNumber) {
            return;
        }

        double currentValue = Double.parseDouble(displayField.getText());
        if (!tryCalculate(currentValue)) {
            return;
        }
        startNewNumber = true;
    }

    private boolean tryCalculate(double currentValue) {
        try {
            firstNumber = CalculatorEngine.calculate(firstNumber, operator, currentValue);
            displayField.setText(String.valueOf(firstNumber));
            return true;
        } catch (ArithmeticException e) {
            displayField.setText("Error");
            startNewNumber = true;
            return false;
        }
    }

    private void clearDisplay() {
        displayField.setText("0");
        firstNumber = 0;
        operator = "+";
        startNewNumber = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MathSum().setVisible(true);
        });
    }
}
