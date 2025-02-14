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
public class Vente {
    int idVente;
    int idProduit;
    int idClient;
    Date date;

    public Vente() {
    }

    public Vente(int idVente, int idProduit, int idClient, Date date) {
        this.idVente = idVente;
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.date = date;
    }

    public Vente(int idProduit, int idClient, Date date) {
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.date = date;
    }

    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    
     public Vente(int idProduit, int idClient, String date) throws Exception {
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.setDate(date);
    }
     
     public void ajoutVente() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into vente values(default,?,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setInt(1, this.getIdProduit());
                pstmt.setInt(2, this.getIdClient());
                pstmt.setDate(3, this.getDate());
                
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    public static List<Vente> listVente() throws Exception {
    Connection co = null;
    Statement state = null;
    ResultSet resultat = null;
    List<Vente> mat = new ArrayList<>();

    try {
        co = new Connexion().ConnectPostgres();
        state = co.createStatement();
        resultat = state.executeQuery("SELECT * FROM vente");

        while (resultat.next()) {
            Vente c = new Vente();
            c.setIdVente(resultat.getInt(1));
            c.setIdProduit(resultat.getInt(2));
            c.setIdClient(resultat.getInt(3));
            c.setDate(resultat.getDate(4));
            

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
