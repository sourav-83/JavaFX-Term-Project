import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddPlayerController {

    // FXML fields for user input
    @FXML
    private TextField nameField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField clubField;

    @FXML
    private TextField positionField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField salaryField;

    

    // Method to save the player
    @FXML
    private void savePlayer() {
        try {
            // Get input values from TextFields
            String name1 = nameField.getText();
            String country1 = countryField.getText();
            int age1 = Integer.parseInt(ageField.getText());
            double height1 = Double.parseDouble(heightField.getText());
            String club1 = clubField.getText();
            String position1 = positionField.getText();
            int number1 = Integer.parseInt(numberField.getText());
            int salary1 = Integer.parseInt(salaryField.getText());

            // Create a new Player object
            player p1 = new player(name1, country1, age1, height1, club1, position1, number1, salary1);

            // --------------------------------------------------

            String filename = "C:\\\\Users\\\\soura\\\\OneDrive\\\\Desktop\\\\project\\\\project_2205083\\\\src\\\\players.txt";
            players database = new players();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    String name = data[0];
                    String country = data[1];
                    int age = Integer.parseInt(data[2]);
                    double height = Double.parseDouble(data[3]);
                    String club = data[4];
                    String position = data[5];
                    int number = data[6].isEmpty() ? -1 : Integer.parseInt(data[6]);
                    int weeklySalary = Integer.parseInt(data[7]);

                    player p = new player(name, country, age, height, club, position, number, weeklySalary);
                    database.add(p);
                }
            } catch (IOException e) {
                System.out.println("Error reading from file: " + e.getMessage());
            }
            if (database.search_name_number(name1) != 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("This player already exists!.");
                alert.showAndWait();
            } else {
                database.add(p1);
                database.add_to_file(1);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Player Saved");
                alert.setHeaderText(null);
                alert.setContentText("Player " + p1.getName() + " has been saved successfully!");
                alert.showAndWait();

                // Clear the fields after saving
                clearFields();

            }

            // --------------------------------------------------

        } catch (NumberFormatException e) {
            // Show an error alert if input is invalid
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid values for all fields.");
            alert.showAndWait();
        }
    }

    // Method to clear all input fields
    @FXML
    private void clearFields() {
        nameField.clear();
        countryField.clear();
        ageField.clear();
        heightField.clear();
        clubField.clear();
        positionField.clear();
        numberField.clear();
        salaryField.clear();
    }

    // Method to cancel and clear fields
    @FXML
    private void cancel() {
        clearFields();
    }

}
