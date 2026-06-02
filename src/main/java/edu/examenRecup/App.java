package edu.examenRecup;

import edu.examenRecup.util.AppShell;
import edu.examenRecup.util.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

   @Override
   public void start(Stage stage) {
       AppShell.getInstance().init(stage);
       AppShell.getInstance().loadView(View.LOGIN);
       AppShell.getInstance().adjustWindow();
   }

    public static void main(String[] args) {
        launch();
    }
}
