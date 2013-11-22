/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import exceptions.GourmetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Kayttaja;
import utilities.SalausUtility;

/**
 *
 * @author Valeria
 */
public class TkKayttaja {

    private static final String HAE_KAYTTAJAT =
            "SELECT id, tunnus, sahkoposti, admin_oikeudet, vip_oikeudet FROM kayttaja";
    private static final String HAE_TUNNUKSELLA_JA_SALASANALLA = HAE_KAYTTAJAT
            + " WHERE tunnus = ? AND salasana = ?";
    private static final String HAE_TUNNUKSELLA = "SELECT id FROM kayttaja WHERE tunnus = ?";
    private static final String LISAA_KAYTTAJA = "INSERT INTO kayttaja (tunnus, sahkoposti, "
            + "salasana, admin_oikeudet, vip_oikeudet) VALUES (?, ?, ?, ?, ?)";

    private static List<Kayttaja> muunnaKayttajaOlioiksi(ResultSet rs) throws SQLException {
        List<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
        while (rs.next()) {
            kayttajat.add(new Kayttaja(rs.getInt("id"), rs.getString("tunnus"),
                    rs.getString("sahkoposti"), rs.getBoolean("admin_oikeudet"), rs.getBoolean("vip_oikeudet")));
        }
        return kayttajat;
    }

    public static Kayttaja kirjaudu(String tunnus, String salasana) {

        Kayttaja kirjautunutKayttaja = null;
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(HAE_TUNNUKSELLA_JA_SALASANALLA);
            kysely.setString(1, tunnus);
            kysely.setString(2, SalausUtility.salaaSalasana(salasana));
            List<Kayttaja> kayttajat = muunnaKayttajaOlioiksi(kysely.executeQuery());
            if (!kayttajat.isEmpty()) {
                kirjautunutKayttaja = kayttajat.get(0);
            }
            kysely.close();
            Tietokanta.suljeYhteys(yhteys);
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Kirjautuminen epäonnistui: " + ex.getMessage());
        }
        return kirjautunutKayttaja;
    }

    public static List<Kayttaja> haeKayttajat() {
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(HAE_KAYTTAJAT);
            List<Kayttaja> kayttajat = muunnaKayttajaOlioiksi(kysely.executeQuery());
            return kayttajat;
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Käyttäjien haku epäonnistui: " + ex.getMessage());
        }
    }

    public static boolean tunnusOlemassa(String tunnus) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_TUNNUKSELLA);
            kysely.setString(1, tunnus);
            ResultSet rs = kysely.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Tunnuksen tarkistus epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void lisaaKayttaja(Kayttaja kayttaja, String salasana) {
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            PreparedStatement lisayslause = yhteys.prepareStatement(LISAA_KAYTTAJA);
            lisayslause.setString(1, kayttaja.getTunnus());
            lisayslause.setString(2, kayttaja.getSahkoposti());
            lisayslause.setString(3, SalausUtility.salaaSalasana(salasana));
            lisayslause.setBoolean(4, false);
            lisayslause.setBoolean(5, false);
            lisayslause.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Käyttäjän lisäys epäonnistui: " + ex.getMessage());
        }
    }
}
