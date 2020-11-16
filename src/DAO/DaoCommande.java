package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ENTITIES.Book;

import ENTITIES.Commande;

public class DaoCommande {



	public void addCommande(Commande c) {
		PreparedStatement stmt = null;

		HashMap<Book, Integer> lb = new HashMap<Book, Integer>();

		lb = c.getLb();

		try {

			for (Book i : lb.keySet()) {
				stmt = Singleton.getConnection().prepareStatement(
						"INSERT INTO `commande` (`idCommande`, `idBook`, `date`, `quantite`, `prix`, `idClient`) VALUES ('"
								+ c.getId() + "','" + i.getId() + "','" + c.getDateCommande() + "','" + lb.get(i)
								+ "','" + c.getPrix() + "','" + c.getIdClient() + "');");

				// int ajout = stmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static List<Commande> listCommandeClient(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Commande> l = new ArrayList<>();
		
		try {
			stmt = Singleton.getConnection().prepareStatement("SELECT DISTINCT(idCommande), date,prix FROM `commande` WHERE idClient ="+id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Commande c = new Commande(rs.getInt("idCommande"),null,new java.sql.Date(rs.getDate("date") .getTime()).toLocalDate(),rs.getFloat("prix"),id);	
				l.add(c);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
	
	public static HashMap<Book, Integer> listLivresCommande(int idCommande) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashMap<Book, Integer> lb = new HashMap<Book, Integer>();
		
		try {
			stmt = Singleton.getConnection().prepareStatement("SELECT idBook,quantite,price,title FROM `commande` join `book` on commande.idBook = book.id where idCommande = "+idCommande);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Book b = new Book(rs.getInt("idBook"),rs.getString("title"),null,rs.getFloat("price"),null,null);
				lb.put(b,rs.getInt("quantite"));
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return lb;
	}

}
