/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.sql.*;
import java.util.List;
/**
 *
 * @author hp elitebook 840 G4
 */
public class Main {
    public static void main (String args[ ]) throws Exception{
        try {
            VoirGenre vr = new VoirGenre();
            List<VoirGenre> listeGenres = vr.listeGenreVente();
            
            // Affichage des résultats pour vérification
            for (VoirGenre genre : listeGenres) {
                System.out.println(genre.getNomClient());
                System.out.println(genre.getPrenomClient());
                System.out.println(genre.getNomGenre());
                System.out.println(genre.getDates());
                System.out.println("//////////////////////////////////////////////");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
