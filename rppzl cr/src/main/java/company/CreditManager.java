package company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditManager extends JFrame {

    private JLabel num = new JLabel("Номер счета");
    private JTextField numtext = new JTextField(5);
    private JLabel typecr = new JLabel("Тип кредита");
    private JTextField typecrtext = new JTextField(5);
    private JLabel val = new JLabel("Валюта");
    private JTextField valtext = new JTextField(5);
    private JLabel time = new JLabel("Срок");
    private JTextField timetext = new JTextField(5);
    private JLabel finances = new JLabel("Средства");
    private JTextField financestext = new JTextField(5);
    private JLabel perc = new JLabel("Процент");
    private JTextField perctext = new JTextField(5);
    private JLabel endperiod = new JLabel("Долг по окончанию срока");
    private JTextField endperiodtext = new JTextField(5);
    private JLabel forcredit = new JLabel("Сумма к погашению");
    private JTextField forcredittext = new JTextField(5);
    private JLabel ost = new JLabel("Остаток");
    private JTextField osttext = new JTextField(5);

    private JButton inf = new JButton("Информация");
    private JButton pay = new JButton("Погасить");

    public CreditManager() {
        super("Кредитная история");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setBounds(460, 250, 320, 350);
        this.setLayout(new GridLayout(10, 2));
        this.add(num);
        this.add(numtext);
        this.add(typecr);
        this.add(typecrtext);
        this.add(val);
        this.add(valtext);
        this.add(time);
        this.add(timetext);
        this.add(finances);
        this.add(financestext);
        this.add(perc);
        this.add(perctext);
        this.add(endperiod);
        this.add(endperiodtext);
        this.add(forcredit);
        this.add(forcredittext);
        this.add(ost);
        this.add(osttext);

        this.add(inf);
        inf.addActionListener(new CreditInfo());
        this.add(pay); pay.addActionListener(new Payment());

        this.setVisible(true);
    }

    class CreditInfo implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String jq = "SELECT * FROM credits WHERE numberdoc='" + numtext.getText() + "'";
            try {
                ResultSet resultSet = company.Main.state.executeQuery(jq);

                if (resultSet.next()) {
                    System.out.println("ok");
                }
                typecrtext.setText(resultSet.getString("typecredit"));
                valtext.setText(resultSet.getString("currency"));
                timetext.setText(resultSet.getString("period"));
                financestext.setText(resultSet.getString("money"));
                perctext.setText(resultSet.getString("percent"));
                forcredittext.setText("");
                osttext.setText("");

                int mon = Integer.parseInt(financestext.getText());
                int per = Integer.parseInt(perctext.getText());
                System.out.println(per);
                System.out.println(mon);
                int allcountp = (mon/10) * per/2;
                int allcount = mon + allcountp;
                System.out.println(allcount);

                endperiodtext.setText(String.valueOf(allcount));

            } catch (SQLException er) {
            }
        }

    }


    class Payment implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            int needtopay = Integer.parseInt(financestext.getText());
            int wepay = Integer.parseInt(forcredittext.getText());

            int countdata = needtopay-wepay;

            osttext.setText(String.valueOf(countdata));

                try {
                    PreparedStatement preparedStatement;
                    preparedStatement = (PreparedStatement) company.Main.con.prepareStatement("UPDATE credits set money=? where numberdoc='" + numtext.getText() + "'");
                    preparedStatement.setString(1, String.valueOf(countdata));
                    preparedStatement.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
        }
    }


}
