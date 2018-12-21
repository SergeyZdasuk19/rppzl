package gui;

import Credit.Credit;
import Database.WorkWithDatabase;
import Deposit.Deposit;
import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditForm {
    private JPanel PanelRoot;
    private JLabel labelFIO;
    private JTextField textFieldFIO;
    private JLabel labelCredit;
    private JLabel labelCurrency;
    private JLabel labelTerm;
    private JLabel labelsum;
    private JLabel labelPercent;
    private JLabel labelNumberScore;
    private JLabel labelCodeScore;
    private JTextField textFieldTerm;
    private JTextField textFieldSum;
    private JTextField textFieldPercent;
    private JTextField textFieldNumberScore;
    private JTextField textFieldCodeScore;
    private JComboBox comboBoxCurrency;
    private JComboBox comboBoxCredit;
    private JButton buttonSend;
    private JButton buttonReset;

    public CreditForm() {
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

    public void creditFrame(){
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 560, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(PanelRoot);
        frame.dispose();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void init(){
        Credit credit = new Credit();
        User user = new User();
        WorkWithDatabase workWithDatabase = new WorkWithDatabase();
        user.setSurname(textFieldFIO.getText());
        credit.setCredit((String) comboBoxCredit.getSelectedItem());
        credit.setCurrency((String) comboBoxCurrency.getSelectedItem());
        credit.setScore(textFieldTerm.getText());
        credit.setSum(textFieldSum.getText());
        credit.setPersent(textFieldPercent.getText());
        credit.setNumberScore(textFieldNumberScore.getText());
        credit.setCodeScore(textFieldCodeScore.getText());
        workWithDatabase.setValueUser(user);
        workWithDatabase.setValueCredit(credit);
        String result = check();
        if(result.equals("true")) {
            workWithDatabase.inputCredit();
        }
    }

    public String check(){
        String number ="[0-9]+";
        String letter ="[а-я]+";
        if(textFieldFIO.getText().matches(number)|| textFieldFIO.getText().length()==0){
            System.out.println("Фамилия");
            return "false";
        }
        if(textFieldTerm.getText().matches(letter)|| textFieldTerm.getText().length()==0){
            return "false";
        }
        if(textFieldSum.getText().matches(letter)|| textFieldSum.getText().length()==0){
            return "false";
        }
        if(textFieldPercent.getText().matches(letter)|| textFieldPercent.getText().length()==0){
            return "false";
        }
        if(textFieldNumberScore.getText().matches(letter)|| textFieldNumberScore.getText().length()==0 || textFieldNumberScore.getText().length()!=13){
            return "false";
        }
        if(textFieldCodeScore.getText().matches(letter)|| textFieldCodeScore.getText().length()==0|| textFieldCodeScore.getText().length()!=4){
            return "false";
        }
        return "true";
    }

    public void reset() {
        textFieldFIO.setText("");
        textFieldTerm.setText("");
        textFieldSum.setText("");
        textFieldPercent.setText("");
        textFieldNumberScore.setText("");
        textFieldCodeScore.setText("");
    }
}
