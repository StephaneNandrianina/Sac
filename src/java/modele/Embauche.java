/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author hp elitebook 840 G4
 */
public class Embauche {
    int idEmbauche;
    int idEmploye;
    int idProfil;
    double tauxHoraire;

    public Embauche() {
    }

    public Embauche(int idEmbauche, int idEmploye, int idProfil, double tauxHoraire) {
        this.idEmbauche = idEmbauche;
        this.idEmploye = idEmploye;
        this.idProfil = idProfil;
        this.tauxHoraire = tauxHoraire;
    }

    public Embauche(int idEmploye, int idProfil, double tauxHoraire) {
        this.idEmploye = idEmploye;
        this.idProfil = idProfil;
        this.tauxHoraire = tauxHoraire;
    }

    public int getIdEmbauche() {
        return idEmbauche;
    }

    public void setIdEmbauche(int idEmbauche) {
        this.idEmbauche = idEmbauche;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
    
//    public List<Embauche> listeEmbauche (Connection co , int niveau) throws Exception {
//        if(co == null || co.isClosed()){
//            co = Connexion.ConnectPostgres();
//         }
//        Profil profil = new Profil ();
//        niveau = profil.getIdProfil();
//        List<Embauche> listes = new ArrayList<>();
//        
//        
//        
//    }
    
}
