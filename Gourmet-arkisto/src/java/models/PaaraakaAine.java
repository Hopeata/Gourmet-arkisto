/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Valeria
 */
public class PaaraakaAine {

    private int id;
    private String paaraakaAine;

    public PaaraakaAine(int id, String paaraakaAine) {
        this.id = id;
        this.paaraakaAine = paaraakaAine;
    }

    public int getId() {
        return id;
    }

    public String getPaaraakaAine() {
        return paaraakaAine;
    }
}
