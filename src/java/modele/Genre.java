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
public class Genre {
    int idGenre;
    String nomGenre;

    public Genre() {
    }

    public Genre(int idGenre, String nomGenre) {
        this.idGenre = idGenre;
        this.nomGenre = nomGenre;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    public Genre(String nomGenre) {
        this.nomGenre = nomGenre;
    }
    
    
    public void ajoutGenre() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into genre values(default,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setString(1, this.getNomGenre());
                
               
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    
    public List<Genre> listeGenre() throws Exception {
        Connection co=new Connexion().ConnectPostgres();
        List<Genre> genres=new ArrayList<>();
        String sql="select * from genre";
        Statement state = null;
        ResultSet resultat = null;
        boolean open = false;
        try {
        state = co.createStatement();
        resultat = state.executeQuery(sql);
        Genre g = null;
        while (resultat.next()) {
            g = new Genre();
            g.setIdGenre(resultat.getInt(1));
            g.setNomGenre(resultat.getString(2));
            genres.add(g);
        }
        if (genres.isEmpty()) {
          throw new Exception("Il n' y a pas de genre inscrit ");
        }
      
        } catch (Exception e) {
             throw new Exception("Il y a une erreur sur l'affichage de la liste des genres");
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
