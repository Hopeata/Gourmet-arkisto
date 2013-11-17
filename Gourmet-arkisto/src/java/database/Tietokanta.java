package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Tietokanta {

    private static DataSource yhteysVarasto;

    static {
        try {
            Context ctx = new InitialContext();
            yhteysVarasto = (DataSource) ctx.lookup("java:/comp/env/jdbc/tietokanta");
        } catch (NamingException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection avaaYhteys() throws SQLException {
        return yhteysVarasto.getConnection();
    }

    public static void suljeYhteys(Connection yhteys) throws SQLException {
        yhteys.close();
    }
}