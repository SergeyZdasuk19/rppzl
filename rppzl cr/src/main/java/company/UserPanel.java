package company;


import company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPanel extends JFrame {
    private JLabel chnum = new JLabel(" Номер счета");
    private JTextField Textchnum = new JTextField(5);

    private JLabel chcode = new JLabel(" Код счета");
    private JTextField Textchcode = new JTextField(5);

    private JLabel FIO = new JLabel("ФИО");
    private JTextField txt = new JTextField(5);

    private JLabel saldo = new JLabel("Сальдо");
    private JTextField Textsaldo = new JTextField(5);

    private JLabel countsal = new JLabel("Остаток");
    private JTextField Textcount = new JTextField(5);

    private JButton submit = new JButton("Подтвердить");
    private JButton count = new JButton("Расчет");
    private JButton credithistory = new JButton("Кредитная история");


    public UserPanel(){
        super("Личный кабинет");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setBounds(460,250, 300, 350);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(7,2));

        container.add(chnum);
        container.add(Textchnum);

        container.add(chcode);
        container.add(Textchcode);

        container.add(FIO);
        container.add(txt);

        container.add(saldo);
        container.add(Textsaldo);

        container.add(countsal);
        container.add(Textcount);

        container.add(submit);submit.addActionListener(new SubmitUser());
        container.add(count); count.addActionListener(new Counting());
        container.add(credithistory); credithistory.addActionListener(new CreditInf());


        this.setVisible(true);
    }


    class SubmitUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String jq = "SELECT * FROM checks WHERE checknumber= '" + Textchnum.getText() + "' and code = '" + Textchcode.getText() + "'";
         try{
             ResultSet resultSet = Main.state.executeQuery(jq);

            if (resultSet.next()){
                System.out.println("ok");

            }
             txt.setText(resultSet.getString("FIO"));
             Textsaldo.setText(resultSet.getString("money"));
//             String t=resultSet.getString("period");
//             String per=resultSet.getString("percent");
//
//
////             String hh=resultSet.getString("money");
////            int all= Integer.parseInt(hh);
////            int time = Integer.parseInt(t);
////            int perc=Integer.parseInt(per);
//
//            int m= all + (all*(perc/100)/time*12);

           // countsal.setText(String.valueOf(m));


         }catch (SQLException ex){ex.printStackTrace();}
        }
    }


    class Counting implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String jq = "SELECT * FROM checks WHERE checknumber= '" + Textchnum.getText() + "' and code = '" + Textchcode.getText() + "'";
                ResultSet resultSet = Main.state.executeQuery(jq);

                if (resultSet.next()){
                    System.out.println("ok");

                }
                txt.setText(resultSet.getString("FIO"));
                Textsaldo.setText(resultSet.getString("money"));
                String t=resultSet.getString("period");
                String per=resultSet.getString("percent");

                int all= Integer.parseInt(Textsaldo.getText());
                int time = Integer.parseInt(t);
                int perc=Integer.parseInt(per);

                int pr= (time * perc)*365/100;
                System.out.println(pr);

               all+=pr;

                Textcount.setText(String.valueOf(all));


            }catch (SQLException ex){ex.printStackTrace();}
        }
    }

    class CreditInf implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new CreditManager();
        }
    }

}
