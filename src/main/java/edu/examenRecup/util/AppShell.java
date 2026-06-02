package edu.examenRecup.util;

import edu.examenRecup.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AppShell {
    private  static AppShell instance;
    private Stage primaryStage;
    private User sessionUser;

    private AppShell(){}
    public static AppShell getInstance(){
        if(instance==null){
            instance=new AppShell();
        }
        return instance;
    }
    public void init(Stage stage){
        this.primaryStage=stage;
        Scene scene = new Scene(new StackPane(), 900, 600);
        stage.setScene(scene);
        stage.setTitle("Examen Usuarios");
        stage.show();
    }

    public Object loadView(View view){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view.getFxmlPath()));
        try {
            Parent viewNode = loader.load();
            Object controller = loader.getController();

            if (view == View.MAIN || view == View.LOGIN) {
                primaryStage.setScene(new Scene(viewNode));
            } else {
                Parent currentRoot = primaryStage.getScene().getRoot();
                if (currentRoot instanceof BorderPane) {
                    BorderPane mainBorderPane = (BorderPane) currentRoot;
                    StackPane contentArea = (StackPane) mainBorderPane.getCenter();
                    if (contentArea != null) {
                        contentArea.getChildren().clear();
                    }
                } else {
                    primaryStage.getScene().setRoot(viewNode);
                }
            }
            return controller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void adjustWindow() {
        if (primaryStage != null) {
            primaryStage.sizeToScene();
            primaryStage.centerOnScreen();
        }
    }

    public User getSessionUser() {
        return sessionUser;
    }
    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
