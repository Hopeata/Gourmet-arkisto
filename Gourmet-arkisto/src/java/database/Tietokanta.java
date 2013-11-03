package database;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Tietokanta {

    private static Connection yhteys = null;
    private static PreparedStatement kysely = null;
    private static ResultSet tulokset = null;

    public static List<String> teeAsioitaKannalla() throws Exception {
        try {
            //Haetaan context-xml-tiedostosta tietokannan yhteystiedot
            //HUOM! Tämä esimerkki ei toimi sellaisenaan ilman Tomcat-palvelinta!
            Context ctx = new InitialContext();
            DataSource yhteysVarasto = (DataSource) ctx.lookup("java:/comp/env/jdbc/tietokanta");

            //Otetaan yhteys tietokantaan
            yhteys = yhteysVarasto.getConnection();

            //Suoritetaan sql-kysely. Haetaan täysi-ikäiset Lehtoset tietokannasta
            String sql = "SELECT name FROM TestTable";
            kysely = yhteys.prepareStatement(sql);
            tulokset = kysely.executeQuery();

            //Tulostetaan tietoja löydetyistä käyttäjistä
            List<String> nimet = new ArrayList<String>();
            while (tulokset.next()) {
                String nimi = tulokset.getString("name");
                nimet.add(nimi);
                // System.out.println("Käyttäjän " + nimi);
            }
            
            return nimet;

        } catch (Exception e) {
            throw e;
        } finally {
            //Suljetaan lopulta kaikki avatut resurssit
            try {
                tulokset.close();
            } catch (Exception e) {
            }
            try {
                kysely.close();
            } catch (Exception e) {
            }
            try {
                yhteys.close();
            } catch (Exception e) {
            }
        }
    }
    
}