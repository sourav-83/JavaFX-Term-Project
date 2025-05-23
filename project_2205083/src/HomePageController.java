import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class HomePageController {

    @FXML
    private MenuButton searching_options;

    @FXML
    private MenuItem ByPlayerName;

    @FXML
    private MenuItem ByClubandCountry;

    @FXML
    private MenuItem ByPosition;

    @FXML
    private MenuItem BySalaryRange;

    @FXML
    private MenuItem Countrywiseplayercount;

    @FXML
    private MenuItem maximumsalary;

    @FXML
    private MenuItem maximumage;

    @FXML
    private MenuItem maximumheight;

    @FXML
    private MenuItem yearlysalary;

    @FXML
    private Button addplayer;

    @FXML
    private Button back;

    @FXML
    void backfunction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lab.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            // Add the CSS file link
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

            // Get the primary stage and set the new scene
            Stage primaryStage = (Stage) addplayer.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("IPL");
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error loading lab.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    void handleByPlayerName(ActionEvent event) {
        loadNewWindow("ByPlayerName.fxml", "Search By Player Name");
    }

    @FXML
    void handleByClubandCountry(ActionEvent event) {
        loadNewWindow("ByClubAndCountry.fxml", "Search By Club and Country");
    }

    @FXML
    void handleByPosition(ActionEvent event) {
        loadNewWindow("ByPosition.fxml", "Search By Position");
    }

    @FXML
    void handleBySalaryRange(ActionEvent event) {
        loadNewWindow("BySalaryRange.fxml", "Search By Salary Range");
    }

    @FXML
    void handleCountrywiseplayercount(ActionEvent event) {
        loadNewWindow("CountrywisePlayerCount.fxml", "Country-wise Player Count");
    }

    @FXML
    void handleMaximumSalary(ActionEvent event) {
        loadNewWindow("MaximumSalary.fxml", "Maximum Salary of Players in a Club");
    }

    @FXML
    void handleMaximumAge(ActionEvent event) {
        loadNewWindow("MaximumAge.fxml", "Maximum Age of Players in a Club");
    }

    @FXML
    void handleMaximumHeight(ActionEvent event) {
        loadNewWindow("MaximumHeight.fxml", "Maximum Height of Players in a Club");
    }

    @FXML
    void handleYearlySalary(ActionEvent event) {
        loadNewWindow("YearlySalary.fxml", "Total Yearly Salary of a Club");
    }

    @FXML
    void handleAddPlayer(ActionEvent event) {
        loadNewWindow("AddPlayer.fxml", "Add a New Player");
    }

    private void loadNewWindow(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML: " + fxmlFile);
            e.printStackTrace();
        }
    }
    
}
