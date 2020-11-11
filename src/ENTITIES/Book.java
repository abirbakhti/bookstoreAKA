package ENTITIES;

import java.util.Date;

import javax.swing.ImageIcon;

public class Book {
	private int id;
	private String title, author;
	private double price;
	private String releaseDate;
	private byte[] image ;
	public Book(int id, String title, String author, double price, String releaseDate, byte[] bs) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.releaseDate = releaseDate;
		this.image = bs ;
	}

	
	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", releaseDate="
				+ releaseDate + "]";
	}
	

}
