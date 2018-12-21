package Main;

import com.devcolibri.database.DatabaseConnection;
import gui.Form;

public class Main {
    private static DatabaseConnection databaseConnection;
    private static Form form;
    public static void main(String[] args) {
        databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        form = new Form();
        form.technic();
    }
}
