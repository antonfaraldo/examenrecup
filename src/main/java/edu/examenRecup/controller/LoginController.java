package edu.examenRecup.controller;

import edu.examenRecup.dao.UserDAO;
import edu.examenRecup.model.User;
import edu.examenRecup.util.AppShell;
import edu.examenRecup.util.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin() {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            lblError.setText("Rellena los campos vacios");
            return;
        }
        User user = userDAO.authenticate(email, password);
        if (user != null) {
            AppShell.getInstance().setSessionUser(user);
            AppShell.getInstance().loadView(View.MAIN);
            AppShell.getInstance().adjustWindow();
        } else {
            lblError.setText("Credenciales incorrectas");
        }
    }
}
