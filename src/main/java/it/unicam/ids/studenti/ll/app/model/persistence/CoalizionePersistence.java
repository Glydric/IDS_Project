package it.unicam.ids.studenti.ll.app.model.persistence;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class CoalizionePersistence {
    String connectionUrl =
            "jdbc:sqlserver://localhost:5432;"
                    + "database=ProgettoIDS;"
                    + "user=;"
                    + "password=yourpassword;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=30;";
            try(
    Connection connection = DriverManager.getConnection(connectionUrl);)
    {
        // Code here.
    }
    // Handle any errors that may have occurred.
            catch(
    SQLException e)

    {
        e.printStackTrace();
    }
}

