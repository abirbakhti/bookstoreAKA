package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ENTITIES.Client;


public class DaoClient {
	
	/*********************************** Ajouter un client *****************************************/

	public int ajouterClient(Client c) {
		PreparedStatement stmt = null;
        ResultSet rs = null ;
		int id = 0 ;

	

		try {

		
				stmt = Singleton.getConnection().prepareStatement(
						"INSERT INTO `client` (`nom`, `prenom`, `email`, `tel`, `adresse`) VALUES ('"+ c.getNom()+ "','" + c.getPrenom() + "','" + c.getEmail()
								+ "','" + c.getTel() + "','" + c.getAdresse() + "');");

				 stmt.executeUpdate();
				
				stmt = Singleton.getConnection().prepareStatement("select id from client where email ='"+c.getEmail()+"'");
				rs = stmt.executeQuery();
				
				
				while (rs.next()) {
				
					id =rs.getInt(1);
			
				}
			

		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				Singleton.getConnection().close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return id ;
	}
}
