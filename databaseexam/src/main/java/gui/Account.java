package gui;

import Database.WorkWithDatabase;
import Main.Main;
import com.devcolibri.database.DatabaseConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private JPanel PanelRoot;
    private JTextField textFieldNumber;
    private JTextField textFieldCode;
    private JComboBox comboBoxSurnameForDeposit;
    private JTextField textFieldBalance;
    private JLabel labelNumber;
    private JLabel labelCode;
    private JLabel labelFIOForDeposit;
    private JLabel labelBalance;
    private JButton buttonCount;
    private JButton buttinReset;
    private JLabel labelBalanceCredit;
    private JTextField textFieldBalanceCredit;
    private JButton buttonPayCredit;
    private JLabel labelSurnameCredit;
    private JComboBox comboBoxSurnameForCredit;
    private JTextField textFieldPayCredit;
    private DatabaseConnection databaseConnection;

    public Account() {
        buttonCount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                countDeposit();
                countCredit();
            }
        });
        buttinReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        comboBoxSurnameForDeposit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addFromComboForDeposit((String) comboBoxSurnameForDeposit.getSelectedItem());
            }
        });
        buttonPayCredit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payCredit();
            }
        });

        comboBoxSurnameForCredit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                addFromComboForCredit((String) comboBoxSurnameForCredit.getSelectedItem());
            }
        });
    }

    public void accountFrame() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 560, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(PanelRoot);
        frame.dispose();
        frame.setVisible(true);
        frame.setResizable(false);
        addToBox();
    }

    public void addToBox() {
        databaseConnection = new DatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("SELECT FIO from deposit");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String surnameDeposit = resultSet.getString("FIO");
                comboBoxSurnameForDeposit.addItem(surnameDeposit);
            }

            PreparedStatement preparedStatement1 = databaseConnection.con.prepareStatement("SELECT FIOCredit from credit");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                String surnameCredit = resultSet1.getString("FIOCredit");
                comboBoxSurnameForCredit.addItem(surnameCredit);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFromComboForDeposit(String surnameD) {
        databaseConnection = new DatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("SELECT number_score,code_score from deposit where FIO='" + surnameD + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                textFieldNumber.setText(resultSet.getString("number_score"));
                textFieldCode.setText(resultSet.getString("code_score"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addFromComboForCredit(String surnameC) {
        try {
            PreparedStatement preparedStatementCredit = databaseConnection.con.prepareStatement("SELECT number_score,code_score from credit where FIOCredit='" + surnameC + "'");
            ResultSet resultSetCredit = preparedStatementCredit.executeQuery();
            while (resultSetCredit.next()) {
                textFieldNumber.setText(resultSetCredit.getString("number_score"));
                textFieldCode.setText(resultSetCredit.getString("code_score"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        textFieldNumber.setText("");
        textFieldCode.setText("");
        textFieldBalance.setText("");
        textFieldPayCredit.setText("");
        textFieldBalanceCredit.setText("");
    }

    public void countDeposit() {
        databaseConnection = new DatabaseConnection();
        WorkWithDatabase workWithDatabase = new WorkWithDatabase();
        double suma, percent, result, K;
        suma = percent = result = 0;
        int daysInForm = 0;
        int daysInYear = 365;
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("SELECT suma,score,percent from deposit where FIO='" + comboBoxSurnameForDeposit.getSelectedItem() + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                suma = Double.parseDouble(resultSet.getString("suma"));
                daysInForm = Integer.parseInt(resultSet.getString("score"));
                percent = Double.parseDouble(resultSet.getString("percent"));
            }
            result = suma + (suma * (percent / 100) * daysInForm * 30) / daysInYear;
            System.out.println(suma * (percent / 100));
            workWithDatabase.updateDeposit(result, (String) comboBoxSurnameForDeposit.getSelectedItem());
            textFieldBalance.setText((String.valueOf(result)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void countCredit() {
        databaseConnection = new DatabaseConnection();
        WorkWithDatabase workWithDatabase = new WorkWithDatabase();
        double suma, percent, result, K, balance;
        suma = percent = result = 0;
        int daysInForm = 0;
        int daysInYear = 365;
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("SELECT suma,score,percent,balance from credit where FIOCredit='" + comboBoxSurnameForCredit.getSelectedItem() + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                balance = resultSet.getDouble("balance");
                if (String.valueOf(balance).equals("")) {
                    suma = Double.parseDouble(resultSet.getString("suma"));
                    daysInForm = Integer.parseInt(resultSet.getString("score"));
                    percent = Double.parseDouble(resultSet.getString("percent"));
                    K = (percent / 12 * (Math.pow(1 + percent / 12, daysInForm))) / ((Math.pow(1 + percent / 12, daysInForm)) - 1);
                    result = suma * K;
                    workWithDatabase.updateCredit(result, (String) comboBoxSurnameForCredit.getSelectedItem());
                    textFieldBalanceCredit.setText((String.valueOf(result)));
                }
                else{
                    textFieldBalanceCredit.setText((String.valueOf(balance)));
                }
                }

            } catch(SQLException e){
                e.printStackTrace();
            }
        }

        public void payCredit () {
            databaseConnection = new DatabaseConnection();
            try {
                Double result;
                Double suma = 0.0;
                Double payForGet = Double.parseDouble(textFieldPayCredit.getText());
                System.out.println(payForGet);
                PreparedStatement preparedStatement2 = databaseConnection.con.prepareStatement("SELECT balance from credit where FIOCredit = '" + comboBoxSurnameForCredit.getSelectedItem() + "'");
                ResultSet resultSet = preparedStatement2.executeQuery();
                while (resultSet.next()) {
                    suma = resultSet.getDouble("balance");
                    System.out.println(suma);
                }
                result = suma - payForGet;
                PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("UPDATE credit set balance=? where FIOCredit=?");
                preparedStatement.setDouble(1, result);
                preparedStatement.setString(2, (String) comboBoxSurnameForCredit.getSelectedItem());
                preparedStatement.executeUpdate();
                System.out.println(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
