package Step_1;



import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import DAO.DaoLivre;
import ENTITIES.Livre;

public class Menu {

	public static void main(String[] args) {
		int x;
		DaoLivre Dao = new DaoLivre();
		do {
			System.out.println("Tapez (1) pour Lister  ");
			System.out.println("Tapez (2) pour Ajouter : ");
			System.out.println("Tapez (0) pour Sortir : ");
			Scanner s = new Scanner(System.in);
			x = s.nextInt();

			switch (x) {
			case 1: {

				System.out.println("La liste des livres : ");
				List<Livre> listBook = new ArrayList<>();
				listBook = Dao.listLivre();
				System.out.println(listBook);

			}
				break;
			case 2: {
				System.out.println("Donner l'id du livre");
				int id = s.nextInt();
				System.out.println("Donner le titre du livre : ");
				String titre = s.next();
				System.out.println("Donner auteur du livre : ");
				String auteur = s.next();
				System.out.println("Donner le prix du livre : ");
				Double prix = s.nextDouble();
				System.out.println("Donner Date de livre (yyyy-mm-jj) : ");
				
				String date = s.next();
				
				Dao.ajouterLivre(new Livre(id,titre,auteur,prix,date,null),null);
			}
				break;
			}
		} while (x != 0);

	}

}
