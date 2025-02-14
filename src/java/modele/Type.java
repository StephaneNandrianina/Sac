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
public class Type {
    int idType;
    String nomType;

    public Type() {
    }

    public Type(int idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }

    public Type(String nomType) {
        this.nomType = nomType;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
    
    
    public void ajoutType() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into type values(default,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setString(1, this.getNomType());
                
               
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    public List<Type> listeType() throws Exception {
        Connection co=new Connexion().ConnectPostgres();
        List<Type> genres=new ArrayList<>();
        String sql="select * from type";
        Statement state = null;
        ResultSet resultat = null;
        boolean open = false;
        try {
        state = co.createStatement();
        resultat = state.executeQuery(sql);
        Type g = null;
        while (resultat.next()) {
            g = new Type();
            g.setIdType(resultat.getInt(1));
            g.setNomType(resultat.getString(2));
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
