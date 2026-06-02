package edu.examenRecup.controller;

import edu.examenRecup.model.Role;
import edu.examenRecup.model.User;
import edu.examenRecup.util.AppShell;
import edu.examenRecup.util.View;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML private BorderPane mainPane;
    @FXML private Button btnExit;
    @FXML private Label lblWelcome;
    @FXML private StackPane centerContent;
    @FXML private StackPane rightContent;

    private UserController userController;
    private AdminController adminController;
    @FXML
    public void initialize() {
        User sesssionUser = AppShell.getInstance().getSessionUser();
        if (sesssionUser!=null){
            lblWelcome.setText("Welcome" + sesssionUser.getNombre() + "(" + sesssionUser.getRole() + ")");
            AppShell.getInstance().getPrimaryStage().setTitle("User Management");

            loadUserView();

            if (sesssionUser.getRole() == Role.admin) {
                loadAdminView();
                userController.setOnUserSelected(selectedUser -> {
                    adminController.loadUserData(selectedUser);
                });
                adminController.setOnDataChanged(() -> {
                    userController.reloadCards();
                });
            } else {
                rightContent.setVisible(false);
                rightContent.setManaged(false);
            }
        }
    }

    private void loadAdminView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(View.ADMIN.getFxmlPath()));
            Parent adminView = loader.load();
            adminController = loader.getController();
            rightContent.getChildren().setAll(adminView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(View.USER.getFxmlPath()));
            Parent userView = loader.load();
            userController = loader.getController();
            centerContent.getChildren().setAll(userView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }@FXML
    private void handleLogout() {
        AppShell.getInstance().setSessionUser(null);
        AppShell.getInstance().loadView(View.LOGIN);
        AppShell.getInstance().adjustWindow();
    }
}
