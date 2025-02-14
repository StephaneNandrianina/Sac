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
public class Client {
    int idClient;
    String nom;
    String prenom;
    Date dateDeNaissance;
    int genre;

    public Client(int idClient, String nom, String prenom, Date dateDeNaissance, int genre) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Client() {
    }

    public Client(String nom, String prenom, Date dateDeNaissance, int genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.genre = genre;
    }
    
    

    public void setDate(String dates)throws Exception {
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dates);
            this.dateDeNaissance = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace(); // Ou gérer l'exception de manière appropriée selon votre application
        }
    }
    
    public Client(String nom, String prenom, String dateDeNaissance, int genre) throws Exception{
        this.nom = nom;
        this.prenom = prenom;
        this.setDate(dateDeNaissance); 
        this.genre = genre;
    }    

    public void ajoutClient() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into client values(default,?,?,?,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setString(1, this.getNom());
                pstmt.setString(2, this.getPrenom());
                pstmt.setDate(3, this.getDateDeNaissance());
                pstmt.setInt(4, this.getGenre());
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
    public static List<Client> listClient() throws Exception {
    Connection co = null;
    Statement state = null;
    ResultSet resultat = null;
    List<Client> mat = new ArrayList<>();

    try {
        co = new Connexion().ConnectPostgres();
        state = co.createStatement();
        resultat = state.executeQuery("SELECT * FROM client");

        while (resultat.next()) {
            Client c = new Client();
            c.setIdClient(resultat.getInt(1));
            c.setNom(resultat.getString(2));
            

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