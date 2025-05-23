import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    String clubname;

    @FXML
    private Button enter;

    @FXML
    private ImageView ipl;

    @FXML
    private Label login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void guest(ActionEvent event) {
        Stage stage = (Stage) enter.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        System.out.println("Hi");
        stage.setScene(scene);

    }

    @FXML
    void login(ActionEvent event) {

        Socket sc = null;
        try {

            sc = new Socket("localhost", 2083);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(sc.getInputStream());
            oos = new ObjectOutputStream(sc.getOutputStream());
            oos.writeObject(username.getText().trim());
            oos.writeObject(password.getText().trim());
            String s1 = null;
            try {
                s1 = (String) ois.readObject();
                String[] arr = s1.split(",");
                clubname = arr[1];
                s1 = arr[0];
                System.out.println(s1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (s1.equals("Correct")) {
                Parent root = null;
                ServerConnection sConnection = ServerConnection.getInstance();
                sConnection.setStreams(oos, ois);

                try {
                    // --------------------------------------------
                    /*
                     * FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("club.fxml"));
                     * root = fxmlLoader.load();
                     * 
                     * Stage stage = new Stage();
                     * Scene scene = new Scene(root);
                     * scene.getStylesheets().add(getClass().getResource("styles.css").
                     * toExternalForm());
                     * 
                     * stage.setTitle(clubname);
                     * stage.setScene(scene);
                     * stage.show();
                     */
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("club.fxml"));
                    root = fxmlLoader.load();

                    // Access the controller to interact with the FXML elements
                    clubcontroller controller = fxmlLoader.getController();

                    // Set the background image
                    ImageView backgroundImageView = (ImageView) root.lookup("#backgroundImageView");
                    if (backgroundImageView != null) {
                        backgroundImageView.setImage(new Image(getClass().getResourceAsStream(clubname+".jpg")));
                    }

                    // Set up the stage and scene
                    Stage currentStage = (Stage) enter.getScene().getWindow();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

                    stage.setTitle(clubname);
                    stage.setScene(scene);
                    stage.show();
                    currentStage.close();

                    // -------------------------------------------
                } catch (IOException e) {
                    System.err.println("Error loading FXML: " + "club.fxml");
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Incorrect Credentials");
                alert.setHeaderText("Incorrect Credentials");
                alert.setContentText("The username and password you provided is not correct.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObjectInputStream getOStream() {
        return ois;
    }

    public ObjectOutputStream getIStream() {
        return oos;
    }

}
