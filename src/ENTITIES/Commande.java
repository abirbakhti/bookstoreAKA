package ENTITIES;


import java.time.LocalDate;
import java.util.HashMap;

public class Commande {
    private int id ;
    private HashMap<Livre, Integer> lb = new HashMap<Livre, Integer>();
    private  LocalDate dateCommande ;
    private Double prix ;
    private int idClient ;
    
    
	public Commande(int id, HashMap<Livre, Integer> lb,Double prix, int idClient) {
		this.id = id;
		this.lb = lb;
		this.dateCommande =LocalDate.now();
		this.prix = prix;
		this.idClient = idClient;
	}
	public Commande(int id, HashMap<Livre, Integer> lb,LocalDate dateCommande,Double prix, int idClient) {
		this.id = id;
		this.lb = lb;
		this.dateCommande =dateCommande;
		this.prix = prix;
		this.idClient = idClient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashMap<Livre, Integer> getLb() {
		return lb;
	}
	public void setLb(HashMap<Livre, Integer> lb) {
		this.lb = lb;
	}
	public LocalDate getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(LocalDate dateCommande) {
		this.dateCommande = dateCommande;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
    
    
    
}
