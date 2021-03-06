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

    // Lisäyslauseet
    private static final String LISAA_KAYTTAJA = "INSERT INTO kayttaja (tunnus, sahkoposti, "
            + "salasana, admin_oikeudet, vip_oikeudet) VALUES (?, ?, ?, ?, ?)";
    // Hakulauseet
    private static final String HAE_KAYTTAJAT =
            "SELECT id, tunnus, sahkoposti, admin_oikeudet, vip_oikeudet FROM kayttaja";
    private static final String JARJESTA_TUNNUKSEN_MUKAAN = " ORDER BY tunnus";
    private static final String HAE_TUNNUKSELLA_JA_SALASANALLA = HAE_KAYTTAJAT
            + " WHERE tunnus = ? AND salasana = ?";
    private static final String HAE_TUNNUKSELLA = "SELECT id FROM kayttaja WHERE tunnus = ?";
    private static final String HAE_IDLLA = HAE_KAYTTAJAT + " WHERE id = ?";
    private static final String HAE_TUNNUKSELLA_TAI_SAHKOPOSTILLA = HAE_KAYTTAJAT + " WHERE tunnus LIKE ? OR sahkoposti LIKE ?";
    // Poistolauseet
    private static final String POISTA_KAYTTAJA = "DELETE FROM kayttaja WHERE id = ?";
    // Päivityslauseet
    private static final String PAIVITA_VIP = "UPDATE kayttaja SET vip_oikeudet = ? WHERE id = ?";
    private static final String PAIVITA_TIEDOT = "UPDATE kayttaja SET tunnus = ?, sahkoposti = ? WHERE id = ?";
    private static final String PAIVITA_SALASANA = "UPDATE kayttaja SET salasana = ? WHERE id = ?";

    private static List<Kayttaja> muunnaKayttajaOlioiksi(ResultSet rs) throws SQLException {
        List<Kayttaja> kayttajat = new ArrayList<Kayttaja>();
        while (rs.next()) {
            kayttajat.add(new Kayttaja(rs.getInt("id"), rs.getString("tunnus"),
                    rs.getString("sahkoposti"), rs.getBoolean("admin_oikeudet"), rs.getBoolean("vip_oikeudet")));
        }
        return kayttajat;
    }

    public static Kayttaja kirjaudu(String tunnus, String salasana) {
        Connection yhteys = Tietokanta.avaaYhteys();
        Kayttaja kirjautunutKayttaja = null;
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_TUNNUKSELLA_JA_SALASANALLA);
            kysely.setString(1, tunnus);
            kysely.setString(2, SalausUtility.salaaSalasana(salasana));
            List<Kayttaja> kayttajat = muunnaKayttajaOlioiksi(kysely.executeQuery());
            if (!kayttajat.isEmpty()) {
                kirjautunutKayttaja = kayttajat.get(0);
            }
            kysely.close();
            return kirjautunutKayttaja;
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Kirjautuminen epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static Kayttaja haeKayttajaIdlla(int id) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_IDLLA);
            kysely.setInt(1, id);
            List<Kayttaja> kayttajat = muunnaKayttajaOlioiksi(kysely.executeQuery());
            return kayttajat.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Hakutuloksen etsintä epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static List<Kayttaja> haeKayttajat() {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_KAYTTAJAT + JARJESTA_TUNNUKSEN_MUKAAN);
            List<Kayttaja> kayttajat = muunnaKayttajaOlioiksi(kysely.executeQuery());
            return kayttajat;
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Käyttäjien haku epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
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

    public static List<Kayttaja> haeKayttajaa(String haku) {
        Connection yhteys = Tietokanta.avaaYhteys();
        ResultSet rs = null;
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_TUNNUKSELLA_TAI_SAHKOPOSTILLA + JARJESTA_TUNNUKSEN_MUKAAN);
            kysely.setString(1, "%" + haku + "%");
            kysely.setString(2, "%" + haku + "%");
            rs = kysely.executeQuery();
            List<Kayttaja> kayttajat = muunnaKayttajaOlioiksi(rs);
            return kayttajat;
        } catch (SQLException ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Hakutuloksen etsintä epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void lisaaKayttaja(Kayttaja kayttaja, String salasana) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
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
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void paivitaKayttajaTiedot(Kayttaja kayttaja, String salasana) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement muokkauslause = yhteys.prepareStatement(PAIVITA_TIEDOT);
            muokkauslause.setString(1, kayttaja.getTunnus());
            muokkauslause.setString(2, kayttaja.getSahkoposti());
            muokkauslause.setInt(3, kayttaja.getId());
            muokkauslause.executeUpdate();
            if (salasana != null && !salasana.equals("")) {
                muokkauslause = yhteys.prepareStatement(PAIVITA_SALASANA);
                muokkauslause.setString(1, SalausUtility.salaaSalasana(salasana));
                muokkauslause.setInt(2, kayttaja.getId());
                muokkauslause.executeUpdate();
            }
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Käyttäjätietojen muokkaus epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void paivitaVipOikeudet(int id, boolean vipOikeus) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement paivityslause = yhteys.prepareStatement(PAIVITA_VIP);
            if (vipOikeus) {
                paivityslause.setBoolean(1, false);
            } else {
                paivityslause.setBoolean(1, true);
            }
            paivityslause.setInt(2, id);
            paivityslause.executeUpdate();
            paivityslause.close();
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Käyttäjän vip-oikeuksien päivitys epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void poistaKayttaja(int id) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement poistolause = yhteys.prepareStatement(POISTA_KAYTTAJA);
            poistolause.setInt(1, id);
            poistolause.executeUpdate();
            poistolause.close();
        } catch (Exception ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Käyttäjän poisto epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }
}
