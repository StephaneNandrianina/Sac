/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author hp elitebook 840 G4
 */
public class Look {
    int idLook;
    String nomLook;

    public int getIdLook() {
        return idLook;
    }

    public void setIdLook(int idLook) {
        this.idLook = idLook;
    }

    public String getNomLook() {
        return nomLook;
    }

    public void setNomLook(String nomLook) {
        this.nomLook = nomLook;
    }

    public Look(int idLook, String nomLook) {
        this.idLook = idLook;
        this.nomLook = nomLook;
    }

    public Look() {
    }

    public Look(String nomLook) {
        this.nomLook = nomLook;
    }
    
    public void ajoutLook() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into look values(default,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setString(1, this.getNomLook());
                
               
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    
    public List<Look> listeLook() throws Exception {
        Connection co=new Connexion().ConnectPostgres();
        List<Look> genres=new ArrayList<>();
        String sql="select * from look";
        Statement state = null;
        ResultSet resultat = null;
        boolean open = false;
        try {
        state = co.createStatement();
        resultat = state.executeQuery(sql);
        Look g = null;
        while (resultat.next()) {
            g = new Look();
            g.setIdLook(resultat.getInt(1));
            g.setNomLook(resultat.getString(2));
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
