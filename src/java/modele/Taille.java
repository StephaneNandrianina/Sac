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
public class Taille {
    int idTaille;
    String nomTaille;

    public int getIdTaille() {
        return idTaille;
    }

    public void setIdTaille(int idTaille) {
        this.idTaille = idTaille;
    }

    public String getNomTaille() {
        return nomTaille;
    }

    public void setNomTaille(String nomTaille) {
        this.nomTaille = nomTaille;
    }

    public Taille() {
    }

    public Taille(int idTaille, String nomTaille) {
        this.idTaille = idTaille;
        this.nomTaille = nomTaille;
    }

    public Taille(String nomTaille) {
        this.nomTaille = nomTaille;
    }
    
    public void ajoutTaille() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into taille values(default,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setString(1, this.getNomTaille());
                
               
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    public List<Taille> listeTaille() throws Exception {
        Connection co=new Connexion().ConnectPostgres();
        List<Taille> genres=new ArrayList<>();
        String sql="select * from taille";
        Statement state = null;
        ResultSet resultat = null;
        boolean open = false;
        try {
        state = co.createStatement();
        resultat = state.executeQuery(sql);
        Taille g = null;
        while (resultat.next()) {
            g = new Taille();
            g.setIdTaille(resultat.getInt(1));
            g.setNomTaille(resultat.getString(2));
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
