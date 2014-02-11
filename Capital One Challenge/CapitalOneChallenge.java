/*
 * CapitalOneChallenege uses the JDBC in order to optimize efficiency for the problem.
 * A connection to a MySQL database server is established and passed to create a database and table.
 * The .csv file is scanned into a String, a command to insert all of the data into the table.
 * Scanning the file is the majority of the execution time.
 * Three preselected MySQL queries are run against the database and the results are printed to terminal.
 *
 * In order for this program to function correctly, getConnection's parameters on line 36 must be replaced
 * with a legitimate database URL in the form of "jdbc:subprotocol:subname", a username, and a password
 * to that database. 
 * The MySQL connector driver can be found here: https://drive.google.com/file/d/0B8-2AZ8fsL3mRzdhQnB6QjVRbWs
 * It must be added to your IDE's library.
 *
 * A video of myself running the program is viewable at: http://youtu.be/cs4HLkuCaeQ
 *
 * @author Timothy Mendez
 * @version 1/27/2014 - 1/28/2014
 * tmendez@calpoly.edu
 **/
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import java.util.*;
import java.io.*;

public class CapitalOneChallenge
{
   public static void main(String[] args) {
      Connection cnc = null;

      try {
         //Though in reality, all three parameters will be prompted for due to security risks of hardcoding in a DB URL and user credentials
         cnc = DriverManager.getConnection("jdbc:mysql://", "USERNAME", "PASSWORD");
 
         createDatabase(cnc);
         fillData(cnc);
         determineThings(cnc);

         cnc.close();
      }
      catch(Exception e) {
         System.out.println(e.getMessage());
      }
   }

  /* Takes in a connection.
   * Executes 3 separate queries and prints results of each.
   **/
   static void determineThings(Connection cnc) {
      Statement stm = null;
      ResultSet rst = null;
      String target = "SELECT City, 2012Population/2010Population Growth FROM MetroData WHERE 2010Population > 50000 GROUP BY City ORDER BY Growth DESC LIMIT 5;";
      String avoid = "SELECT City, 2012Population/2010Population Growth FROM MetroData WHERE 2010Population > 50000 GROUP BY City ORDER BY Growth ASC LIMIT 5;";
      String cumulative = "SELECT State, SUM(2012Population - 2010Population) Growth FROM MetroData GROUP BY State ORDER BY Growth DESC LIMIT 5;";

      try {
         stm = cnc.createStatement();

         //First problem
         rst = stm.executeQuery(target);
         System.out.println("Top five cities to target based on highest population growth");
         for(int i = 1; rst.next(); i++)
            System.out.println(i + ". " + rst.getNString(1));

         //Second problem
         rst = stm.executeQuery(avoid);
         System.out.println("\nTop five cities to avoid based on the most shrinking population");
         for(int i = 1; rst.next(); i++)
            System.out.println(i + ". " + rst.getNString(1));

         //Third problem
         rst = stm.executeQuery(cumulative);
         System.out.println("\nTop five states with highest cumulative growth ");
         for(int i = 1; rst.next(); i++)
            System.out.println(i + ". " + rst.getNString(1));

         rst.close();
         stm.close();
      }
      catch(SQLException e) {
         System.out.println(e.getMessage());
      }
   }

  /* Takes in a connection.
   * Creates a new database MetroPopulations and a new relation MetroData.
   * MetroData has 5 attributes - City, State, 2010 Population, 2011 Population, 2012 Population.
   * MetroData's primary key is (City, State).
   **/
   static void createDatabase(Connection cnc) {
      Statement stm = null;
      String createDB1 = "DROP DATABASE IF EXISTS MetroPopulations;";
      String createDB2 = "CREATE DATABASE MetroPopulations;";
      String createDB3 = "USE MetroPopulations;";
      String createTBL = "CREATE TABLE MetroData (City VARCHAR(90) NOT NULL, State VARCHAR(20) NOT NULL, 2010Population int, 2011Population int, 2012Population int, PRIMARY KEY(City, State));"; 

      try {
         stm = cnc.createStatement();
         stm.execute(createDB1);
         stm.execute(createDB2);
         stm.execute(createDB3);
         stm.execute(createTBL);

         stm.close();
      }
      catch(SQLException e) {
         System.out.println(e.getMessage());
      }
   }

  /* Takes in a connection.
   * Scans in the data.
   * Inserts all of the data at once into the MetroData relation in the MetroPopulations database.
   **/
   static void fillData(Connection cnc) {
      Statement stm = null;
      String tmp, ins = "INSERT INTO MetroData (City, State, 2010Population, 2011Population, 2012Population) VALUES ";

      try {
         Scanner scan = new Scanner(new File("Metropolitan_Populations__2010-2012_.csv"));
         stm = cnc.createStatement();
         scan.nextLine(); //eating first line with no data
  
         while(scan.hasNext()) {
            tmp = scan.nextLine();

            if(tmp.contains(";")) //edge case: cities with commas in the name
               tmp = tmp.replaceFirst("; ", "\",\""); //tmp now holds "City","State",pop0,pop1,pop2
            else
               tmp = tmp.replaceFirst(", ", "\",\""); //tmp now holds "City","State",pop0,pop1,pop2

            ins += "(" + tmp + "),"; //updating insert statement, only using one SQL command for speed
         }
         ins = ins.substring(0, ins.length() - 1);
         ins += ";";
         stm.execute(ins);

         stm.close();
      }
      catch(SQLException e) {
         System.out.println(e.getMessage());
      }
      catch(FileNotFoundException e) {
         System.out.println("File not found. Make sure 'Metropolitan_Populations__2010-2012_.csv' is in the root directory");
      }
   }
}
