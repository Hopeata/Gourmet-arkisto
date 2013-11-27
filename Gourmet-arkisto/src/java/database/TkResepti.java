/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.sun.rmi.rmid.ExecOptionPermission;
import exceptions.GourmetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Kayttaja;
import models.PaaraakaAine;
import models.Resepti;
import models.ReseptinNimi;
import models.Ruokalaji;

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
    private static final String LISAA_RESEPTIN_RUOKALAJI = "INSERT INTO reseptinruokalaji (ruokalaji_id, "
            + "resepti_id) VALUES (?, ?)";
    private static final String HAE_RESEPTIN_NIMET = "SELECT * FROM reseptinnimi WHERE resepti_id = ?";
    private static final String HAE_RUOKALAJIT = "SELECT * FROM ruokalaji";
    private static final String HAE_RUOKALAJIT_IDLLA = HAE_RUOKALAJIT + " WHERE id = ?";
    private static final String HAE_RESEPTIN_RUOKALAJI_ID = "SELECT ruokalaji_id FROM reseptinruokalaji WHERE resepti_id = ?";
    private static final String HAE_PAARAAKAAINEET = "SELECT * FROM paaraaka_aine";
    private static final String HAE_PAARAAKAAINE_IDLLA = HAE_PAARAAKAAINEET + " WHERE id = ?";

    private static List<Resepti> muunnaNimettomiksiReseptiOlioiksi(Connection yhteys, ResultSet rs) throws SQLException {
        List<Resepti> reseptit = new ArrayList<Resepti>();
        while (rs.next()) {
            int tekijaId = rs.getInt("kayttaja_id");
            Kayttaja tekija = null;
            if (tekijaId != 0) {
                tekija = TkKayttaja.haeKayttajaIdlla(tekijaId);
            }
            int paaraakaAineId = rs.getInt("paaraaka_aine_id");
            PaaraakaAine paaraakaAine = null;
            if (paaraakaAineId != 0) {
                paaraakaAine = haePaaraakaAineIdlla(paaraakaAineId);
            }
            List<Ruokalaji> ruokalajit = haeReseptinRuokalajit(yhteys, rs.getInt("id"));
            reseptit.add(new Resepti(rs.getInt("id"),
                    rs.getTimestamp("lisaysaika"), rs.getString("ohje"), rs.getString("kuva_url"),
                    tekija, paaraakaAine, null, ruokalajit));
        }
        rs.close();
        return reseptit;
    }

    private static List<ReseptinNimi> muunnaReseptinNimiOlioiksi(ResultSet rs) throws SQLException {
        List<ReseptinNimi> reseptinNimet = new ArrayList<ReseptinNimi>();
        while (rs.next()) {
            reseptinNimet.add(new ReseptinNimi(rs.getInt("resepti_id"), rs.getString("nimi"),
                    rs.getBoolean("on_paanimi")));
        }
        return reseptinNimet;
    }

    private static List<ReseptinNimi> haeReseptinNimet(Connection yhteys, int id) throws SQLException {
        PreparedStatement kysely = yhteys.prepareStatement(HAE_RESEPTIN_NIMET);
        kysely.setInt(1, id);
        ResultSet rs = kysely.executeQuery();
        List<ReseptinNimi> reseptinNimet = muunnaReseptinNimiOlioiksi(rs);
        kysely.close();
        return reseptinNimet;
    }

    private static List<Ruokalaji> haeReseptinRuokalajit(Connection yhteys, int id) throws SQLException {
        PreparedStatement kysely = yhteys.prepareStatement(HAE_RESEPTIN_RUOKALAJI_ID);
        kysely.setInt(1, id);
        ResultSet rs = kysely.executeQuery();
        List<Ruokalaji> reseptinRuokalajit = new ArrayList<Ruokalaji>();
        while (rs.next()) {
            PreparedStatement kysely1 = yhteys.prepareStatement(HAE_RUOKALAJIT_IDLLA);
            kysely1.setInt(1, rs.getInt("ruokalaji_id"));
            reseptinRuokalajit.add(muunnaRuokalajiOlioiksi(kysely1.executeQuery()).get(0));
        }
        kysely.close();
        return reseptinRuokalajit;
    }

    public static List<Resepti> haeReseptit() {
        List<Resepti> reseptit = null;
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            PreparedStatement kysely = yhteys.prepareStatement(HAE_RESEPTIT);
            ResultSet rs = kysely.executeQuery();
            reseptit = muunnaNimettomiksiReseptiOlioiksi(yhteys, rs);
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

    private static List<Ruokalaji> muunnaRuokalajiOlioiksi(ResultSet rs) throws SQLException {
        List<Ruokalaji> ruokalajit = new ArrayList<Ruokalaji>();
        while (rs.next()) {
            ruokalajit.add(new Ruokalaji(rs.getInt("id"), rs.getString("nimi")));
        }
        return ruokalajit;
    }

    public static List<Ruokalaji> haeRuokalajit() {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_RUOKALAJIT);
            List<Ruokalaji> ruokalajit = muunnaRuokalajiOlioiksi(kysely.executeQuery());
            kysely.close();
            return ruokalajit;
        } catch (SQLException ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Ruokalajien haku epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    private static List<PaaraakaAine> muunnaPaaraakaAineOlioiksi(ResultSet rs) throws SQLException {
        List<PaaraakaAine> paaraakaAineet = new ArrayList<PaaraakaAine>();
        while (rs.next()) {
            paaraakaAineet.add(new PaaraakaAine(rs.getInt("id"), rs.getString("nimi")));
        }
        return paaraakaAineet;
    }

    public static PaaraakaAine haePaaraakaAineIdlla(int id) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_PAARAAKAAINE_IDLLA);
            kysely.setInt(1, id);
            List<PaaraakaAine> paaraakaAineet = muunnaPaaraakaAineOlioiksi(kysely.executeQuery());
            return paaraakaAineet.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Pääraaka-aineen haku epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static List<PaaraakaAine> haePaaraakaAineet() {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_PAARAAKAAINEET);
            List<PaaraakaAine> paaraakaAineet = muunnaPaaraakaAineOlioiksi(kysely.executeQuery());
            kysely.close();
            return paaraakaAineet;
        } catch (SQLException ex) {
            Logger.getLogger(TkKayttaja.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Ruokalajien haku epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void lisaaResepti(Resepti resepti, List<Integer> ruokalajiIdt, String reseptinPaanimi) {
        Connection yhteys = Tietokanta.avaaYhteys();
        PreparedStatement lisayslause = null;
        PreparedStatement ruokalajinlisayslause = null;
        PreparedStatement nimenlisayslause = null;
        try {
            yhteys.setAutoCommit(false);
            lisayslause = yhteys.prepareStatement(LISAA_RESEPTI, Statement.RETURN_GENERATED_KEYS);
            ruokalajinlisayslause = yhteys.prepareStatement(LISAA_RESEPTIN_RUOKALAJI);
            nimenlisayslause = yhteys.prepareStatement(LISAA_RESEPTIN_NIMI);
            Date pvm = new Date();
            Timestamp lisaysaika = new Timestamp(pvm.getTime());
            lisayslause.setTimestamp(1, lisaysaika);
            lisayslause.setString(2, resepti.getOhje());
            lisayslause.setString(3, resepti.getKuvaUrl());
            lisayslause.setInt(4, resepti.getTekija().getId());
            if (resepti.getPaaraakaAine() == null) {
                lisayslause.setNull(5, java.sql.Types.INTEGER);
            } else {
                lisayslause.setInt(5, resepti.getPaaraakaAine().getId());
            }
            lisayslause.executeUpdate();
            ResultSet vastaus = lisayslause.getGeneratedKeys();
            int id = -1;
            if (vastaus.next()) {
                id = vastaus.getInt(1);
            }
            for (Integer ruokalajiId : ruokalajiIdt) {
                ruokalajinlisayslause.setInt(1, ruokalajiId);
                ruokalajinlisayslause.setInt(2, id);
                ruokalajinlisayslause.executeUpdate();
            }
            nimenlisayslause.setInt(1, id);
            nimenlisayslause.setString(2, reseptinPaanimi);
            nimenlisayslause.setBoolean(3, true);
            nimenlisayslause.executeUpdate();

            yhteys.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            try {
                System.err.print("Transaction is being rolled back");
                yhteys.rollback();
            } catch (SQLException excep) {
                throw new GourmetException("Reseptinlisäys epäonnistui: " + ex.getMessage());
            }
        } finally {
            try {
                if (lisayslause != null) {
                    lisayslause.close();
                }
                if (ruokalajinlisayslause != null) {
                    ruokalajinlisayslause.close();
                }
                if (nimenlisayslause != null) {
                    nimenlisayslause.close();
                }
                yhteys.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            }
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void poistaResepti(Resepti resepti) {
        try {
            Connection yhteys = Tietokanta.avaaYhteys();
            Statement poisto = yhteys.createStatement();
            poisto.executeUpdate("DELETE FROM resepti WHERE id=" + resepti.getId());


        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class
                    .getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException(
                    "Reseptinlisäys epäonnistui: " + ex.getMessage());

        }


    }
}
