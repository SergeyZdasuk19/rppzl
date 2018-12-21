package company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.JOptionPane.*;

public class CreditForm extends JFrame {

    private JLabel surname = new JLabel("ФИО");
    private JTextField surnametext = new JTextField(5);
    private JLabel typecredit = new JLabel("Вид кредита");
    private JTextField typecredittext = new JTextField(5);
    private JLabel numberdoc = new JLabel("Номер договора");
    private JTextField numberdoctext = new JTextField(5);
    private JLabel currency = new JLabel("Вид валюты");
    private JTextField currencytext = new JTextField(5);
    private JLabel period = new JLabel("Срок договора");
    private JTextField periodtext = new JTextField(5);
    private JLabel quan = new JLabel("Сумма кредита");
    private JTextField quantext = new JTextField(5);
    private JLabel percent = new JLabel("Процент по кредиту");
    private JTextField percenttext = new JTextField(5);

    private JButton subm = new JButton("Подтвердить");
    private JButton reset = new JButton("Сбросить");

   public CreditForm(){

        this.setBounds(100, 100, 380, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(8,2));

        this.add(surname);
        this.add(surnametext);
        this.add(typecredit);
        this.add(typecredittext);
        this.add(numberdoc);
        this.add(numberdoctext);
        numberdoctext.setToolTipText("Данное поле заполнится автоматически");
        this.add(currency);
        this.add(currencytext);
        this.add(period);
        this.add(periodtext);
        this.add(quan);
        this.add(quantext);
        this.add(percent);
        this.add(percenttext);
        this.add(subm); subm.addActionListener(new Credit());
        this.add(reset); reset.addActionListener(new Resett());

        this.setVisible(true);
    }

    class Credit implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            int l=0;
            String sqljquery="select checknumber from checks where fio='" + surnametext.getText() + "'";
            try {
                ResultSet RS = Main.state.executeQuery(sqljquery);
                if (RS.next()){
                    System.out.println("OK");
                }
                numberdoctext.setText(RS.getString("checknumber"));
                System.out.println(numberdoctext.getText());
            } catch (SQLException Exp) {
                Exp.printStackTrace();
            }

            try{
                if(surnametext.getText().length()==0){
                    l++;
                }if(typecredittext.getText().length()==0){
                    l++;
                }if(numberdoctext.getText().length()==0){
                    l++;
                }if(currencytext.getText().length()==0){
                    l++;
                }if(percenttext.getText().length()==0){
                    l++;
                }if(periodtext.getText().length()==0){
                    l++;
                }
                //int type = Integer.parseInt(typecredittext.getText());
               // int cur = Integer.parseInt(currencytext.getText());
                int time = Integer.parseInt(periodtext.getText());
                int money = Integer.parseInt(quantext.getText());
                int pers = Integer.parseInt(percenttext.getText());

                System.out.println(l);
                if(time<0 || time>10) {showMessageDialog(null, "Такой срок кредита недоступен"); l++;}
                if(money<=0 || money>1000000){showMessageDialog(null, "Такая сумма для кредита недоступна"); l++;}
                if (pers<5 || pers>40){showMessageDialog(null, "Такой процент по кредиту недоступен"); l++;}

            }catch (NumberFormatException t){t.printStackTrace();}
            if (l==0){
                try {
                    PreparedStatement PS = (PreparedStatement) Main.con.prepareStatement("INSERT INTO credits(typecredit, numberdoc, currency, period, money, percent) values (?,?,?,?,?,?)");
                    PS.setString(1, typecredittext.getText());
                    PS.setString(2, numberdoctext.getText());
                    PS.setString(3, currencytext.getText());
                    PS.setString(4, periodtext.getText());
                    PS.setString(5, quantext.getText());
                    PS.setString(6, percenttext.getText());
                    PS.execute();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

    class Resett implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            typecredittext.setText("");
            surnametext.setText("");
            currencytext.setText("");
            percenttext.setText("");
            periodtext.setText("");
            numberdoctext.setText("");
            quantext.setText("");
        }
    }

}
