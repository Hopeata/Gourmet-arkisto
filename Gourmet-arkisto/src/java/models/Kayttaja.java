/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
/**
 *
 * @author Valeria
 */
public class Kayttaja {

    private int id;
    private String tunnus;
    private String sahkoposti;
    private boolean adminOikeudet;
    private boolean vipOikeudet;

    public Kayttaja(int id, String tunnus, String sahkoposti, 
            boolean adminOikeudet, boolean vipOikeudet) {
        this.id = id;
        this.tunnus = tunnus;
        this.sahkoposti = sahkoposti;
        this.adminOikeudet = adminOikeudet;
        this.vipOikeudet = vipOikeudet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTunnus() {
        return tunnus;
    }

    public void setTunnus(String tunnus) {
        this.tunnus = tunnus;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public boolean isAdminOikeudet() {
        return adminOikeudet;
    }

    public void setAdminOikeudet(boolean adminOikeudet) {
        this.adminOikeudet = adminOikeudet;
    }

    public boolean isVipOikeudet() {
        return vipOikeudet;
    }

    public void setVipOikeudet(boolean vipOikeudet) {
        this.vipOikeudet = vipOikeudet;
    }
    
    
    
}
