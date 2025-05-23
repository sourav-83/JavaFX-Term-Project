import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ByClubAndCountryController {

    @FXML
    private TextField clubField;

    @FXML
    private TextField countryField;

    @FXML
    private TableView<player> playerTable;

    @FXML
    private TableColumn<player, String> nameColumn;

    @FXML
    private TableColumn<player, String> countryColumn;

    @FXML
    private TableColumn<player, Integer> ageColumn;

    @FXML
    private TableColumn<player, Double> heightColumn;

    @FXML
    private TableColumn<player, String> clubColumn;

    @FXML
    private TableColumn<player, String> positionColumn;

    @FXML
    private TableColumn<player, Integer> numberColumn;

    @FXML
    private TableColumn<player, Integer> salaryColumn;

    private ObservableList<player> playerData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the table columns with the Player class's properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        clubColumn.setCellValueFactory(new PropertyValueFactory<>("club"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Attach the observable list to the table view
        playerTable.setItems(playerData);
    }

    @FXML
    private void handleSearch() {
        String clubname = clubField.getText();
        String countryname = countryField.getText();
        if (!clubname.isEmpty() && !countryname.isEmpty()) {

            // Call the search_name function from the Players class

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

            ArrayList<player> players = database.search_club_country(countryname, clubname);

            // Clear the existing data in the table
            playerData.clear();

            // Add the new data to the observable list
            playerData.addAll(players);
        }
    }
}
