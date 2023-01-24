package it.unicam.ids.studenti.ll.app.model;


//TODO salvare dati dei commercianti, coalizione e dei clienti..le tre C.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerDataBase {
    private static ManagerDataBase instance;
    private String url, user, pwd;
    private Connection conn;

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Database connected , ready to go!");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Problems in opening a connection to the DB");
            e.printStackTrace();
        }
    }

    public void salva(Commerciante commerciante) {
        connect();
    }

    public void salva(Cliente cliente) {
        connect();

    }

    public void salva(Coalizione coalizione) {
        connect();

    }


}
