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
public class VoirGenre {
    String nomClient;
    String prenomClient;
    int idGenre;
    String nomGenre;
    int idProduit;
    int idVente;
    Date dates;

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
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

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public VoirGenre() {
    }

    public VoirGenre(String nomClient, String prenomClient, int idGenre, String nomGenre, int idProduit, int idVente, Date dates) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.idGenre = idGenre;
        this.nomGenre = nomGenre;
        this.idProduit = idProduit;
        this.idVente = idVente;
        this.dates = dates;
    }
    public VoirGenre(String nomClient, String prenomClient, int idGenre, String nomGenre, int idProduit, int idVente, String dates) throws Exception {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.idGenre = idGenre;
        this.nomGenre = nomGenre;
        this.idProduit = idProduit;
        this.idVente = idVente;
        this.setDate (dates);
    }
    
    public void setDate(String dates)throws Exception {
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dates);
            this.dates = new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            e.printStackTrace(); // Ou gérer l'exception de manière appropriée selon votre application
        }
    }
    
    public List<VoirGenre> listeGenreVente() throws Exception{
        List<VoirGenre> liste = new ArrayList<>();
       String sql = "SELECT * FROM V_ClientGenreVente ";

       try (Connection conex = new Connexion().ConnectPostgres();
            PreparedStatement preparedStatement = conex.prepareStatement(sql)) {
           
           try (ResultSet resultSet = preparedStatement.executeQuery()) {
     
           while (resultSet.next()) {
               String nomClient = resultSet.getString("nomclient");
               String prenomClient = resultSet.getString("prenomclient");
               int idGenre = resultSet.getInt ("idgenre") ;
               String nomGenre= resultSet.getString ("nomgenre") ;        
               int idProduit = resultSet.getInt("idproduit");
               int idVente = resultSet.getInt("idvente");
               Date dates = resultSet.getDate("date");
               
               VoirGenre listepersVente = new VoirGenre(nomClient,prenomClient,idGenre,nomGenre,idProduit, idVente , dates );
               liste.add(listepersVente);
           }
          }   
       } catch (SQLException e) {
           e.printStackTrace();
       }
        
       return liste;
   }
    
    
    
}
