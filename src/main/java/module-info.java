module edu.examenRecup {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens edu.examenRecup to javafx.fxml;
    opens edu.examenRecup.controller to javafx.fxml;
    opens edu.examenRecup.model to javafx.base;

    exports edu.examenRecup;
    exports edu.examenRecup.controller;
    exports edu.examenRecup.model;
    exports edu.examenRecup.dao;
}
