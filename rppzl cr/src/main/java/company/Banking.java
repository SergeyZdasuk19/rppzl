package company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.JOptionPane.showMessageDialog;

public class Banking extends JFrame {

    private JLabel forcode = new JLabel("Фамилия");
    private JTextField forcodetext = new JTextField(5);
    private JLabel acc = new JLabel("Доступно");
    private JTextField acctext = new JTextField(5);
    private JLabel getmoney = new JLabel("Снять");
    private JTextField getmoneytext = new JTextField(5);

    private JButton show = new JButton("Вывод");
    private JButton get = new JButton("Снять");

    public Banking() {
        super("Банкомат");
        this.setBounds(100, 100, 380, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(4, 2));

        this.add(forcode);
        this.add(forcodetext);
        this.add(acc);
        this.add(acctext);
        this.add(getmoney);
        this.add(getmoneytext);
        this.add(show);
        show.addActionListener(new Show());
        this.add(get);
        get.addActionListener(new GetMoney());
        this.setVisible(true);
    }

    class Show implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //   System.out.println(forcodetext.getText());



            try {

                PreparedStatement preparedStatement = Main.con.prepareStatement("SELECT balance from deposit where FIO = '" + forcodetext.getText() + "'");
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    System.out.println("Запись найдена");
                }
                else {
                    showMessageDialog(null, "Пользователь не найден");
                }
                acctext.setText(resultSet.getString("balance"));
                //System.out.println(fiousertext.getText());
            } catch (SQLException Exp) {
                Exp.printStackTrace();
            }
        }
    }

    class GetMoney implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            double accmoney = Double.parseDouble(acctext.getText());
            double getmoney = Double.parseDouble(getmoneytext.getText());

            if (getmoney < 0 && getmoney > accmoney) {
                showMessageDialog(null, "Вывод срежств недоступен");
            } else {
                double result = accmoney - getmoney;
                try {
                    PreparedStatement preparedStatement;
                    preparedStatement = (PreparedStatement) company.Main.con.prepareStatement("UPDATE deposit set balance=? where FIO='" + forcodetext.getText() + "'");
                    preparedStatement.setString(1, String.valueOf(result));
                    preparedStatement.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
