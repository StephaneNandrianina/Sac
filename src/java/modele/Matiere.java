/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hp elitebook 840 G4
 */
public class Matiere {
    int idMatiere;
    String nomMatiere;

    public Matiere() {
    }

    public Matiere(int idMatiere, String nomMatiere) {
        this.idMatiere = idMatiere;
        this.nomMatiere = nomMatiere;
    }

    public Matiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }
    
    public void ajoutMatiere() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into matiere values(default,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setString(1, this.getNomMatiere());
                
               
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    public static List<Matiere> listMatiere() throws Exception {
    Connection co = null;
    Statement state = null;
    ResultSet resultat = null;
    List<Matiere> mat = new ArrayList<>();

    try {
        co = new Connexion().ConnectPostgres();
        state = co.createStatement();
        resultat = state.executeQuery("SELECT * FROM matiere");

        while (resultat.next()) {
            Matiere m = new Matiere();
            m.setIdMatiere(resultat.getInt(1));
            m.setNomMatiere(resultat.getString(2));
            

            mat.add(m);
        }

        if (mat.isEmpty()) {
            throw new Exception("Il n'y a pas de matériel inscrit.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new Exception("Il y a une erreur sur l'affichage de la liste des matériaux : " + e.getMessage());
    } finally {
        try {
            if (resultat != null) {
                resultat.close();
            }
            if (state != null) {
                state.close();
            }
            if (co != null) {
                co.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return mat;
} 
    
    
    public static List<Matiere> recupererMatieresParProduitId(int produitId) throws Exception {
        List<Matiere> matieres = new ArrayList<>();
        String sql = "SELECT * FROM matieres_par_produit WHERE idProduit = ?";

        try (Connection conex = new Connexion().ConnectPostgres();
             PreparedStatement preparedStatement = conex.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, produitId);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
      
            while (resultSet.next()) {
                int idMatiere = resultSet.getInt("idMatiere");
                String nomMatiere = resultSet.getString("nomMatiere");
                Matiere matiere = new Matiere(idMatiere, nomMatiere);
                matieres.add(matiere);
            }
           }   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return matieres;
    }

public Matiere getNameById(int idMatiere) throws Exception {
    
    String sql = "SELECT matiere.nommatiere FROM matiere where idMatiere = ?";

    // Utilisation d'un bloc try-with-resources pour assurer la fermeture automatique de la connexion
    try (Connection conex = new Connexion().ConnectPostgres();
         PreparedStatement preparedStatement = conex.prepareStatement(sql)) {

        preparedStatement.setInt(1, idMatiere);
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // Modification des noms des colonnes pour correspondre à la requête SQL
                
                String nomMatiere = resultSet.getString("nommatiere");
                Matiere matiere = new Matiere(nomMatiere);
                return matiere;
            }
        }

    } catch (SQLException e) {
        // Gérer les exceptions SQL
        e.printStackTrace();
        throw new Exception("Erreur lors de l'accès à la base de données.", e);
    }

    return null;
}
 
}
