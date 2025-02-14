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
public class MatierePrix {
    int idMatierePrix;
    int idMatiere;
    double MatierePrix;

    public MatierePrix() {
    }

    public MatierePrix(int idMatierePrix, int idMatiere, double MatierePrix) {
        this.idMatierePrix = idMatierePrix;
        this.idMatiere = idMatiere;
        this.MatierePrix = MatierePrix;
    }

    public MatierePrix(int idMatiere, double MatierePrix) {
        this.idMatiere = idMatiere;
        this.MatierePrix = MatierePrix;
    }

    public int getIdMatierePrix() {
        return idMatierePrix;
    }

    public void setIdMatierePrix(int idMatierePrix) {
        this.idMatierePrix = idMatierePrix;
    }

    public int getIdMatiere() {
        return idMatiere;
    }

    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }

    public double getMatierePrix() {
        return MatierePrix;
    }

    public void setMatierePrix(double MatierePrix) {
        this.MatierePrix = MatierePrix;
    }
    
    
    public void ajoutMatierePrix() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into matiereprix values(default,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);
          
                pstmt.setInt(1, this.getIdMatiere());
                pstmt.setDouble(2, this.getMatierePrix());
            
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
}
