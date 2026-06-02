package edu.examenRecup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/examenusuarios";
    private static final String USER = "root"; // Cambia si tu usuario es diferente
    private static final String PASSWORD = ""; // Cambia si tu contraseña es diferente

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
