/*import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ServerApp {

    private static boolean isJavaFxInitialized = false;

    private static synchronized void initJavaFX() {
        if (!isJavaFxInitialized) {
            Platform.startup(() -> {});  // Initialize JavaFX Toolkit
            isJavaFxInitialized = true;
        }
    }

    public static void playMusic(String filePath) {
        initJavaFX();  // Ensure JavaFX is initialized
        Platform.runLater(() -> {
            try {
                Media media = new Media(filePath);
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}*/
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ServerApp {

    private static boolean isJavaFxInitialized = false;

    // Method to initialize JavaFX
    private static synchronized void initJavaFX() {
        if (!isJavaFxInitialized) {
            Platform.startup(() -> {});  // Initialize JavaFX Toolkit
            isJavaFxInitialized = true;
        }
    }

    // Method to play music
    public static void playMusic(String filePath) {
        initJavaFX();  // Ensure JavaFX is initialized
        Platform.runLater(() -> {
            try {
                // Convert the raw file path to a URI
                File file = new File(filePath);
                String uri = file.toURI().toString(); // Convert file path to URI format

                // Create Media and MediaPlayer objects
                Media media = new Media(uri);
                MediaPlayer mediaPlayer = new MediaPlayer(media);

                // Play the music
                mediaPlayer.play();
                System.out.println("Playing music: " + filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

