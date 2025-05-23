import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CountrywisePlayerCountController {

    // FXML elements
    @FXML
    private TableView<country> countryPlayerTable;

    @FXML
    private TableColumn<country, String> countryColumn;

    @FXML
    private TableColumn<country, Integer> playerCountColumn;

    // ArrayList to store Country objects
    private final ArrayList<country> countryList = new ArrayList<>();

    @FXML
    public void initialize() {
        // Set up TableColumn mappings
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerCountColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        // Populate the ArrayList with sample data
        

        // Convert ArrayList to ObservableList and set it to the TableView
        ObservableList<country> observableCountryList = FXCollections.observableArrayList(countryList);
        countryPlayerTable.setItems(observableCountryList);


        

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

            ArrayList<country> players = database.search_country();

            // Clear the existing data in the table
            observableCountryList.clear();

            // Add the new data to the observable list
            observableCountryList.addAll(players);











    }

    // Populate the countryList with sample dat

    
    
}
