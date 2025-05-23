import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class clubcontroller {

    // FXML UI elements
    @FXML
    private ImageView backgroundImageView;
    @FXML
    private TextField variable;
    @FXML
    private TableView<player> tablePlayers;
    @FXML
    private TableColumn<player, String> colName;
    @FXML
    private TableColumn<player, String> colCountry;
    @FXML
    private TableColumn<player, Integer> colAge;
    @FXML
    private TableColumn<player, Double> colHeight;
    @FXML
    private TableColumn<player, String> colClub;
    @FXML
    private TableColumn<player, String> colPosition;
    @FXML
    private TableColumn<player, Integer> colNumber;
    @FXML
    private TableColumn<player, Double> colSalary;

    @FXML
    private Button btnBuyPlayer;
    @FXML
    private Button btnConfirmAction;
    @FXML
    private Button btnViewPlayers;
    @FXML
    private Label selectedPlayerLabel;
    @FXML
    private HBox popupBox;
    @FXML
    private Button btnSellPlayers;
    @FXML
    private TextField selltext;
    @FXML
    private TextField buytext;

    private ObservableList<player> playerList = FXCollections.observableArrayList(); // Holds player data

    @FXML
    public void initialize() {
        
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        colClub.setCellValueFactory(new PropertyValueFactory<>("club"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        // Hide the popup initially
        popupBox.setVisible(false);

        
        new readPlayer();
        variable.setText("Viewing Club Players");
        tablePlayers.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) { 
                player selectedPlayer = tablePlayers.getSelectionModel().getSelectedItem();
                if (selectedPlayer != null) {
                    handleRowClick(selectedPlayer.getName() + "," + selectedPlayer.getClub());
                }
            }
        });

    }

    private void handleRowClick(String name) {
        Platform.runLater(() -> {
            String strings[] = name.split(",");
            String title = strings[0] + ".jpg";
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(strings[0]);
            alert.setContentText(strings[1]);

            Image image = new Image(getClass().getResourceAsStream(title));
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            alert.setGraphic(imageView);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.lookup(".header-panel").setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

            alert.showAndWait();

        });

    }

    @FXML
    void handlebuy(ActionEvent event) {

        String name = buytext.getText().trim();
        ServerConnection server = ServerConnection.getInstance();
        ObjectOutputStream output = server.getOutput();

        try {
            // Send "buyPlayer" request to the server
            output.writeObject("confirmBuyPlayer," + name);
            output.flush(); // Ensure the message is sent immediately
            System.out.println("Request sent to buy a player.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to send 'buyPlayer' request to the server.");
        }

    }

    @FXML
    void handlesell(ActionEvent event) {

        String name = selltext.getText().trim();
        ServerConnection server = ServerConnection.getInstance();
        ObjectOutputStream output = server.getOutput();

        try {
            // Send "buyPlayer" request to the server
            output.writeObject("sellPlayer," + name);
            output.flush(); // Ensure the message is sent immediately
            System.out.println("Request sent to sell a player.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to send 'sellPlayer' request to the server.");
        }

    }

    @FXML
    void handleviewpurchasableplayers(ActionEvent event) {
        ServerConnection server = ServerConnection.getInstance();
        ObjectOutputStream output = server.getOutput();

        try {
            // Send "buyPlayer" request to the server
            output.writeObject("buyPlayer");
            output.flush(); // Ensure the message is sent immediately
            System.out.println("Request sent to view purchasable players.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to send 'buyPlayer' request to the server.");
        }

    }

    @FXML
    private void handleViewPlayers(ActionEvent event) {
        ServerConnection server = ServerConnection.getInstance();
        ObjectOutputStream output = server.getOutput();

        try {
            // Send "buyPlayer" request to the server
            output.writeObject("showPlayer");
            output.flush(); // Ensure the message is sent immediately
            System.out.println("Request sent to view own players.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to send 'handleViewPlayer' request to the server.");
        }
    }

    // Inner class to fetch player data from the server
    private class readPlayer implements Runnable {
        private ObjectInputStream input;
        private ObjectOutputStream output;

        public readPlayer() {
            ServerConnection s = ServerConnection.getInstance();
            input = s.getInput();
            output = s.getOutput();

            try {
                output.writeObject("showPlayer"); // Request player data
            } catch (IOException e) {
                throw new RuntimeException("Failed to send 'handleViewPlayer' request to server", e);
            }

            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String msg = (String) input.readObject();

                    if ("playerList".equals(msg)) {
                        player[] arr = (player[]) input.readObject();

                        System.out.println("Number of own players received: " + arr.length);

                        Platform.runLater(() -> {
                            // Update the playerList
                            playerList.clear();
                            playerList.addAll(arr);

                            System.out.println("Number of own players received: " + arr.length);
                            // Refresh the table
                            tablePlayers.setItems(playerList);
                            variable.setText("Viewing Club Players");
                        });

                    } else if ("sellList".equals(msg)) {

                        player[] arr2 = (player[]) input.readObject();
                        System.out.println("Number of purchasable players received: " + arr2.length);

                        Platform.runLater(() -> {
                            // Update the playerList
                            playerList.clear();
                            playerList.addAll(arr2);

                            System.out.println("Number of purchasable players received: " + arr2.length);
                            // Refresh the table
                            tablePlayers.setItems(playerList);
                            variable.setText("Viewing Purchasable Players");
                        });

                    } else if ("purchase request received".equals(msg)) {
                        //System.out.println("31-12-2024");
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("notification");
                            alert.setHeaderText("Purchase request received successfully by server");
                            alert.setContentText("success!");
                            alert.showAndWait();
                        });

                    } else if ("player successfully bought".equals(msg)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("notification");
                            alert.setHeaderText("Player purchase successful");
                            alert.setContentText("success!");
                            alert.showAndWait();
                        });

                    } else if ("player purchase unsuccessful".equals(msg)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("notification");
                            alert.setHeaderText("The player is not available in the purchasable players list");
                            alert.setContentText("unsuccessful attempt");
                            alert.showAndWait();
                        });

                    } else if ("player sell request unsuccessful".equals(msg)) {
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("notification");
                            alert.setHeaderText("The player is not available in your team");
                            alert.setContentText("unsuccessful attempt");
                            alert.showAndWait();
                        });

                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break; // Exit the loop in case of an error
                }
            }
        }
    }
}
