package dad.enviaremail;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EnviarEmailApp extends Application {
   private RootController rootController = new RootController();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.getIcons().add(new Image("/images/email-icon.png"));
        primaryStage.setTitle("Enviar Email");
        primaryStage.show();
    }
}
