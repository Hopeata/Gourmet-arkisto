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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Resepti;
import models.ReseptinNimi;

/**
 *
 * @author Valeria
 */
public class TkResepti {

    private static final String HAE_RESEPTIT = "SELECT * FROM resepti";
    private static final String LISAA_RESEPTI = "INSERT INTO resepti (lisaysaika, ohje, "
            + "kuva_url, kayttaja_id, paaraaka_aine_id) VALUES (?, ?, ?, ?, ?)";
    private static final String LISAA_RESEPTIN_NIMI = "INSERT INTO reseptinnimi (resepti_id, "
            + "nimi, on_paanimi) VALUES (?, ?, ?)";
    private static final String HAE_RESEPTIN_NIMET = "SELECT nimi FROM resepti, WHERE reseptin_id = ?";

    private static List<Resepti> muunnaNimettomiksiReseptiOlioiksi(ResultSet rs) throws SQLException {
        List<Resepti> reseptit = new ArrayList<Resepti>();
        while (rs.next()) {
            reseptit.add(new Resepti(rs.getInt("id"),
                    rs.getDate("lisaysaika"), rs.getString("ohje"), rs.getString("kuva_url"),
                    rs.getInt("kayttaja_id"), rs.getInt("paaraaka_aine_id"), null));
        }
        rs.close();
        return reseptit;
    }

    private static List<ReseptinNimi> muunnaReseptinNimiOlioiksi(ResultSet rs) throws SQLException {
        List<ReseptinNimi> reseptinNimet = new ArrayList<ReseptinNimi>();
        while (rs.next()) {
            reseptinNimet.add(new ReseptinNimi(rs.getInt("reseptin_id"), rs.getString("nimi"),
                    rs.getBoolean("onPaanimi")));
        }
        return reseptinNimet;
    }

    private static List<ReseptinNimi> haeReseptinNimet(Connection yhteys, int id) throws SQLException {
        PreparedStatement kysely = yhteys.prepareStatement(HAE_RESEPTIN_NIMET);
        kysely.setInt(1, id);
        List<ReseptinNimi> reseptinNimet = muunnaReseptinNimiOlioiksi(kysely.executeQuery());
        kysely.close();
        return reseptinNimet;
    }

    public static List<Resepti> haeReseptit() {
        List<Resepti> reseptit = null;
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(HAE_RESEPTIT);
            reseptit = muunnaNimettomiksiReseptiOlioiksi(kysely.executeQuery());
            kysely.close();
            for (Resepti resepti : reseptit) {
                resepti.setNimet(haeReseptinNimet(yhteys, resepti.getId()));
            }
            Tietokanta.suljeYhteys(yhteys);
        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Reseptien haku epäonnistui: " + ex.getMessage());

        }
        return reseptit;
    }

    public static void lisaaReseptiKantaan(Resepti resepti) {
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            yhteys.setAutoCommit(false);
            PreparedStatement lisayslause = yhteys.prepareStatement(LISAA_RESEPTI);
            PreparedStatement nimenlisayslause = yhteys.prepareStatement(LISAA_RESEPTIN_NIMI);
            lisayslause.setDate(1, resepti.getLisaysaika());
            lisayslause.setString(2, resepti.getOhje());
            lisayslause.setString(3, resepti.getKuvaUrl());
            lisayslause.setInt(4, resepti.getTekija());
            if (resepti.getPaaraakaAine() == -1) {
                lisayslause.setNull(5, java.sql.Types.INTEGER);
            } else {
                lisayslause.setInt(5, resepti.getPaaraakaAine());
            }
            lisayslause.executeUpdate();
            ResultSet vastaus = lisayslause.getGeneratedKeys();
            int id = -1;
            if (vastaus.next()) {
                id = vastaus.getInt(1);
            }
            nimenlisayslause.setInt(1, id);
            nimenlisayslause.setString(2, resepti.haePaanimi());
            nimenlisayslause.setBoolean(3, true);

            Tietokanta.suljeYhteys(yhteys);
        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Reseptinlisäys epäonnistui: " + ex.getMessage());
        }
    }

    public static void poistaResepti(Resepti resepti) {
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            Statement poisto = yhteys.createStatement();
            poisto.executeUpdate("DELETE FROM resepti WHERE id=" + resepti.getId());
        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Reseptinlisäys epäonnistui: " + ex.getMessage());

        }


    }
}
