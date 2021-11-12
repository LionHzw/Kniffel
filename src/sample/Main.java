package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Main extends Application {

    //private static final Logger log = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.startClient();

            //log.info("test");
            Scene scene = new Scene(root);
            stage.setTitle("Kniffel");
            stage.setScene(scene);
            stage.setResizable(false);
            scene.setCursor(new ImageCursor(new Image("/resources/Cursor.png")));
            stage.getIcons().add(new Image(MiscFilePath.DICE5.filePath));
            stage.setOnCloseRequest(event -> controller.windowClosed());
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.show();
        } catch (Exception e) {
            System.out.println("Exception while creating GUI: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
