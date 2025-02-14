/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author hp elitebook 840 G4
 */
public class Profil {
    int idProfil;
    String nomProfil;
    int niveau ;

    public int getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public String getNomProfil() {
        return nomProfil;
    }

    public void setNomProfil(String nomProfil) {
        this.nomProfil = nomProfil;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public Profil() {
    }

    public Profil(int idProfil, String nomProfil, int niveau) {
        this.idProfil = idProfil;
        this.nomProfil = nomProfil;
        this.niveau = niveau;
    }

    public Profil(String nomProfil, int niveau) {
        this.nomProfil = nomProfil;
        this.niveau = niveau;
    }
    
    public void ajoutProfil() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into profil  values(default,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setInt(1, this.getIdProfil());
                pstmt.setString(2, this.getNomProfil());
                pstmt.setInt(3, this.getNiveau());
                
             
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
}
