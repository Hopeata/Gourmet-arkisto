/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Valeria
 */
public class Ruokalaji {

    private int id;
    private String ruokalaji;
    private boolean checked;

    public Ruokalaji(int id, String ruokalaji) {
        this.id = id;
        this.ruokalaji = ruokalaji;
    }

    public int getId() {
        return id;
    }

    public String getRuokalaji() {
        return ruokalaji;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
