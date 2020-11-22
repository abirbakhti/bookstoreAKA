package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ENTITIES.Livre;

import ENTITIES.Commande;

public class DaoCommande {

	
	/*********************************** Ajouter une commande *****************************************/
	public void ajouterCommande(Commande c) {
		PreparedStatement stmt = null;

		HashMap<Livre, Integer> lb = new HashMap<Livre, Integer>();

		lb = c.getLb();

		try {

			for (Livre i : lb.keySet()) {
				stmt = Singleton.getConnection().prepareStatement(
						"INSERT INTO `commande` (`idCommande`, `idBook`, `date`, `quantite`, `prix`, `idClient`) VALUES ('"
								+ c.getId() + "','" + i.getId() + "','" + c.getDateCommande() + "','" + lb.get(i)
								+ "','" + c.getPrix() + "','" + c.getIdClient() + "');");

				stmt.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	/*********************************** Lister les commandes *****************************************/
	public List<Commande> listCommandesClient() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Commande> l = new ArrayList<>();

		try {
			stmt = Singleton.getConnection()
					.prepareStatement("SELECT DISTINCT(idCommande), date,prix FROM `commande` ");
			rs = stmt.executeQuery();

			while (rs.next()) {
				Commande c = new Commande(rs.getInt("idCommande"), null,
						new java.sql.Date(rs.getDate("date").getTime()).toLocalDate(), rs.getDouble("prix"), 0);
				l.add(c);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}

	
	/*********************************** Lister les livres d'une commande *****************************************/
	public HashMap<Livre, Integer> listLivresCommande(int idCommande) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashMap<Livre, Integer> lb = new HashMap<Livre, Integer>();

		try {
			stmt = Singleton.getConnection().prepareStatement(
					"SELECT idBook,quantite,price,title FROM `commande` join `livre` on commande.idBook = livre.id where idCommande = "
							+ idCommande);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Livre b = new Livre(rs.getInt("idBook"), rs.getString("title"), null, rs.getFloat("price"), null, null);
				lb.put(b, rs.getInt("quantite"));

			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		return lb;
	}

	
	/*********************************** Lister les id des commandes *****************************************/
	public List<Integer> listId() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Integer> l = new ArrayList<>();

		try {
			stmt = Singleton.getConnection().prepareStatement("SELECT DISTINCT(idCommande) FROM `commande` ");
			rs = stmt.executeQuery();

			while (rs.next()) {
				l.add(rs.getInt("idCommande"));
			}
		} catch (Exception e) {
			System.out.println(e);
		} 
		return l;
	}

}
