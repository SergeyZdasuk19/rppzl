package gui;

import Database.WorkWithDatabase;
import Deposit.Deposit;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositForm {
    private JPanel PanelRoot;
    private JTextField textFieldFIO;
    private JTextField textFieldDeposit;
    private JTextField textFieldTermDeposit;
    private JTextField textFieldSumDeposit;
    private JTextField textFieldPercentDeposit;
    private JTextField textFieldNumberScoreDeposit;
    private JTextField textFieldCodeScoreDeposit;
    private JLabel labelFIO;
    private JLabel labelDeposit;
    private JLabel labelCurrency;
    private JComboBox comboBoxCurrencyDeposit;
    private JLabel labelTerm;
    private JLabel labelsum;
    private JLabel labelPercent;
    private JLabel labelNumberScore;
    private JLabel labelCodeScore;
    private JButton buttonSend;
    private JButton buttonReset;
    private JComboBox comboBoxDeposit;

    public DepositForm() {
        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WorkWithDatabase workWithDatabase = new WorkWithDatabase();
                init();

            }
        });
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
    }

    public void depositFrame() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 560, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(PanelRoot);
        frame.dispose();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void init() {
        Deposit deposit = new Deposit();
        User user = new User();
        WorkWithDatabase workWithDatabase = new WorkWithDatabase();
        user.setSurname(textFieldFIO.getText());
        deposit.setDeposit((String) comboBoxDeposit.getSelectedItem());
        deposit.setCurrency((String) comboBoxCurrencyDeposit.getSelectedItem());
        deposit.setScore(textFieldTermDeposit.getText());
        deposit.setSum(textFieldSumDeposit.getText());
        deposit.setPersent(textFieldPercentDeposit.getText());
        deposit.setNumberScore(textFieldNumberScoreDeposit.getText());
        deposit.setCodeScore(textFieldCodeScoreDeposit.getText());
        workWithDatabase.setValueUser(user);
        workWithDatabase.setValueDeposit(deposit);
        String result = check();
        if(result.equals("true")) {
            workWithDatabase.inputDeposit();
        }

    }

    public void reset() {
        textFieldFIO.setText("");
        textFieldTermDeposit.setText("");
        textFieldSumDeposit.setText("");
        textFieldPercentDeposit.setText("");
        textFieldNumberScoreDeposit.setText("");
        textFieldCodeScoreDeposit.setText("");
    }

    public String check(){
        String number ="[0-9]+";
        String letter ="[а-я]+";
        if(textFieldFIO.getText().matches(number)|| textFieldFIO.getText().length()==0){
            System.out.println("Фамилия");
            return "false";
        }
        if(textFieldTermDeposit.getText().matches(letter)|| textFieldTermDeposit.getText().length()==0){
            return "false";
        }
        if(textFieldSumDeposit.getText().matches(letter)|| textFieldSumDeposit.getText().length()==0){
            return "false";
        }
        if(textFieldPercentDeposit.getText().matches(letter)|| textFieldPercentDeposit.getText().length()==0){
            return "false";
        }
        if(textFieldNumberScoreDeposit.getText().matches(letter)|| textFieldNumberScoreDeposit.getText().length()==0 || textFieldNumberScoreDeposit.getText().length()!=13){
            return "false";
        }
        if(textFieldCodeScoreDeposit.getText().matches(letter)|| textFieldCodeScoreDeposit.getText().length()==0|| textFieldCodeScoreDeposit.getText().length()!=4){
            return "false";
        }
        return "true";
    }
}
