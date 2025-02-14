/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

/**
 *
 * @author hp elitebook 840 G4
 */
public class ListeMatiereSelonLook {
    String look;
    String matiere;

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public ListeMatiereSelonLook(String nomProduit, String nomTaille, String nomMatiere, int quantite) {
    }

    public ListeMatiereSelonLook(String look, String matiere) {
        this.look = look;
        this.matiere = matiere;
    }
    
    public static List<ListeMatiereSelonLook> listeMatieres (int idLook) throws Exception {
       List<ListeMatiereSelonLook> matieres = new ArrayList<>(); 
       String sql = "SELECT * FROM V_MatiereParLook WHERE idlook=?";
       
    try (Connection conex = new Connexion().ConnectPostgres();
         PreparedStatement preparedStatement = conex.prepareStatement(sql)) {

        preparedStatement.setInt(1, idLook);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                // Modification des noms des colonnes pour correspondre à la requête SQL
                String nomMatiere= resultSet.getString("nomMatiere");
                

                // Création de l'objet Matiere avec les résultats
               ListeMatiereSelonLook matiereLook = new ListeMatiereSelonLook(nomMatiere);
                matieres.add(matiereLook );
            }
        }

    } catch (SQLException e) {
        // Gérer les exceptions SQL
        e.printStackTrace();
        throw new Exception("Erreur lors de l'accès à la base de données.", e);
    }

    return matieres;
}

    public ListeMatiereSelonLook(String matiere) {
        this.matiere = matiere;
    }

}
