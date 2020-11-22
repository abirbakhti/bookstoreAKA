package Step_1;

import java.util.Scanner;


public class Bookstore {

	public static void main(String[] args) {
		
		Scanner s= new Scanner(System.in);
		
		System.out.println("Donner le titre: ");
		int qte=s.nextInt();
		
		System.out.println("Donner le prix unitaire: ");
		double unitPrice=s.nextDouble();
		
		Utilitaire u = new Utilitaire();
		System.out.println("Total: " + u.calculateTotalPrice(qte, unitPrice));
		 
		
		
		
		
		
	}

}
