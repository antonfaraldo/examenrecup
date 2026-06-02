package edu.examenRecup.controller;

import edu.examenRecup.dao.UserDAO;
import edu.examenRecup.model.Role;
import edu.examenRecup.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class UserController {
    @FXML private FlowPane flowPaneCards;
    private final UserDAO userDAO = new UserDAO();
    private Consumer<User> onUserSelected;
    public void setOnUserSelected(Consumer<User> onUserSelected) {
        this.onUserSelected = onUserSelected;
    }
    @FXML private void initialize() {
        loadCards();
    }
    public void reloadCards() {
        loadCards();
    }

    private void loadCards() {
        flowPaneCards.getChildren().clear();
        List<User> users = userDAO.getAllUsers();

        if (users.isEmpty()) return;

        for (User user : users) {
            VBox card = createCard(user);

            // Tarjeta con color amarillo para usuario con el rol admin
            if (user.getRole() == Role.admin) {
                card.setStyle("-fx-background-color: yellow; -fx-padding: 10; -fx-background-radius: 5;");
            } else {
                card.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10,0,0,0)");
            }
            card.setOnMouseClicked(event -> {
                if (onUserSelected != null) {
                    onUserSelected.accept(user);
                }
            });
            flowPaneCards.getChildren().add(card);
        }
    }

    private VBox createCard(User user) {
        VBox card = new VBox(5);
        Label name = new Label(user.getNombre());
        name.setStyle("-fx-font-weight: bold; -fx-text-fill: lightblue; -fx-font-size: 14px;");

        Label nickname = new Label("@" + user.getNickname());
        Label email = new Label(user.getEmail());
        email.setStyle("-fx-text-fill: #0066cc;");

        Label age = new Label("Edad: " + user.getEdad());
        Label role = new Label("Role: " + user.getRole());
        if (user.getRole() == Role.admin) {
            role.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else {
            role.setStyle("-fx-text-fill: green; fx-font-weight: bold;");
        }
        card.getChildren().addAll(name, nickname, new Separator(), email, age, role);
        return  card;
    }
}
