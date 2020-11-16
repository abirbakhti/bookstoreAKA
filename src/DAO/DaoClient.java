package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import ENTITIES.Book;
import ENTITIES.Client;
import ENTITIES.Commande;

public class DaoClient {

	public int ajouterClient(Client c) {
		PreparedStatement stmt = null;
        ResultSet rs = null ;
		int id = 0 ;

	

		try {

		
				stmt = Singleton.getConnection().prepareStatement(
						"INSERT INTO `client` (`nom`, `prenom`, `email`, `tel`, `adresse`) VALUES ('"+ c.getNom()+ "','" + c.getPrenom() + "','" + c.getEmail()
								+ "','" + c.getTel() + "','" + c.getAdresse() + "');");

				int ajout = stmt.executeUpdate();
				
				stmt = Singleton.getConnection().prepareStatement("select id from client where email ='"+c.getEmail()+"'");
				rs = stmt.executeQuery();
				
				
				while (rs.next()) {
				
					id =rs.getInt(1);
			
				}
			

		} catch (Exception e) {
			System.out.println(e);
		}
		return id ;
	}
}
