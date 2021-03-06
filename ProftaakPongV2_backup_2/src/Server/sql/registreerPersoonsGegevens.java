/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server.sql;

import server.data.DatabaseConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */


public class registreerPersoonsGegevens extends DatabaseConnector implements Runnable
{
    /**
     * variabelen met een logische naam
     */
    private final String username;
    private final String wachtwoord;
    
    /**
 * de constructor
 * @author hsm
 */
    public registreerPersoonsGegevens(String username, String wachtwoord)
    {
        super();
        this.username = username;
        this.wachtwoord = wachtwoord;
    }

    @Override
    public void run() 
    {
        try 
        {
            super.verbindmetDatabase();
        } 
        catch (ClassNotFoundException | InstantiationException | SQLException | IllegalAccessException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            String query = "INSERT INTO persoon (GEBRUIKERSNAAM, WACHTWOORD)" 
                    + " values (?,?)";
            String Invoerwachtwoord = wachtwoord;
            String Invoergebruikersnaam = username;
            PreparedStatement prest = conn.prepareStatement(query);
            prest.setString(1, Invoergebruikersnaam);
            prest.setString(2, Invoerwachtwoord);
            
            prest.execute();
            
            DecimalFormat dec = new DecimalFormat("#.#");
            int scoreResult = 0;
            scoreResult = ((5 * 15) + (4 * 15) + (3 * 15) + (2 * 15) + (1 * 15)) / 15;
                dec.format(scoreResult);
            String query2 = "INSERT INTO score (GEBRUIKERSNAAM, SCORE, ROUND1, ROUND2, ROUND3, ROUND4, ROUND5)" 
                    + " values (?,?,?,?,?,?,?)";
            PreparedStatement prest2 = conn.prepareStatement(query2);
            prest2.setString(1, username);
            prest2.setDouble(2, 15);
            prest2.setInt(3, 15);
            prest2.setInt(4, 15);
            prest2.setInt(5, 15);
            prest2.setInt(6, 15);
            prest2.setInt(7, 15);
            
            prest2.execute();
        }
        catch(SQLException e)
        {
            e.getMessage();
            System.out.println("db fout");
        }
        finally
        {
            super.verbindingverbrekenmetDatabase();
        }
    }   

}
