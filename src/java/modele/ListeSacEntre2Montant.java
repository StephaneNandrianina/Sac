/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp elitebook 840 G4
 */
public class ListeSacEntre2Montant {
    int idProduit;
    String nomProduit;
    String nomType;
    String nomLook;
    double sommePrix;
    
         public ListeSacEntre2Montant(int idProduit, String nomProduit, String nomType, String nomLook, double sommePrix) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.nomType = nomType;
        this.nomLook = nomLook;
        this.sommePrix = sommePrix;
    }

    // Constructeur vide
    public ListeSacEntre2Montant() {
        // Vous pouvez laisser ce constructeur vide ou ajouter des initialisations par défaut si nécessaire
    }

    // Getters
    public int getIdProduit() {
        return idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public String getNomType() {
        return nomType;
    }

    public String getNomLook() {
        return nomLook;
    }

    public double getSommePrix() {
        return sommePrix;
    }

    // Setters
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public void setNomLook(String nomLook) {
        this.nomLook = nomLook;
    }

    public void setSommePrix(double sommePrix) {
        this.sommePrix = sommePrix;
    }
    
    public static List<ListeSacEntre2Montant> recupererProduitIdWithMontant(double montant1 , double montant2) throws Exception {
       List<ListeSacEntre2Montant> listeSac = new ArrayList<>();
       String sql = "SELECT * FROM V_totalPrixProduit where somme_prix BETWEEN ? AND ? ";

       try (Connection conex = new Connexion().ConnectPostgres();
            PreparedStatement preparedStatement = conex.prepareStatement(sql)) {
           
           preparedStatement.setDouble(1, montant1);
           preparedStatement.setDouble(2, montant2);
           
           try (ResultSet resultSet = preparedStatement.executeQuery()) {
     
           while (resultSet.next()) {
               int idProduit = resultSet.getInt("idproduit");
               String nomProduit = resultSet.getString("nom");
               String nomType = resultSet.getString ("nomType") ;
               String nomLook= resultSet.getString ("nomLook") ;        
               String sommePrix = resultSet.getString("somme_prix");
               
               ListeSacEntre2Montant sac = new ListeSacEntre2Montant(idProduit,nomProduit,nomType,nomLook,Double.parseDouble(sommePrix) );
               listeSac.add(sac);
           }
          }   
       } catch (SQLException e) {
           e.printStackTrace();
       }
        
       return listeSac;
   }

}
