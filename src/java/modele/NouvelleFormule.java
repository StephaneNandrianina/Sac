/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp elitebook 840 G4
 */
public class NouvelleFormule {
    int  idNouvelleFormule;
    int idProduit;
    int idTaille;
    int idMatiere;
    int quantiteMatiere;

    String nomProduit;
    
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
    String nomTaille;
    
    public NouvelleFormule() {
    }

    public int getIdNouvelleFormule() {
        return idNouvelleFormule;
    }

    public void setIdNouvelleFormule(int idNouvelleFormule) {
        this.idNouvelleFormule = idNouvelleFormule;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public int getQuantiteMatiere() {
        return quantiteMatiere;
    }

    public void setQuantiteMatiere(int quantiteMatiere) {
        this.quantiteMatiere = quantiteMatiere;
    }

    public NouvelleFormule(int idNouvelleFormule, int idProduit, int idTaille, int idMatiere, int quantiteMatiere) {
        this.idNouvelleFormule = idNouvelleFormule;
        this.idProduit = idProduit;
        this.idTaille = idTaille;
        this.idMatiere = idMatiere;
        this.quantiteMatiere = quantiteMatiere;
    }

    public NouvelleFormule(int idProduit, int idTaille, int idMatiere, int quantiteMatiere) {
        this.idProduit = idProduit;
        this.idTaille = idTaille;
        this.idMatiere = idMatiere;
        this.quantiteMatiere = quantiteMatiere;
    }
    
    public void ajoutDeNouvelleFormule() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into nouvelleformule values(default,?,?,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setInt(1, this.getIdProduit());
                pstmt.setInt(2, this.getIdTaille());
                pstmt.setInt(3, this.getIdMatiere());
                pstmt.setInt(4, this.getQuantiteMatiere());
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    public static List<NouvelleFormule> listFormule() throws Exception {
    Connection co = null;
    Statement state = null;
    ResultSet resultat = null;
    List<NouvelleFormule> mat = new ArrayList<>();

    try {
        co = new Connexion().ConnectPostgres();
        state = co.createStatement();
        resultat = state.executeQuery("SELECT * FROM V_ListeFormulle");

        while (resultat.next()) {
            NouvelleFormule m = new NouvelleFormule();
            m.setIdProduit(resultat.getInt(1));
            m.setNomProduit(resultat.getString(2));
            

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
    
    
}
