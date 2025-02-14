/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hp elitebook 840 G4
 */
public class Fabrication {
    int idFabrication;
    int idProduit;
    int quantite;

    public int getIdFabrication() {
        return idFabrication;
    }

    public void setIdFabrication(int idFabrication) {
        this.idFabrication = idFabrication;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    Date date;
    
    public Fabrication() {
    }

    
    
    public void setDate(String dates)throws Exception {
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dates);
            this.date = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace(); // Ou gérer l'exception de manière appropriée selon votre application
        }
    }

    public Fabrication(int idFabrication, int idProduit, int quantite, Date date) {
        this.idFabrication = idFabrication;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.date = date;
    }

    public Fabrication(int idProduit, int quantite, Date date) {
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.date = date;
    }
    public Fabrication(int idProduit, int quantite, String date) throws Exception {
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.setDate(date);
    }
    
    
    public void ajoutFabricationSac() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into fabricationSac values(default,?,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);
                
                
                pstmt.setInt(1, this.getIdProduit());
                pstmt.setInt(2, this.getQuantite());
                pstmt.setDate(3, this.getDate());
                
                pstmt.execute();  
                
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
   
}
