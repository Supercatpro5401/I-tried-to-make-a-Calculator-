import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimpleCalculatorFinalV1 extends JFrame implements ActionListener {

    private JTextField displayField;
    private ArrayList<Double> numbers;
    private String operator;
    private double firstOperand;
    private boolean startNewNumber;

    public SimpleCalculatorFinalV1() {
        setTitle("Graphical Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        numbers = new ArrayList<>();
        operator = "";
        startNewNumber = true;

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(25, 25, 25));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        displayField = new JTextField("0");
        displayField.setFont(new Font("Arial", Font.BOLD, 40));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setEditable(false);
        displayField.setBackground(new Color(40, 40, 40));
        displayField.setForeground(Color.WHITE);
        displayField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 15, 15));
        buttonPanel.setBackground(new Color(25, 25, 25));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "sqrt", "pow", "log",
            "sin", "cos", "clear", "enter"
        };
        
        Map<String, Color> specialButtonColors = new HashMap<>();
        specialButtonColors.put("=", new Color(255, 150, 0)); 
        specialButtonColors.put("C", new Color(220, 50, 50)); 
        specialButtonColors.put("/", new Color(100, 100, 255));
        specialButtonColors.put("*", new Color(100, 100, 255));
        specialButtonColors.put("-", new Color(100, 100, 255));
        specialButtonColors.put("+", new Color(100, 100, 255));
        specialButtonColors.put("sqrt", new Color(50, 150, 220));
        specialButtonColors.put("pow", new Color(50, 150, 220));
        specialButtonColors.put("log", new Color(50, 150, 220));
        specialButtonColors.put("sin", new Color(50, 150, 220));
        specialButtonColors.put("cos", new Color(50, 150, 220));
        specialButtonColors.put("clear", new Color(220, 50, 50));
        specialButtonColors.put("enter", new Color(0, 150, 50));

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder());
            
            Color buttonColor = specialButtonColors.getOrDefault(label, new Color(80, 80, 80));
            button.setBackground(buttonColor);
            
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
            try {
                firstOperand = Double.parseDouble(displayField.getText());
                operator = command;
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if ("=".equals(command)) {
            if (!operator.isEmpty()) {
                try {
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
                        case "pow":
                            result = Math.pow(firstOperand, secondOperand);
                            break;
                    }
                    displayField.setText(String.valueOf(result));
                    operator = "";
                    startNewNumber = true;
                } catch (NumberFormatException ex) {
                    displayField.setText("Error");
                }
            }
        } else if ("C".equals(command)) {
            displayField.setText("0");
            operator = "";
            startNewNumber = true;
        } else if ("sqrt".equals(command)) {
            try {
                double num = Double.parseDouble(displayField.getText());
                if (num >= 0) {
                    displayField.setText(String.valueOf(Math.sqrt(num)));
                } else {
                    displayField.setText("Error");
                }
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if ("log".equals(command)) {
            try {
                double num = Double.parseDouble(displayField.getText());
                if (num > 0) {
                    displayField.setText(String.valueOf(Math.log(num)));
                } else {
                    displayField.setText("Error");
                }
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if ("sin".equals(command)) {
            try {
                double num = Double.parseDouble(displayField.getText());
                displayField.setText(String.valueOf(Math.sin(num)));
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if ("cos".equals(command)) {
            try {
                double num = Double.parseDouble(displayField.getText());
                displayField.setText(String.valueOf(Math.cos(num)));
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if ("pow".equals(command)) {
            try {
                firstOperand = Double.parseDouble(displayField.getText());
                operator = "pow";
                startNewNumber = true;
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
        } else if ("clear".equals(command)) {
            numbers.clear();
            displayField.setText("0");
        } else if ("enter".equals(command)) {
            try {
                double num = Double.parseDouble(displayField.getText());
                if (numbers.size() < 64) {
                    numbers.add(num);
                } else {
                    displayField.setText("Mem Full");
                }
            } catch (NumberFormatException ex) {
                displayField.setText("Error");
            }
            displayField.setText("0");
            startNewNumber = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculatorFinalV1());
    }
}
