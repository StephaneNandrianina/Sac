/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.sql.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author hp elitebook 840 G4
 */
public class Employe {
    int idEmploye;
    String nomEmploye;
    String prenomEmploye;
    Date dateDenaissance;

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public Date getDateDenaissance() {
        return dateDenaissance;
    }

    public void setDateDenaissance(Date dateDenaissance) {
        this.dateDenaissance = dateDenaissance;
    }
    
    public void setDate(String dates)throws Exception {
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dates);
            this.dateDenaissance = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace(); // Ou gérer l'exception de manière appropriée selon votre application
        }
    }

    public Employe() {
    }

    public Employe(String nomEmploye, String prenomEmploye, String dateDenaissance) throws Exception {
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.setDate(dateDenaissance);
    }
    
    public Employe(String nomEmploye, String prenomEmploye, Date dateDenaissance) {
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.dateDenaissance = dateDenaissance;
    }

    public Employe(int idEmploye, String nomEmploye, String prenomEmploye, Date dateDenaissance) {
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.dateDenaissance = dateDenaissance;
    }
    
    public void ajoutEmpoye () throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into employe values(default,?,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);
            
                
                pstmt.setString(1, this.getNomEmploye());
                pstmt.setString(2, this.getPrenomEmploye());
                pstmt.setDate(3, this.getDateDenaissance());
                
               
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
}
