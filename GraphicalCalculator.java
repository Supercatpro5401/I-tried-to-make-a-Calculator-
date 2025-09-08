import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import java.util.ArrayList;

public class GraphicalCalculator extends JFrame implements ActionListener {

    private JTextField displayField;
    private ArrayList<Double> numbers;
    private String operator;
    private double firstOperand;
    private boolean startNewNumber;

    public GraphicalCalculator() {
        setTitle("Graphical Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        numbers = new ArrayList<>();
        operator = "";
        startNewNumber = true;

        JPanel mainPanel = new JPanel(new BorderLayout());

        displayField = new JTextField("0");
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setEditable(false);
        mainPanel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "sqrt", "pow", "log",
            "sin", "cos", "clear", "enter"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789.".contains(command)) {
            if (startNewNumber) {
                displayField.setText(command.equals(".") ? "0." : command);
                startNewNumber = false;
            } else if (!command.equals(".") || !displayField.getText().contains(".")) {
                displayField.setText(displayField.getText() + command);
            }
        } else if ("+-*/".contains(command)) {
            firstOperand = Double.parseDouble(displayField.getText());
            operator = command;
            startNewNumber = true;
        } else if ("=".equals(command)) {
            if (!operator.isEmpty()) {
                double secondOperand = Double.parseDouble(displayField.getText());
                double result = 0;
                switch (operator) {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            result = firstOperand / secondOperand;
                        } else {
                            displayField.setText("Error");
                            return;
                        }
                        break;
                }
                displayField.setText(String.valueOf(result));
                operator = "";
                startNewNumber = true;
            }
        } else if ("C".equals(command)) {
            displayField.setText("0");
            operator = "";
            startNewNumber = true;
        } else if ("sqrt".equals(command)) {
            double num = Double.parseDouble(displayField.getText());
            if (num >= 0) {
                displayField.setText(String.valueOf(Math.sqrt(num)));
            } else {
                displayField.setText("Error");
            }
            startNewNumber = true;
        } else if ("pow".equals(command)) {
            firstOperand = Double.parseDouble(displayField.getText());
            operator = "pow";
            startNewNumber = true;
        } else if ("log".equals(command)) {
            double num = Double.parseDouble(displayField.getText());
            if (num > 0) {
                displayField.setText(String.valueOf(Math.log(num)));
            } else {
                displayField.setText("Error");
            }
            startNewNumber = true;
        } else if ("sin".equals(command)) {
            double num = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(Math.sin(num)));
            startNewNumber = true;
        } else if ("cos".equals(command)) {
            double num = Double.parseDouble(displayField.getText());
            displayField.setText(String.valueOf(Math.cos(num)));
            startNewNumber = true;
        } else if ("clear".equals(command)) {
            numbers.clear();
            displayField.setText("0");
            System.out.println("All numbers cleared from memory.");
        } else if ("enter".equals(command)) {
            try {
                double num = Double.parseDouble(displayField.getText());
                if (numbers.size() < 64) {
                    numbers.add(num);
                    System.out.println("Number added to memory: " + num);
                } else {
                    System.out.println("Memory capacity reached. Cannot add more.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number format.");
            }
            displayField.setText("0");
            startNewNumber = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GraphicalCalculator());
    }
}
