import java.util.List;
import java.util.Random;
import java.util.Scanner;

import DAO.DaoCommande;

public class Bookstore {

	public static void main(String[] args) {
		/*Scanner s= new Scanner(System.in);
		System.out.println("Donner le titre: ");
		int qte=s.nextInt();
		System.out.println("Donner le prix unitaire: ");
		double unitPrice=s.nextDouble();
		Utility u = new Utility();
		System.out.println("Total: " + u.calculateTotalPrice(qte, unitPrice));*/
		  DaoCommande daoCommande = new DaoCommande();
		    Random r = new Random();
		    int n;
	       
	        List<Integer> l = daoCommande.listId();
	        do {
	         n = r.nextInt(99999);
	        }while (l.contains(n) == true) ;
	        System.out.println(l.contains(n));
	        System.out.println(n);
		
		
		
		
		
	}

}
