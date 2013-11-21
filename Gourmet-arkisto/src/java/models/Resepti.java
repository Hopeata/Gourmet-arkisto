/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Valeria
 */
public class Resepti {

    private int id;
    private Date lisaysaika;
    private String ohje;
    private String kuvaUrl;
    private int tekija;
    private int paaraakaAine;
    private List<ReseptinNimi> nimet;

    public Resepti(int id, Date lisaysaika, String ohje, String kuvaUrl,
            int tekija, int paaraakaAine, List<ReseptinNimi> nimet) {
        this.id = id;
        this.lisaysaika = lisaysaika;
        this.ohje = ohje;
        this.kuvaUrl = kuvaUrl;
        this.tekija = tekija;
        this.paaraakaAine = paaraakaAine;
        this.nimet = nimet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLisaysaika() {
        return lisaysaika;
    }

    public void setLisaysaika(Date lisaysaika) {
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

    public int getTekija() {
        return tekija;
    }

    public void setTekija(int tekija) {
        this.tekija = tekija;
    }

    public int getPaaraakaAine() {
        return paaraakaAine;
    }

    public void setPaaraakaAine(int paaraakaAine) {
        this.paaraakaAine = paaraakaAine;
    }

    public List<ReseptinNimi> getNimet() {
        return nimet;
    }

    public void setNimet(List<ReseptinNimi> nimet) {
        this.nimet = nimet;
    }
    
    
    public String haePaanimi() {
        String paanimi = null;
        for (ReseptinNimi reseptinNimi : nimet) {
            if (reseptinNimi.isOnPaanimi()) {
                paanimi = reseptinNimi.getNimi();
            }
        }
        return paanimi;
    }
}
