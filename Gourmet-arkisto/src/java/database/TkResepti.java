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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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

    // Lisäyslauseet
    private static final String LISAA_RESEPTI = "INSERT INTO resepti (lisaysaika, ohje, "
            + "kuva_url, kayttaja_id, paaraaka_aine_id) VALUES (?, ?, ?, ?, ?)";
    private static final String LISAA_RESEPTIN_NIMI = "INSERT INTO reseptinnimi (resepti_id, "
            + "nimi, on_paanimi) VALUES (?, ?, ?)";
    private static final String LISAA_RESEPTIN_RUOKALAJI = "INSERT INTO reseptinruokalaji (ruokalaji_id, "
            + "resepti_id) VALUES (?, ?)";
    // Hakulauseet
    private static final String HAE_RESEPTIT = "SELECT * FROM resepti";
    private static final String HAE_RESEPTI_IDLLA = HAE_RESEPTIT + " WHERE id = ?";
    private static final String HAE_RESEPTIN_NIMET = "SELECT * FROM reseptinnimi WHERE resepti_id = ?";
    private static final String HAE_RESEPTI_HAKUEHDOILLA = "SELECT * FROM resepti r INNER JOIN reseptinruokalaji rr ON (r.id=rr.resepti_id) "
            + "INNER JOIN reseptinnimi rn ON (r.id = rn.resepti_id) "
            + "WHERE (LOWER(nimi) LIKE LOWER(?) OR LOWER(ohje) LIKE LOWER(?))";
    private static final String JARJESTA_RESEPTIT_PVM_MUKAAN = " ORDER BY lisaysaika DESC";
    private static final String HAE_RUOKALAJIT = "SELECT * FROM ruokalaji";
    private static final String HAE_RUOKALAJIT_IDLLA = HAE_RUOKALAJIT + " WHERE id = ?";
    private static final String HAE_RESEPTIN_RUOKALAJI_ID = "SELECT ruokalaji_id FROM reseptinruokalaji WHERE resepti_id = ?";
    private static final String HAE_PAARAAKAAINEET = "SELECT * FROM paaraaka_aine";
    private static final String HAE_PAARAAKAAINE_IDLLA = HAE_PAARAAKAAINEET + " WHERE id = ?";
    // Poistolauseet
    private static final String POISTA_RESEPTI = "DELETE FROM resepti WHERE id = ?";
    private static final String POISTA_RESEPTIN_RUOKALAJIT = "DELETE FROM reseptinruokalaji WHERE resepti_id = ?";
    private static final String POISTA_RESEPTIN_NIMET = "DELETE FROM reseptinnimi WHERE resepti_id = ?";

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

    public static Resepti haeResepti(int id) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_RESEPTI_IDLLA);
            kysely.setInt(1, id);
            ResultSet rs = kysely.executeQuery();
            List<Resepti> reseptit = muunnaNimettomiksiReseptiOlioiksi(yhteys, rs);
            kysely.close();
            Resepti resepti = reseptit.get(0);
            resepti.setNimet(haeReseptinNimet(yhteys, resepti.getId()));
            return resepti;
        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Reseptin haku epäonnistui: " + ex.getMessage());
        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static List<Resepti> haeReseptit() {
        List<Resepti> reseptit = null;
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            PreparedStatement kysely = yhteys.prepareStatement(HAE_RESEPTIT + JARJESTA_RESEPTIT_PVM_MUKAAN);
            ResultSet rs = kysely.executeQuery();
            reseptit = muunnaNimettomiksiReseptiOlioiksi(yhteys, rs);
            kysely.close();
            for (Resepti resepti : reseptit) {
                resepti.setNimet(haeReseptinNimet(yhteys, resepti.getId()));
            }
            return reseptit;
        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Reseptien haku epäonnistui: " + ex.getMessage());

        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static List<Resepti> haeReseptia(String hakusana, String[] ruokalajit, String[] paaraakaAineet) {
        List<Resepti> reseptit = new ArrayList<Resepti>();
        HashSet<Integer> reseptinIdt = new HashSet<Integer>();
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            StringBuilder hakuehdot = new StringBuilder();
            if (ruokalajit != null && ruokalajit.length != 0) {
                hakuehdot.append(" AND (");
                for (int i = 0; i < ruokalajit.length; i++) {
                    hakuehdot.append("ruokalaji_id = ?");
                    if (i != ruokalajit.length - 1) {
                        hakuehdot.append(" OR ");
                    }
                }
                hakuehdot.append(")");
            }
            if (paaraakaAineet != null && paaraakaAineet.length != 0) {
                hakuehdot.append(" AND (");
                for (int i = 0; i < paaraakaAineet.length; i++) {
                    hakuehdot.append("paaraaka_aine_id = ?");
                    if (i != paaraakaAineet.length - 1) {
                        hakuehdot.append(" OR ");
                    }
                }
                hakuehdot.append(")");
            }
            String haku = HAE_RESEPTI_HAKUEHDOILLA + hakuehdot.toString() + JARJESTA_RESEPTIT_PVM_MUKAAN;
            PreparedStatement kysely = yhteys.prepareStatement(haku);
            kysely.setString(1, "%" + hakusana + "%");
            kysely.setString(2, "%" + hakusana + "%");
            int parametrinIndeksi = 3;
            if (ruokalajit != null) {
                for (String ruokalajiId : ruokalajit) {
                    kysely.setInt(parametrinIndeksi, Integer.parseInt(ruokalajiId));
                    parametrinIndeksi++;
                }
            }
            if (paaraakaAineet != null) {
                for (String paaraakaAineId : paaraakaAineet) {
                    kysely.setInt(parametrinIndeksi, Integer.parseInt(paaraakaAineId));
                    parametrinIndeksi++;
                }
            }
            ResultSet rs = kysely.executeQuery();
            List<Resepti> haetutReseptit = muunnaNimettomiksiReseptiOlioiksi(yhteys, rs);
            kysely.close();
            for (Resepti resepti : haetutReseptit) {
                int id = resepti.getId();
                if (!reseptinIdt.contains(id)) {
                    reseptinIdt.add(id);
                    reseptit.add(resepti);
                }
            }
            for (Resepti resepti : reseptit) {
                resepti.setNimet(haeReseptinNimet(yhteys, resepti.getId()));
            }
            return reseptit;
        } catch (SQLException ex) {
            Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Reseptien haku epäonnistui: " + ex.getMessage());

        } finally {
            Tietokanta.suljeYhteys(yhteys);
        }
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
        try {
            yhteys.setAutoCommit(false);
            PreparedStatement lisayslause = yhteys.prepareStatement(LISAA_RESEPTI, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ruokalajinlisayslause = yhteys.prepareStatement(LISAA_RESEPTIN_RUOKALAJI);
            PreparedStatement nimenlisayslause = yhteys.prepareStatement(LISAA_RESEPTIN_NIMI);
            Timestamp lisaysaika = new Timestamp(new Date().getTime());
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
            try {
                System.err.print("Transaction is being rolled back");
                yhteys.rollback();
            } catch (SQLException excep) {
                Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
                throw new GourmetException("Reseptinlisäys epäonnistui: " + ex.getMessage());
            }
        } finally {
            try {
                yhteys.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
                throw new GourmetException("Yhteyden automatisointi epäonnistui: " + ex.getMessage());
            }
            Tietokanta.suljeYhteys(yhteys);
        }
    }

    public static void poistaResepti(int id) {
        Connection yhteys = Tietokanta.avaaYhteys();
        try {
            yhteys.setAutoCommit(false);
            PreparedStatement reseptinPoistolause = yhteys.prepareStatement(POISTA_RESEPTI);
            PreparedStatement reseptinNimienPoistolause = yhteys.prepareStatement(POISTA_RESEPTIN_NIMET);
            PreparedStatement reseptinRuokalajinPoistolause = yhteys.prepareStatement(POISTA_RESEPTIN_RUOKALAJIT);
            reseptinPoistolause.setInt(1, id);
            reseptinNimienPoistolause.setInt(1, id);
            reseptinRuokalajinPoistolause.setInt(1, id);
            reseptinNimienPoistolause.executeUpdate();
            reseptinRuokalajinPoistolause.executeUpdate();
            reseptinPoistolause.executeUpdate();
            yhteys.commit();
        } catch (SQLException ex) {
            try {
                yhteys.rollback();
                throw new GourmetException("Reseptin poisto epäonnistui: " + ex.getMessage());
            } catch (SQLException excep) {
                Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
                throw new GourmetException("Reseptin poisto epäonnistui: " + ex.getMessage());
            }
        } finally {
            try {
                yhteys.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TkResepti.class.getName()).log(Level.SEVERE, null, ex);
                throw new GourmetException("Yhteyden automatisointi epäonnistui: " + ex.getMessage());
            }
            Tietokanta.suljeYhteys(yhteys);
        }
    }
}
