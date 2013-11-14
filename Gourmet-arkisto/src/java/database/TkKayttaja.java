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

/**
 *
 * @author Valeria
 */
public class TkKayttaja {

    private static final String HAE_KAYTTAJAT =
            "SELECT id, tunnus, sahkoposti, admin_oikeudet, vip_oikeudet FROM kayttaja";
    private static final String HAE_TUNNUKSELLA_JA_SALASANALLA = HAE_KAYTTAJAT
            + " WHERE tunnus = ? AND salasana = ?";

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
            kysely.setString(2, salasana);
            List<Kayttaja> kayttajat = muunnaKayttajaOlioiksi(kysely.executeQuery());
            if (!kayttajat.isEmpty()) {
                kirjautunutKayttaja = kayttajat.get(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Kirjautuminen epäonnistui: " + ex.getMessage());
        }
        return kirjautunutKayttaja;
    }
}
