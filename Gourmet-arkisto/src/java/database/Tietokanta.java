package database;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Tietokanta {

    private static DataSource yhteysVarasto;

    public Tietokanta() throws NamingException {
        Context ctx = new InitialContext();
        yhteysVarasto = (DataSource) ctx.lookup("java:/comp/env/jdbc/tietokanta");
    }

    public static Connection avaaYhteys() throws SQLException {
        return yhteysVarasto.getConnection();
    }

    public static void suljeYhteys(Connection yhteys) throws SQLException {
        yhteys.close();
    }
}