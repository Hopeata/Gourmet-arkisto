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
    
    
    
}