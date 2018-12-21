package gui;

import com.devcolibri.database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormTable {
    private JPanel PanelRoot;
    private JTable table;
    private DatabaseConnection databaseConnection;

    public void secondFrame() throws SQLException {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 560, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(PanelRoot);
        frame.dispose();
        frame.setVisible(true);
        frame.setResizable(false);
        createUIComponents();
    }


    private void createUIComponents() throws SQLException {
        databaseConnection = new DatabaseConnection();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Имя");
        model.addColumn("Фамилия");
        model.addColumn("Отчество");
        model.addColumn("Дата рождения");
        model.addColumn("Паспорт");
        model.addColumn("Кем выдан");
        model.addColumn("Дата выдачи");
        model.addColumn("Место рождения");
        model.addColumn("Город проживания");
        model.addColumn("Адрес");
        model.addColumn("Телефон");
        table = new JTable(model);
        model.addRow(new Object[]{"Имя", "Фамилия", "Отчество", "Дата рождения", "Паспорт", "Кем выдан", "Дата выдачи", "Место рождения", "Город проживания", "Адрес", "Телефон"});
        PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("select Name,Surname,Patronymic,DateOfBurn,Passport,PassportOut,DateOfOut,Mesto,City,Adress,Phone from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("Surname");
            String patronymic = resultSet.getString("Patronymic");
            String dateOfBurn = resultSet.getString("DateOfBurn");
            String passport = resultSet.getString("Passport");
            String passportOut = resultSet.getString("PassportOut");
            String dateOfOut = resultSet.getString("DateOfOut");
            String mesto = resultSet.getString("Mesto");
            String city = resultSet.getString("City");
            String adress = resultSet.getString("Adress");
            String phone = resultSet.getString("Phone");
            model.addRow(new Object[]{name, surname, patronymic, dateOfBurn, passport, passportOut, dateOfOut, mesto, city, adress, phone});
        }

    }

}
