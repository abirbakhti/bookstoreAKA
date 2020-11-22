package DAO;


import java.io.File;
import java.io.FileInputStream;


import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import ENTITIES.Livre;

public class DaoLivre {

	  
	/*********************************** Ajouter un livre  *****************************************/
	public boolean ajouterLivre(Livre book,String s) {
		PreparedStatement stmt = null;
		boolean test = false ;
	
		try {
			File file = new File(s);
			InputStream img = new FileInputStream(file);
			
			stmt =
					Singleton.getConnection().prepareStatement("insert into livre (title,author,price,releaseDate,image) values(?,?,?,?,?)");
				     
			
			stmt.setString(1,book.getTitre());
			stmt.setString(2,book.getAuteur());
			stmt.setDouble(3,book.getPrix());
			stmt.setString(4,book.getDateSortie()); 
			stmt.setBinaryStream(5,(InputStream)img,(int)file.length());
				      
				
			int ajout = stmt.executeUpdate();
			if (ajout != 0)
				test=true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return test ;
	}
	

	/*********************************** Lister un livre *****************************************/
	public List<Livre> listLivre() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Livre> l = new ArrayList<>();
		
		try {
			stmt = Singleton.getConnection().prepareStatement("select * from livre");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Livre b = new Livre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getBytes(6) );
				l.add(b);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}

	/*********************************** Modifier un livre *****************************************/
	public boolean modifierLivre(Livre book) {
		String titrelivre = book.getTitre();
		Double prix = book.getPrix();
		String date = book.getDateSortie();
		String auteur = book.getAuteur();
		Statement st = null ;

		boolean t = false;

		try {
			Connection cc = null;
			cc = Singleton.getConnection();
		    st = cc.createStatement();

			String requete = "  UPDATE livre SET  title = '" + titrelivre + "',author = '" + auteur + "', price= '"
					+ prix + "',releaseDate = '" + date + "' WHERE id= '" + book.getId() + "'";

			st.executeUpdate(requete);
			t = true;
		} catch (SQLException x) {
			System.out.println(x.getMessage());
		}
		return t;
	}

	/*********************************** Supprimer un livre *****************************************/
	public boolean supprimerLivre(int id) {

		boolean t = false;
		Statement st = null;
		try {
			Connection cc = null;
			cc = Singleton.getConnection();
			st = cc.createStatement();

			String requete = "DELETE FROM livre where id='" + id + "'";

			st.executeUpdate(requete);
			t = true;
		} catch (SQLException x) {
			System.out.println(x.getMessage());
		}
		return t;
	}

}
