package com.devcolibri.database;

import java.sql.*;

import gui.*;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;

    public void connect() {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            if (!con.isClosed()) {
                System.out.println("Соединение установлено");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить класс драйвера");
        }
    }
}
