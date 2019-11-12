package datasource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        new MySystem().init();
    }
}
