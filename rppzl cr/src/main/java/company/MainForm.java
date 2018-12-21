package company;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainForm extends JFrame {

    private JLabel FIO = new JLabel(" ФИО");
    private JTextField TextFio= new JTextField(5);

    private JLabel deposit = new JLabel(" Вид депозита");
    private JTextField Textdeposit = new JTextField(5);

    private JLabel currency = new JLabel(" Вид валюты");
    private JTextField Textcur = new JTextField(5);

    private JLabel period = new JLabel(" Срок действия");
    private JTextField TextPeriod = new JTextField(5);

    private JLabel money = new JLabel(" Сумма вклада");
    private JTextField Textmoney = new JTextField(5);

    private JLabel percent = new JLabel(" Процент по вкладу");
    private JTextField Textpercent = new JTextField(5);

    private JLabel checknumber = new JLabel(" Номер счета");
    private JTextField Textnumber = new JTextField(5);

    private JLabel code = new JLabel(" Код счета");
    private JTextField Textcode = new JTextField(5);

    private JButton bsubmit = new JButton("Отправить");
    private JButton breset = new JButton("Сбросить");

    public MainForm(){
        super("Заключение договора");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(460,250, 400, 380);

        Container cont=this.getContentPane();
        cont.setLayout(new GridLayout(9,2));

        cont.add(FIO);
        cont.add(TextFio);

        cont.add(deposit);
        cont.add(Textdeposit);

        cont.add(currency);
        cont.add(Textcur);

        cont.add(period);
        cont.add(TextPeriod);

        cont.add(money);
        cont.add(Textmoney);

        cont.add(percent);
        cont.add(Textpercent);

        cont.add(checknumber);
        cont.add(Textnumber);

        cont.add(code);
        cont.add(Textcode);

        cont.add(bsubmit); bsubmit.addActionListener(new SubmitListener());
        cont.add(breset); breset.addActionListener(new ResetListener());

        this.setVisible(true);
    }

    class SubmitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String error="ERROR"; int n=0;
        String num=checknumber.getText(); String num1=code.getText();
        try{
           int per=Integer.parseInt(TextPeriod.getText());
           int per1=Integer.parseInt(Textpercent.getText());
           //int per2=Integer.parseInt(Textnumber.getText());
           int per3=Integer.parseInt(Textcode.getText());

           if(per<0 || per>25){
               showMessageDialog(null, "Неверно указан период"); n++;
           }
           if (per1<0){
               showMessageDialog(null, "Неверно указана процентная ставка"); n++;
           }
           if (num.length()<12){
               showMessageDialog(null, "Неверно указан номер счета", error, JOptionPane.WARNING_MESSAGE);n++;
           }
           if(num1.length()<4){
               showMessageDialog(null, "Неверно указан год", error, JOptionPane.WARNING_MESSAGE);n++;
           }

        }
        catch (NumberFormatException ex){ex.printStackTrace(); showMessageDialog(null, error, "Error", ERROR_MESSAGE); n++;}

            if(n==0){indata();}
            else{showMessageDialog(null, "Заполните форму верно");}

        }

        public void indata(){

            try (Statement statement = Main.con.createStatement()) {
                PreparedStatement preparedStatement = (PreparedStatement) statement.getConnection().prepareStatement("INSERT into checks (fio, deposit, currency, period, money, percent, checknumber, code) values (?,?,?,?,?,?,?,?)");
                preparedStatement.setString(1, TextFio.getText());
                preparedStatement.setString(2, Textdeposit.getText());
                preparedStatement.setString(3, Textcur.getText());
                preparedStatement.setString(4, TextPeriod.getText());
                preparedStatement.setString(5, Textmoney.getText());
                preparedStatement.setString(6, Textpercent.getText());
                preparedStatement.setString(7, Textnumber.getText());
                preparedStatement.setString(8, Textcode.getText());
                preparedStatement.execute();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
           //new UserPanel();
        }

    }


    class ResetListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          TextFio.setText("");
          Textdeposit.setText("");
          Textmoney.setText("");
          Textcode.setText("");
          Textnumber.setText("");
          Textcur.setText("");
          Textpercent.setText("");
          TextPeriod.setText("");
        }
    }

}
