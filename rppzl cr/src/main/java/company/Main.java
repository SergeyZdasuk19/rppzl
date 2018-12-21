package company;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection con;
    public static Statement stmt;
    public static ResultSet rs;
    public static Statement state;
    public static void main(String[] args) {

            try {
                con = DriverManager.getConnection(url, user, password);
                stmt = con.createStatement();
                if (!con.isClosed()) {
                    System.out.println("Соединение установлено");
                }
            } catch (SQLException e) {
                System.out.println("Не удалось загрузить класс драйвера");
            }
            new Banking();
        }
    }

