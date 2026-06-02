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
    @FXML private ComboBox<Role> cmbRole;
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
        cmbRole.getItems().setAll(Role.values());

        btnCrear.setOnAction(event -> handleAdd());
        btnClear.setOnAction(event -> clearForm());
        btnModificar.setOnAction(event -> handleUpdate());
        btnEliminar.setOnAction(event -> handleDelete());
    }

    private void clearForm() {
    }

    private void handleUpdate() {
        if (!validateForm()) return;

        if (txtID.getText().isEmpty()) {
            showError("Ningun usuario seleccionado");
            return;
        }
        User updatedUser = new User(Integer.parseInt(txtID.getText()), txtName.getText(), txtNickname.getText(), txtEmail.getText(), Integer.parseInt(txtAge)));
    }

    private boolean validateForm() {
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

    private void showSuccess(String usuarioEliminadoCorrectamente) {
    }

    private void showError(String ningunUsuarioSeleccionadoParaEliminar) {
    }

    private void handleAdd() {
    }
}
