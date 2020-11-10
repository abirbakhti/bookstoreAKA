/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Singleton {
      
   private static  Connection con = null;




    public static Connection connecter()
    {
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
                
                con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bookstore","root","");
            
	    }catch(ClassNotFoundException e) 
	    
            {System.out.println(e.getMessage());
	      }catch (SQLException e) {
		e.printStackTrace();
	      }
	return con;
   }
   public static Connection getConnection()
     {
         if(con==null)
             con=connecter();
         return con;
     }
}
