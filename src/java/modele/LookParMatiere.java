/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author hp elitebook 840 G4
 */
public class LookParMatiere {
    int lookParMatiere;
    int  idLook;
    int idMatiere;
    String nomLook;

    public int getLookParMatiere() {
        return lookParMatiere;
    }

    public void setLookParMatiere(int lookParMatiere) {
        this.lookParMatiere = lookParMatiere;
    }

    public String getNomLook() {
        return nomLook;
    }

    public void setNomLook(String nomLook) {
        this.nomLook = nomLook;
    }

    public LookParMatiere(int lookParMatiere, int idLook, int idMatiere, String nomLook) {
        this.lookParMatiere = lookParMatiere;
        this.idLook = idLook;
        this.idMatiere = idMatiere;
        this.nomLook = nomLook;
    }

    public LookParMatiere(String nomLook) {
        this.nomLook = nomLook;
    }

    public int getlookParMatiere() {
        return lookParMatiere;
    }

    public void setlookParMatiere(int lookParMatiere) {
        this.lookParMatiere = lookParMatiere;
    }

    public int getIdLook() {
        return idLook;
    }

    public void setIdLook(int idLook) {
        this.idLook = idLook;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

//  public void ajoutLook(String[] idMatiere) throws SQLException, Exception{
//        Connection co=new Connexion().ConnectPostgres();
    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public LookParMatiere(int lookParMatiere, int idLook, int idMatiere) {
        this.lookParMatiere = lookParMatiere;
        this.idLook = idLook;
        this.idMatiere = idMatiere;
    }

    public LookParMatiere() {
    }

    public LookParMatiere(int idLook, int idMatiere) {
        this.idLook = idLook;
        this.idMatiere = idMatiere;
    }

  public void ajoutLookParMatiere () throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into lookparmatiere values(default,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);
            
                pstmt.setInt(1, this.getIdLook());
                pstmt.setInt(2, this.getIdMatiere());
                
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
  
  public static List<LookParMatiere> listLookParMatiere() throws Exception {
    Connection co = null;
    Statement state = null;
    ResultSet resultat = null;
    List<LookParMatiere> mat = new ArrayList<>();

    try {
        co = new Connexion().ConnectPostgres();
        state = co.createStatement();
        resultat = state.executeQuery("SELECT * FROM v_LookEtLookParMatiere");

        while (resultat.next()) {
            LookParMatiere c = new LookParMatiere();
            c.setIdLook(resultat.getInt(1));
            c.setNomLook(resultat.getString(3));
            

            mat.add(c);
        }

        if (mat.isEmpty()) {
            throw new Exception("Il n'y a pas de client inscrit.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new Exception("Il y a une erreur sur l'affichage de la liste des client : " + e.getMessage());
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
   
