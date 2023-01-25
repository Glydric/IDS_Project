package it.unicam.ids.studenti.ll.app.model.persistence;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class CoalizionePersistence {
    String connectionUrl =
            "jdbc:sqlserver://localhost:5432;"
                    + "database=ids;"
                    + "user=utente;"
                    + "password=pass;"
                    + "encrypt=true;"
                    + "trustServerCertificate=false;"
                    + "loginTimeout=10;";
    public CoalizionePersistence(){
        try{
            Connection connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

