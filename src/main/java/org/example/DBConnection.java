package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:h2:~/testdb";
    private static final String USER = "sa";
    private static final String PASS = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("H2 Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("H2 Driver not found!");
            e.printStackTrace();
        }
    }

    // Lấy Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
