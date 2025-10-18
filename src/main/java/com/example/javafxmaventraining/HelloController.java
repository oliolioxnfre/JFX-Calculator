package com.example.javafxmaventraining;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    private TextField display;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";
    private boolean justEqualed = false;
    private double result = 0;

    @FXML private Button cosButton;
    @FXML private Button sinButton;
    @FXML private Button tanButton;
    @FXML private Button cscButton;
    @FXML private Button secButton;
    @FXML private Button cotButton;
    @FXML private Button radDegButton;

    private boolean trigEnabled = false;

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        String value = button.getText();

        if (value.equals("Trig")) {
            trigEnabled = !trigEnabled; // toggle on/off
            setTrigButtonsEnabled(trigEnabled);
            return; // stop here
        }

        if (value.matches("\\d")) {
            if (justEqualed) {
                handleClear();
                justEqualed = false;
            }
            currentInput += value;
            display.setText(currentInput);
        } else {
            justEqualed = false;
            firstOperand = Double.parseDouble(currentInput);
            operator = value;
            if (operator.equals("√") || operator.equals("sin") || operator.equals("cos")
                    || operator.equals("tan") || operator.equals("csc")
                    || operator.equals("sec") || operator.equals("cot")) {
                handleEquals();
                return;
            }
            currentInput = "";
            display.setText(value);
        }
    }

    @FXML
    private void handleEquals() {
        double secondOperand = Double.parseDouble(currentInput);

        if(justEqualed){
            secondOperand = firstOperand;
            firstOperand = result;
        }
        else result = 0;

        switch (operator) {
            case "+" -> result = firstOperand + secondOperand;
            case "-" -> result = firstOperand - secondOperand;
            case "x" -> result = firstOperand * secondOperand;
            case "/" -> result = firstOperand / secondOperand;
            case "^" -> result = Math.pow(firstOperand, secondOperand);
            case "√" -> result = Math.sqrt(firstOperand);
            case "sin" -> result = Math.sin(firstOperand);
            case "cos" -> result = Math.cos(firstOperand);
            case "tan" -> result = Math.tan(firstOperand);
            case "csc" -> result = (1/Math.sin(firstOperand));
            case "sec" -> result = (1/Math.cos(firstOperand));
            case "cot" -> result = (1/Math.tan(firstOperand));
            // Add other cases later
        }

        display.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
        firstOperand = secondOperand;
        justEqualed = true;
    }

    @FXML
    private void handleClear() {
        firstOperand = 0;
        display.clear();
        currentInput = "";
        operator = "";
    }
    @FXML
    private void setTrigButtonsEnabled(boolean enabled) {
        cosButton.setDisable(!enabled);
        sinButton.setDisable(!enabled);
        tanButton.setDisable(!enabled);
        secButton.setDisable(!enabled);
        cscButton.setDisable(!enabled);
        cotButton.setDisable(!enabled);
        radDegButton.setDisable(!enabled);

    }
}