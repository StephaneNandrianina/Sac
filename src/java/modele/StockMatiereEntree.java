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
public class StockMatiereEntree {
    Date dates;
    int matiere;
    int quantite;
    double prix;

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public int getMatiere() {
        return matiere;
    }

    public void setMatiere(int matiere) {
        this.matiere = matiere;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public StockMatiereEntree( int quantite) {
        this.quantite = quantite;
        
    }
    
    public StockMatiereEntree( ) {
        
        
    }
    
    public StockMatiereEntree(Date dates, int matiere, int quantite,double prix) {
        this.dates = dates;
        this.matiere = matiere;
        this.quantite = quantite;
        this.prix = prix;
    }
    
    public StockMatiereEntree(String dates, int matiere, int quantite,double prix) throws Exception {
        this.setDate(dates);
        this.matiere = matiere;
        this.quantite = quantite;
        this.prix = prix;
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
    
    public void ajoutStockMatiereEntree() throws SQLException, Exception{
        Connection co=new Connexion().ConnectPostgres();
        String sql="insert into stockmatiereentree values(default,?,?,?,0,?)";
        try {
            PreparedStatement pstmt = co.prepareStatement(sql);

                pstmt.setDate(1, this.getDates());
                pstmt.setInt(2, this.getMatiere());
                pstmt.setInt(3, this.getQuantite());
                pstmt.setDouble(4, this.getPrix());
                
                
                pstmt.execute();  
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            co.close();
        }
    }
    
   public double getStockMatiere(int idMatiere) throws Exception{
       String sql = "SELECT stockmatiereentree.quantite FROM stockmatiereentree WHERE idmatiere = ?";
       
       try (Connection conex = new Connexion().ConnectPostgres();
         PreparedStatement preparedStatement = conex.prepareStatement(sql)) { // Utilisation de la variable sql ici

        preparedStatement.setInt(1, idMatiere);
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                // Modification des noms des colonnes pour correspondre à la requête SQL
                
                int quantite  = resultSet.getInt("quantite");
                return quantite;
            }
        }

    } catch (SQLException e) {
        // Gérer les exceptions SQL
        e.printStackTrace();
        throw new Exception("Erreur lors de l'accès à la base de données.", e);
    }

    return 0;
}
   
   
    
}
