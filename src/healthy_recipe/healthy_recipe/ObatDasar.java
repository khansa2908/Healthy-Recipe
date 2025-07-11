/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthy_recipe.healthy_recipe;

/**
 * Kelas `ObatDasar` merupakan kelas dasar yang merepresentasikan informasi dasar mengenai obat.
 * Kelas ini memiliki properti untuk menyimpan nama obat dan dosisnya.
 
 */
public class ObatDasar {
    protected String obat;
    protected String dosis;

    public void setObat(String obat) {
        this.obat = obat;
    }

    public String getObat() {
        return obat;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getDosis() {
        return dosis;
    }
    
}
