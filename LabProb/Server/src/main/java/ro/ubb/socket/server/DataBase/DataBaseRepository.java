package ro.ubb.socket.server.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseRepository {

    private Connection con;

    public DataBaseRepository(String s) {
        connectToDB(s);
    }

    public Connection getConnection() {
        return con;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public void connectToDB(String s){
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + s, "postgres", "mpp");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
}
