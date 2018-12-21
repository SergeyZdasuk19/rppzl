package gui;

import Database.WorkWithDatabase;
import User.User;
import com.devcolibri.database.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Form extends JFrame {
    private JPanel Panelroot;
    private JButton regestrationButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField textFieldSurname;
    private JTextField textFieldName;
    private JLabel LabelSurname;
    private JLabel LabelName;
    private JLabel LabelPatronymic;
    private JTextField textFieldPatronymic;
    private JTextField textFieldDateOfBurn;
    private JLabel LabelDateOfBurn;
    private JLabel LabelPasport;
    private JTextField textFieldPassport;
    private JLabel LabelOut;
    private JTextField textFieldOut;
    private JLabel LabelDate;
    private JTextField textFieldDate;
    private JLabel LabelMesto;
    private JTextField textFieldMesto;
    private JLabel LabelCity;
    private JLabel LabelAdress;
    private JTextField textFieldAdress;
    private JLabel LabelPhone;
    private JTextField textFieldPhone;
    private JLabel LabelErrorSurname;
    private JLabel LabelErrorName;
    private JLabel LabelErrorPatronymic;
    private JLabel LabelErrorDateOfBurn;
    private JLabel LabelErrorPassport;
    private JLabel LabelErrorOut;
    private JLabel LabelErrorDate;
    private JLabel LabelErrorMesto;
    private JLabel LabelErrorCity;
    private JLabel LabelErrorAdress;
    private JLabel LabelErrorPhone;
    private JComboBox comboBoxCity;
    private JButton openButton;
    private JButton resetButton;
    private JComboBox comboBoxForEdit;
    private JButton Reload;
    private JButton depositButton;
    private JButton buttonAccount;
    private JButton buttonCredit;
    private WorkWithDatabase method;
    private User methodUser;
    private JTextField textSurnameOpen;
    private JTextField textNameOpen;
    private JTextField textPatronymicOpen;
    private JTextField textDateOpen;
    private JTextField textPassportOpen;
    private JTextField textPassportOutOpen;
    private JTextField textDateOfDateOpen;
    private JTextField textMestoOpen;
    private JTextField textCityOpen;
    private JTextField textAdresOpen;
    private JTextField textPhoneOpen;
    private DatabaseConnection databaseConnection;
    private FormTable formTable;
    private DepositForm deposit;
    private Account account;
    private CreditForm creditForm;

    public Form() {
        Reload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reload();
            }
        });
        comboBoxForEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addAllInfoFromCombobox((String) comboBoxForEdit.getSelectedItem());
            }
        });
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit = new DepositForm();
                deposit.depositFrame();
            }
        });
        buttonAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                account = new Account();
                account.accountFrame();
            }
        });
        buttonCredit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creditForm = new CreditForm();
                creditForm.creditFrame();
            }
        });
    }

    public void technic() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 560, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(Panelroot);
        frame.dispose();
        frame.setVisible(true);
        frame.setResizable(false);
        regestrationButton.addActionListener(new ButtonActionEventRegestration());
        openButton.addActionListener(new OpenButtonEvent());
        editButton.addActionListener(new EditButtonEvent());
        resetButton.addActionListener(new ResetButtonEvent());
        deleteButton.addActionListener(new DeleteButtonEvent());
    }

    public class ButtonActionEventRegestration implements ActionListener {
        public void actionPerformed(ActionEvent o) {
            method = new WorkWithDatabase();
            methodUser = new User();
            methodUser.setSurname(textFieldSurname.getText());
            methodUser.setName(textFieldName.getText());
            methodUser.setPatronymic(textFieldPatronymic.getText());
            methodUser.setDateOfBurn(textFieldDateOfBurn.getText());
            methodUser.setPassport(textFieldPassport.getText());
            methodUser.setPassportOut(textFieldOut.getText());
            methodUser.setDateOfDate(textFieldDate.getText());
            methodUser.setMestoOfBurn(textFieldMesto.getText());
            methodUser.setCity((String) comboBoxCity.getSelectedItem());
            methodUser.setAdress(textFieldAdress.getText());
            methodUser.setPhone(textFieldPhone.getText());
            method.setValueUser(methodUser);
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,имя")) {
                LabelErrorName.setText("Error");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,фамилия")) {
                LabelErrorSurname.setText("Error");
                LabelErrorName.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,паспорт")) {
                LabelErrorPassport.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,отчество")) {
                LabelErrorPatronymic.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,дата_рождения")) {
                LabelErrorDateOfBurn.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,выдача")) {
                LabelErrorOut.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,дата_выдачи")) {
                LabelErrorDate.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,место")) {
                LabelErrorMesto.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,город")) {
                LabelErrorCity.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,адрес")) {
                LabelErrorAdress.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorPhone.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()).equals("Ошибка,телефон")) {
                LabelErrorPhone.setText("Error");
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
            }
            if (method.output(methodUser.getName(), methodUser.getSurname(), methodUser.getPassport(), methodUser.getPatronymic(), methodUser.getDateOfBurn(), methodUser.getPassportOut(), methodUser.getDateOfDate(), methodUser.getMestoOfBurn(), methodUser.getCity(), methodUser.getAdress(), methodUser.getPhone()) == "ОК") {
                LabelErrorName.setText("");
                LabelErrorSurname.setText("");
                LabelErrorPassport.setText("");
                LabelErrorPatronymic.setText("");
                LabelErrorDateOfBurn.setText("");
                LabelErrorOut.setText("");
                LabelErrorDate.setText("");
                LabelErrorMesto.setText("");
                LabelErrorCity.setText("");
                LabelErrorAdress.setText("");
                LabelErrorPhone.setText("");
                method.inputClient();
                JOptionPane.showMessageDialog(null, "Successfull add");
            }


        }
    }

    public void reload() {
        databaseConnection = new DatabaseConnection();
        comboBoxForEdit.removeAllItems();
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("SELECT Surname from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String surname = resultSet.getString("Surname");
                comboBoxForEdit.addItem(surname);
            }
            JOptionPane.showMessageDialog(null, "Successfull reload");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAllInfoFromCombobox(String surname) {
        databaseConnection = new DatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("SELECT Name,Surname,Patronymic,DateOfBurn,Passport,PassportOut,DateOfOut,Mesto,City,Adress,Phone from users where Surname='" + surname + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                textFieldName.setText(resultSet.getString("Name"));
                textFieldSurname.setText(resultSet.getString("Surname"));
                textFieldPatronymic.setText(resultSet.getString("Patronymic"));
                textFieldDateOfBurn.setText(resultSet.getString("DateOfBurn"));
                textFieldPassport.setText(resultSet.getString("Passport"));
                textFieldOut.setText(resultSet.getString("PassportOut"));
                textFieldDate.setText(resultSet.getString("DateOfOut"));
                textFieldMesto.setText(resultSet.getString("Mesto"));
                String combobox = resultSet.getString("City");
                comboBoxCity.removeAllItems();
                comboBoxCity.addItem(combobox);
                textFieldAdress.setText(resultSet.getString("Adress"));
                textFieldPhone.setText(resultSet.getString("Phone"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class OpenButtonEvent implements ActionListener {
        public void actionPerformed(ActionEvent ob) {
            formTable = new FormTable();
            try {
                formTable.secondFrame();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public class EditButtonEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            method = new WorkWithDatabase();
            methodUser = new User();
            methodUser.setSurname(textFieldSurname.getText());
            methodUser.setName(textFieldName.getText());
            methodUser.setPatronymic(textFieldPatronymic.getText());
            methodUser.setDateOfBurn(textFieldDateOfBurn.getText());
            methodUser.setPassport(textFieldPassport.getText());
            methodUser.setPassportOut(textFieldOut.getText());
            methodUser.setDateOfDate(textFieldDate.getText());
            methodUser.setMestoOfBurn(textFieldMesto.getText());
            methodUser.setCity((String) comboBoxCity.getSelectedItem());
            methodUser.setAdress(textFieldAdress.getText());
            methodUser.setPhone(textFieldPhone.getText());
            method.setValueUser(methodUser);
            method.edit();
        }
    }

    public class ResetButtonEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            method = new WorkWithDatabase();
            methodUser = new User();
            textFieldName.setText("");
            textFieldSurname.setText("");
            textFieldPatronymic.setText("");
            textFieldDateOfBurn.setText("");
            textFieldPassport.setText("");
            textFieldOut.setText("");
            textFieldDate.setText("");
            textFieldMesto.setText("");
            textFieldAdress.setText("");
            textFieldPhone.setText("");
            JOptionPane.showMessageDialog(null, "Successfull reset");
        }
    }

    public class DeleteButtonEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            method = new WorkWithDatabase();
            methodUser = new User();
            if (textFieldPassport.getText().length() != 0) {
                method.delete(textFieldPassport.getText());
                JOptionPane.showMessageDialog(null, "Successfull delete");
            }

        }
    }
}

