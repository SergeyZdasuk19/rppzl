package Database;

import Credit.Credit;
import Deposit.Deposit;
import User.User;
import com.devcolibri.database.DatabaseConnection;
import gui.DepositForm;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkWithDatabase {
    private DatabaseConnection databaseConnection;
    private static User user;
    private static Deposit deposit;
    private static Credit credit;

    static {
        user = new User();
        deposit = new Deposit();
        credit = new Credit();
    }

    public void setValueUser(User user2) {
        user = user2;
    }

    public void setValueDeposit(Deposit deposit2) {
        deposit = deposit2;
    }

    public void setValueCredit(Credit credit2) {
        credit = credit2;
    }

    public void inputClient() {
        databaseConnection = new DatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("INSERT into users(Name, Surname, Patronymic, DateOfBurn, Passport, PassportOut, DateOfOut, Mesto, City, Adress, Phone) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getDateOfBurn());
            preparedStatement.setString(5, user.getPassport());
            preparedStatement.setString(6, user.getPassportOut());
            preparedStatement.setString(7, user.getDateOfDate());
            preparedStatement.setString(8, user.getMestoOfBurn());
            preparedStatement.setString(9, user.getCity());
            preparedStatement.setString(10, user.getAdress());
            preparedStatement.setString(11, user.getPhone());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String output(String nam, String sur, String pas, String pat, String dat, String pasOut, String datOut, String mes, String cit, String addr, String phon) {
        String n = "Ошибка,имя";
        String a = "Ошибка,фамилия";
        String b = "Ошибка,паспорт";
        String d = "Ошибка,отчество";
        String f = "Ошибка,дата_рождения";
        String h = "Ошибка,выдача";
        String g = "Ошибка,дата_выдачи";
        String j = "Ошибка,место";
        String k = "Ошибка,город";
        String l = "Ошибка,адрес";
        String p = "Ошибка,телефон";
        String c = "ОК";
        String letter = "[а-я]+";
        String probel = "[а-я0-9]+";
        try {
            PreparedStatement preparedStatement1 = databaseConnection.con.prepareStatement("SELECT * from users");
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("Surname");
                String patronymic = resultSet.getString("Patronymic");
                String date = resultSet.getString("DateOfBurn");
                String passport = resultSet.getString("Passport");
                String passportOut = resultSet.getString("PassportOut");
                String dateOfOut = resultSet.getString("DateOfOut");
                String mesto = resultSet.getString("Mesto");
                String city = resultSet.getString("City");
                String adress = resultSet.getString("Adress");
                int phone = Integer.parseInt(resultSet.getString("Phone"));
                if (surname.equals(sur)) {
                    return a;
                }
                if (passport.equals(pas)) {
                    return b;
                }
            }
            if (nam.matches(probel) || nam.length() == 0) {
                return n;
            }
            if (sur.matches(probel) || sur.length() == 0) {
                return a;
            }
            char[] massivePassport = pas.toCharArray();
            int cp = 0;
            for (char c8 : massivePassport) {
                if (Character.isDigit(c8) && cp < 2 || massivePassport.length != 8) {
                    return b;
                }
                cp++;
            }
            if (pas.matches(probel) || pas.length() == 0) {
                return b;
            }
            if (pat.matches(probel) || pat.length() == 0) {
                return d;
            }
            char[] massiveDateOfBure = dat.toCharArray();
            if (massiveDateOfBure.length < 10) {
                return f;
            }
            for (int i = 0; i < massiveDateOfBure.length; i++) {
                if (massiveDateOfBure[2] != '-' || massiveDateOfBure[5] != '-') {
                    return f;
                }
            }
            if (dat.matches(letter)) {
                return f;
            } else {
                String[] parts = dat.split("-");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];
                int partInt1 = Integer.parseInt(part1);
                int partInt2 = Integer.parseInt(part2);
                int partInt3 = Integer.parseInt(part3);
                if (partInt1 < 0 || partInt1 > 31 || part1.length() > 3 || part1.length() < 2 || part1.equals("") || part1.equals(" ")) {
                    return f;
                }
                if (partInt2 < 0 || partInt2 > 12 || part2.length() > 3 || part2.length() < 2 || part2.equals("") || part2.equals(" ")) {
                    return f;
                }
                if (partInt3 < 0 || part3.length() < 4 || part3.length() > 4 || part3.equals("") || part3.equals(" ")) {
                    return f;
                }
            }
            if (pasOut.matches(probel) || pasOut.length() == 0) {
                return h;
            }
            char[] massiveDateOfDate = datOut.toCharArray();
            if (massiveDateOfDate.length < 10) {
                return g;
            }
            for (int i = 0; i < massiveDateOfDate.length; i++) {
                if (massiveDateOfDate[2] != '-' || massiveDateOfDate[5] != '-') {
                    return g;
                }
            }
            if (datOut.matches(letter)) {
                return g;
            } else {
                String[] partsOut = datOut.split("-");
                String partOut1 = partsOut[0];
                String partOut2 = partsOut[1];
                String partOut3 = partsOut[2];
                int partInt1 = Integer.parseInt(partOut1);
                int partInt2 = Integer.parseInt(partOut2);
                int partInt3 = Integer.parseInt(partOut3);
                if (partInt1 < 0 || partInt1 > 31 || partOut1.length() > 3 || partOut1.length() < 2 || partOut1.equals("") || partOut1.equals(" ")) {
                    return g;
                }
                if (partInt2 < 0 || partInt2 > 12 || partOut2.length() > 3 || partOut2.length() < 2 || partOut2.equals("") || partOut2.equals(" ")) {
                    return g;
                }
                if (partInt3 < 0 || partOut3.length() < 4 || partOut3.length() > 4 || partOut3.equals("") || partOut3.equals(" ")) {
                    return g;
                }
            }
            if (mes.matches(probel) || mes.length() == 0) {
                return j;
            }
            if (cit.matches(probel) || cit.length() == 0) {
                return k;
            }
            if (addr.matches(probel) || addr.length() == 0) {
                return l;
            }

            if (phon.matches(probel) || phon.length() == 0) {
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void edit() {
        try {
            databaseConnection = new DatabaseConnection();
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("UPDATE users set Name=?, Surname=?,Patronymic=?,DateOfBurn=?,Passport=?,PassportOut=?,DateOfOut=?,Mesto=?,City=?,Adress=?,Phone=? where Surname=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getDateOfBurn());
            preparedStatement.setString(5, user.getPassport());
            preparedStatement.setString(6, user.getPassportOut());
            preparedStatement.setString(7, user.getDateOfDate());
            preparedStatement.setString(8, user.getMestoOfBurn());
            preparedStatement.setString(9, user.getCity());
            preparedStatement.setString(10, user.getAdress());
            preparedStatement.setString(11, user.getPhone());
            preparedStatement.setString(12, user.getSurname());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfull update");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(String passport) {
        try {
            databaseConnection = new DatabaseConnection();
            PreparedStatement preparedStatement50 = databaseConnection.con.prepareStatement("DELETE from users where Passport = ?");
            preparedStatement50.setString(1, passport);
            preparedStatement50.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfull delete");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void inputDeposit() {
        databaseConnection = new DatabaseConnection();
        try {
            PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("INSERT into deposit(FIO, deposit, currency, score, suma, percent, number_score, code_score) values (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, deposit.getDeposit());
            preparedStatement.setString(3, deposit.getCurrency());
            preparedStatement.setString(4, deposit.getScore());
            preparedStatement.setString(5, deposit.getSum());
            preparedStatement.setString(6, deposit.getPersent());
            preparedStatement.setString(7, deposit.getNumberScore());
            preparedStatement.setString(8, deposit.getCodeScore());

            PreparedStatement preparedStatement1 = databaseConnection.con.prepareStatement("SELECT Surname from users");
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                String surname = resultSet.getString("Surname");
                if (surname.equals(user.getSurname())) {
                    preparedStatement.execute();
                    JOptionPane.showMessageDialog(null, "Successfull add to deposit");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDeposit(Double result, String surname) throws SQLException {
        databaseConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("UPDATE deposit set balance=? where FIO=?");
        preparedStatement.setDouble(1, result);
        preparedStatement.setString(2, surname);
        preparedStatement.executeUpdate();

    }

    public void inputCredit() {
        databaseConnection = new DatabaseConnection();
        try {
            PreparedStatement preparedStatementCredit = databaseConnection.con.prepareStatement("INSERT into credit(FIOCredit, credit, currency, score, suma, percent, number_score, code_score) values (?,?,?,?,?,?,?,?)");
            preparedStatementCredit.setString(1, user.getSurname());
            preparedStatementCredit.setString(2, credit.getCredit());
            preparedStatementCredit.setString(3, credit.getCurrency());
            preparedStatementCredit.setString(4, credit.getScore());
            preparedStatementCredit.setString(5, credit.getSum());
            preparedStatementCredit.setString(6, credit.getPersent());
            preparedStatementCredit.setString(7, credit.getNumberScore());
            preparedStatementCredit.setString(8, credit.getCodeScore());
            PreparedStatement preparedStatement1 = databaseConnection.con.prepareStatement("SELECT Surname from users");
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                String surname = resultSet.getString("Surname");
                if (surname.equals(user.getSurname())) {
                    preparedStatementCredit.execute();
                    JOptionPane.showMessageDialog(null, "Successfull add to credit");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCredit(Double result,String surname) throws SQLException {
        databaseConnection = new DatabaseConnection();
        PreparedStatement preparedStatement = databaseConnection.con.prepareStatement("UPDATE credit set balance=? where FIOCredit=?");
        preparedStatement.setDouble(1, result);
        preparedStatement.setString(2, surname);
        preparedStatement.executeUpdate();
    }

}
