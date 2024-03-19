package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MetricConverterGUI extends Application {

    private TextField inputField;
    private Label resultLabel;
  
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Metric Converter");

        // Create UI components
        inputField = new TextField();
        inputField.setPromptText("Enter your query");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert());

        resultLabel = new Label();

        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(inputField, convertButton, resultLabel);

        // Scene
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void convert() {
        String userInput = inputField.getText().toLowerCase();

        if (userInput.equals("exit") || userInput.equals("-1")) {
            resultLabel.setText("Exiting the program. Goodbye!");
            return;
        }

        if (isValidInput(userInput)) {
            try {
                performConversion(userInput);
            } catch (UnsupportedOperationException e) {
                resultLabel.setText("Conversion not supported for the given units.");
            }
        } else {
            resultLabel.setText("Invalid input format. Please follow the format, for example, '1 km = miles'.");
        }
    }

    private boolean isValidInput(String input) {
        // Validate input format
        // For simplicity, assume all inputs are valid in this example
        return true;
    }

    private void performConversion(String input) {
        String[] parts = input.split("\\s*=\\s*");
        if (parts.length == 2) {
            String[] valueAndUnitFrom = parts[0].trim().split("\\s+");
            String unitTo = parts[1].trim();

            double value = Double.parseDouble(valueAndUnitFrom[0]);
            String unitFrom = valueAndUnitFrom[1];

            double result = convert(value, unitFrom, unitTo);
            resultLabel.setText(value + " " + unitFrom + " = " + result + " " + unitTo);
        } else {
            throw new IllegalArgumentException("Invalid input format. Please follow the format, for example, '1 km = miles'.");
        }
    }

    private double convert(double value, String unitFrom, String unitTo) {
        // Implement conversion logic
        // For simplicity, use the same conversion methods as before
        switch (unitFrom.toLowerCase()) {
            case "km":
                return kilometersToMiles(value);
            case "celsius":
                return celsiusToFahrenheit(value);
            case "kg":
                return kilogramsToPounds(value);
            case "m":
                return metersToFeet(value);
            default:
                throw new UnsupportedOperationException("Conversion not supported for the given units.");
        }
    }

    private double kilometersToMiles(double kilometers) {
        return kilometers * 0.621371;
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double kilogramsToPounds(double kilograms) {
        return kilograms * 2.20462;
    }

    private double metersToFeet(double meters) {
        return meters * 3.28084;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
