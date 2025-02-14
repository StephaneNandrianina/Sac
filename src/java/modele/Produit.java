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
public class Produit {
    int idProduit;
    String nomProduit;
    int idType;
    int idLook;
    
    double prix;

    public Produit(int idProduit, double prix) {
        this.idProduit = idProduit;
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public int getIdLook() {
        return idLook;
    }

    public void setIdLook(int idLook) {
        this.idLook = idLook;
    }

    public Produit() {
    }

    public Produit(int idProduit, String nomProduit, int idType, int idLook) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.idType = idType;
        this.idLook = idLook;
    }

    public Produit(String nomProduit, int idType, int idLook) {
        this.nomProduit = nomProduit;
        this.idType = idType;
        this.idLook = idLook;
    }
    
  public void ajoutProduit() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into produit values(default,?,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);
            
                
                pstmt.setString(1, this.getNomProduit());
                pstmt.setInt(2, this.getIdType());
                pstmt.setInt(3, this.getIdLook());
            
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }

  
  public List<Produit> listeProduit() throws Exception {
        Connection co=new Connexion().ConnectPostgres();
        List<Produit> genres=new ArrayList<>();
        String sql="select * from produit";
        Statement state = null;
        ResultSet resultat = null;
        boolean open = false;
        try {
        state = co.createStatement();
        resultat = state.executeQuery(sql);
        Produit g = null;
        while (resultat.next()) {
            g = new Produit();
            g.setIdProduit(resultat.getInt(1));
            g.setNomProduit(resultat.getString(2));
            
            genres.add(g);
        }
        if (genres.isEmpty()) {
          throw new Exception("Il n' y a pas de look inscrit ");
        }
      
        } catch (Exception e) {
             throw new Exception("Il y a une erreur sur l'affichage de la liste des looks");
        } finally {
            if (resultat != null)
            resultat.close();
            if (state != null)
            state.close();
            if (open)
            co.close();
        }
        return genres;
    }
  
 

            
}
