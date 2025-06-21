/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package healthy_recipe.healthy_recipe;

/**
 * Interface untuk login yang harus mengimplementasikan method login().
 */
public interface Loginable {
    /**
     * Melakukan proses login berdasarkan input yang diberikan.
     *
     * @param username Username yang dimasukkan
     * @param password Password yang dimasukkan
     * @param role     Peran (dokter/staff farmasi)
     * @return true jika login berhasil, false jika tidak
     */
    boolean login(String username, String password, String role);
}
