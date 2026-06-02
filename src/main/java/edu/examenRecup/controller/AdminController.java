package edu.examenRecup.controller;

import edu.examenRecup.dao.UserDAO;
import edu.examenRecup.model.Role;
import edu.examenRecup.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminController {
    @FXML private TextField txtID;
    @FXML private TextField txtName;
    @FXML private TextField txtNickname;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAge;
    @FXML private PasswordField txtPassword;
    @FXML private ComboBox<Role> cmbRol;
    @FXML private Label lblError;
    @FXML private Button btnCrear;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;
    @FXML private Button btnClear;

    private final UserDAO userDAO = new UserDAO();

    private Runnable onDataChanged;
    public void setOnDataChanged(Runnable onDataChanged) {
        this.onDataChanged = onDataChanged;
    }
    @FXML private void initialize() {
        txtID.setEditable(false);
        cmbRol.getItems().setAll(Role.values());

        btnCrear.setOnAction(event -> handleAdd());
        btnClear.setOnAction(event -> clearForm());
        btnModificar.setOnAction(event -> handleUpdate());
        btnEliminar.setOnAction(event -> handleDelete());
    }

    private void clearForm() {
        txtID.clear();
        txtName.clear();
        txtNickname.clear();
        txtEmail.clear();
        txtAge.clear();
        txtPassword.clear();
        cmbRol.setValue(null);
        lblError.setText("");
    }

    private void handleUpdate() {
        if (!validateForm()) return;

        if (txtID.getText().isEmpty()) {
            showError("Ningun usuario seleccionado");
            return;
        }
        User updatedUser = new User(Integer.parseInt(txtID.getText()), txtName.getText(), txtNickname.getText(), txtEmail.getText(), Integer.parseInt(txtAge.getText()), txtPassword.getText(), cmbRol.getValue());

        if (userDAO.updateUser(updatedUser)) {
            showSuccess("Usuario actualizado correctamente");
            clearForm();
            notifyDataChanged();
        } else {
            showError("Error al actualizar usuario");
        }
    }

    private boolean validateForm() {
        if (txtName.getText().trim().isEmpty() || txtNickname.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty() || txtAge.getText().trim().isEmpty() || txtPassword.getText().trim().isEmpty()|| cmbRol.getValue() == null) {
            lblError.setText("Por favor informe todos los campos");
            return false;
        }
        try {
            Integer.parseInt(txtAge.getText());
        } catch (NumberFormatException e) {
            lblError.setText("La edad tiene que ser un valor numerico entero");
            return false;
        }
        return true;
    }

    private void handleDelete() {
        if (txtID.getText().isEmpty()) {
            showError("Ningun usuario seleccionado para eliminar");
            return;
        }
        int id =  Integer.parseInt(txtID.getText());
        if (userDAO.deleteUser(id)) {
            showSuccess("Usuario eliminado correctamente");
            clearForm();
            notifyDataChanged();
        } else {
            showError("Fallo al eliminar");
        }
    }

    private void notifyDataChanged() {
        if (onDataChanged != null) {
            onDataChanged.run();
        }
    }

    private void showSuccess(String msg) {
        lblError.setText(msg);
        lblError.setStyle("-fx-text-fill: green;");
    }

    private void showError(String msg) {
        lblError.setText(msg);
        lblError.setStyle("-fx-text-fill: red;");
    }

    private void handleAdd() {
        if (!validateForm()) return;
        User newUser = new User(0, txtName.getText(), txtNickname.getText(), txtEmail.getText(), Integer.parseInt(txtAge.getText()), txtPassword.getText(), cmbRol.getValue());

        if (userDAO.createUser(newUser)) {
            lblError.setStyle("-fx-text-fill: green;");
            lblError.setText("Usuario agregado correctamente");
            clearForm();
            notifyDataChanged();
        } else {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Error al agregar usuario");
        }
    }
    public void loadUserData(User user) {
        txtID.setText(String.valueOf(user.getId()));
        txtName.setText(user.getNombre());
        txtNickname.setText(user.getNickname());
        txtEmail.setText(user.getEmail());
        txtAge.setText(String.valueOf(user.getEdad()));
        txtPassword.setText(user.getPassword());
        cmbRol.setValue(user.getRole());
    }
}
