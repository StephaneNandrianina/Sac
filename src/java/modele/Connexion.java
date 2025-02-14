/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;
import java.sql.*;
/**
 *
 * @author hp elitebook 840 G4
 */
public class Connexion {
    public static Connection ConnectPostgres() throws Exception {
        Connection connect = null;
        try {
          Class.forName("org.postgresql.Driver");
          connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sac", "postgres", "0000");
        } catch (Exception e) {
          throw e;
        }
        return connect;
    }
}
