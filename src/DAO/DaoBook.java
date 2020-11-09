package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Book;

public class DaoBook {
	public Connection connexion() {
		Connection con = null;
		try {  			
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "");  		
			} 
		catch (Exception e) { 			
			System.out.println(e);		
			}
		return con;
	}
	
	public void addBook(Book book) {
		PreparedStatement stmt = null;
		try {
			stmt = connexion().prepareStatement("insert into book (id,title,author,price,releaseDate) "
					+ "values('" + book.getId() + "','" + book.getTitle() +  "','" + book.getAuthor() +  "','" + book.getPrice() 
					+  "','" + book.getReleaseDate()+"')");
			int ajout = stmt.executeUpdate();
			if (ajout != 0)
				System.out.println("Book added");
			else
				System.out.println("Error");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Book> listBook(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Book> l= new ArrayList<>();
		try {
			stmt = connexion().prepareStatement("select * from book");
			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ "  " + rs.getDouble(4)+ "  " + rs.getDate(5));
				Book b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5));
				l.add(b);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return l;
	}
}
