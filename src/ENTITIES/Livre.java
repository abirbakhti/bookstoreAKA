package ENTITIES;

import java.util.Arrays;

public class Livre {
	private int id;
	private String titre, auteur;
	private double prix;
	private String dateSortie;
	private byte[] image;

	public Livre(int id, String titre, String auteur, double prix, String dateSortie, byte[] image) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.prix = prix;
		this.dateSortie = dateSortie;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(String dateSortie) {
		this.dateSortie = dateSortie;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur + ", prix=" + prix + ", dateSortie="
				+ dateSortie + ", image=" + Arrays.toString(image) + "]";
	}
	

}
