/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Valeria
 */
public class ReseptinNimi {
    
    private int reseptinId;
    private String nimi;
    private boolean onPaanimi;

    public ReseptinNimi(int reseptinId, String nimi, boolean onPaanimi) {
        this.reseptinId = reseptinId;
        this.nimi = nimi;
        this.onPaanimi = onPaanimi;
    }

    public int getReseptinId() {
        return reseptinId;
    }

    public void setReseptinId(int reseptinId) {
        this.reseptinId = reseptinId;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public boolean isOnPaanimi() {
        return onPaanimi;
    }

    public void setOnPaanimi(boolean onPaanimi) {
        this.onPaanimi = onPaanimi;
    }
  
    
}
