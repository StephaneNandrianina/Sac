/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author hp elitebook 840 G4
 */
public class ListeSacSelonMatiere {
    String nomProduit;
    String nomTaille;
    String nomMatiere;
    int quantite;

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getNomTaille() {
        return nomTaille;
    }

    public void setNomTaille(String nomTaille) {
        this.nomTaille = nomTaille;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public ListeSacSelonMatiere(String nomProduit, String nomTaille, String nomMatiere, int quantite) {
        this.nomProduit = nomProduit;
        this.nomTaille = nomTaille;
        this.nomMatiere = nomMatiere;
        this.quantite = quantite;
    }

    public ListeSacSelonMatiere() {
    }

    public static List<ListeSacSelonMatiere> listeSacParMatieres (int idMatiere) throws Exception {
       List<ListeSacSelonMatiere> listesSac = new ArrayList<>(); 
       String sql = "SELECT * FROM V_ListeSacParMatiere WHERE idMatiere=?";
       
    try (Connection conex = new Connexion().ConnectPostgres();
         PreparedStatement preparedStatement = conex.prepareStatement(sql)) {

        preparedStatement.setInt(1, idMatiere);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Modification des noms des colonnes pour correspondre à la requête SQL
                String nomProduit= resultSet.getString("nomProduit");
                String nomTaille = resultSet.getString("nomTaille");
                String nomMatiere = resultSet.getString("nomMatiere");
                int quantite = resultSet.getInt("quantitematiere");
                

                // Création de l'objet Matiere avec les résultats
               ListeSacSelonMatiere sacMatiere = new ListeSacSelonMatiere(nomProduit ,nomTaille  ,nomMatiere, quantite);
                listesSac.add(sacMatiere );
            }
        }

    } catch (SQLException e) {
        // Gérer les exceptions SQL
        e.printStackTrace();
        throw new Exception("Erreur lors de l'accès à la base de données.", e);
    }

    return listesSac;
}

}
