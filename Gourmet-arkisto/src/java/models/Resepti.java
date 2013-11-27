/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Valeria
 */
public class Resepti {

    private int id;
    private Timestamp lisaysaika;
    private String ohje;
    private String kuvaUrl;
    private Kayttaja tekija;
    private PaaraakaAine paaraakaAine;
    private List<ReseptinNimi> nimet;
    private List<Ruokalaji> ruokalajit;

    public Resepti(int id, Timestamp lisaysaika, String ohje, String kuvaUrl,
            Kayttaja tekija, PaaraakaAine paaraakaAine, List<ReseptinNimi> nimet, List<Ruokalaji> ruokalajit) {
        this.id = id;
        this.lisaysaika = lisaysaika;
        this.ohje = ohje;
        this.kuvaUrl = kuvaUrl;
        this.tekija = tekija;
        this.paaraakaAine = paaraakaAine;
        this.nimet = nimet;
        this.ruokalajit = ruokalajit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getLisaysaika() {
        return lisaysaika;
    }

    public void setLisaysaika(Timestamp lisaysaika) {
        this.lisaysaika = lisaysaika;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }

    public String getKuvaUrl() {
        return kuvaUrl;
    }

    public void setKuvaUrl(String kuvaUrl) {
        this.kuvaUrl = kuvaUrl;
    }

    public Kayttaja getTekija() {
        return tekija;
    }

    public void setTekija(Kayttaja tekija) {
        this.tekija = tekija;
    }

    public PaaraakaAine getPaaraakaAine() {
        return paaraakaAine;
    }

    public void setPaaraakaAine(PaaraakaAine paaraakaAine) {
        this.paaraakaAine = paaraakaAine;
    }

    public List<ReseptinNimi> getNimet() {
        return nimet;
    }

    public void setNimet(List<ReseptinNimi> nimet) {
        this.nimet = nimet;
    }

    public String getPaanimi() {
        String paanimi = null;
        for (ReseptinNimi reseptinNimi : nimet) {
            if (reseptinNimi.isOnPaanimi()) {
                paanimi = reseptinNimi.getNimi();
            }
        }
        return paanimi;
    }

    public String getPaaraakaAineNimi() {
        if (paaraakaAine != null) {
            return paaraakaAine.getPaaraakaAine();
        } else {
            return "";
        }
    }

    public List<Ruokalaji> getRuokalajit() {
        return ruokalajit;
    }

    public void setRuokalajit(List<Ruokalaji> ruokalajit) {
        this.ruokalajit = ruokalajit;
    }
}
