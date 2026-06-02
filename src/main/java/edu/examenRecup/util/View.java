package edu.examenRecup.util;

public enum View {
    MAIN("/fxml/main_view.fxml"),
    LOGIN("/fxml/login_view.fxml"),
    USER("/fxml/users_view.fxml"),
    ADMIN("/fxml/admin_view.fxml");

    private final String fxmlPath;

    View(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }
    public String getFxmlPath() {
        return fxmlPath;
    }

}
