package database;

import exceptions.GourmetException;
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
            throw new GourmetException("Kannan avaus epäonnistui: " + ex.getMessage());
        }
    }

    public static Connection avaaYhteys() {
        try {
            return yhteysVarasto.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Yhteyden avaaminen epäonnistui: " + ex.getMessage());
        }
    }

    public static void suljeYhteys(Connection yhteys) {
        if (yhteys != null) {
            try {
                yhteys.close();
            } catch (SQLException ex) {
                Logger.getLogger(Tietokanta.class.getName()).log(Level.SEVERE, null, ex);
                throw new GourmetException("Yhteyden sulkeminen epäonnistui: " + ex.getMessage());

            }
        }
    }
}