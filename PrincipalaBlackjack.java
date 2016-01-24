package blackjack;

import cazino.Principala;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Collections;
import java.util.Iterator;

public class PrincipalaBlackjack {
	/**
	 * am creeat cate un arrayList in care adaug pe rand cartile am creeat 3
	 * array-uri pe care le voi folosi pentru generarea pachetului de carti
	 */
	public static ArrayList<Carte> pachet = new ArrayList<Carte>();
	public static String[] denumire = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "Rege", "Regina", "Valet" };
	public static String[] simboluri = { "♠", "♥", "♦", "♣" };
	public static int[] valoare = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10 };

	public static Scanner sc = new Scanner(System.in);
	/**
	 * punctele jucatorului si dealer-ului sunt initial 0
	 */
	public static int puncteTotaleJucator = 0;
	public static int puncteTotaleDealer = 0;
	/**
	 * am creeat un jucatorul si dealer-ul
	 */
	public static Jucator jucator = new Jucator("Madalina");
	public static Jucator dealer = new Jucator("Dealer");

	/**
	 * metoda main apeleaza celelalte metode pentru executarea jocului
	 */
	public static void main(String[] args) {

		genereazaPachetCarti();
		incepeJoc();
		System.out.println("Vrei sa mai joci Blackjack?");
		String reiaJoc = sc.nextLine();
		if ("da".equalsIgnoreCase(reiaJoc)) {
			reseteazaJucator();
			reseteazaDealer();
			incepeJoc();
		} else {
			Principala p = new Principala();
			p.main(args);
		}
	}

	/**
	 * am creeam metoda genereaza pachet, pentru a introduce cartile cu
	 * denumirea,valoare si simbol in arrayList
	 */
	public static void genereazaPachetCarti() {
		int i = 0;
		int k = 0;
		for (i = 0, k = 0; i < denumire.length && k < valoare.length; i++, k++) {

			for (int j = 0; j < simboluri.length; j++) {
				pachet.add(new Carte(denumire[i], simboluri[j], valoare[k]));
			}
		}
		
	}

	/**
	 * am creeat metoda de incepere joc
	 */
	public static void incepeJoc() {
		/**
		 * La inceputul jocului amestecam cartile
		 */
		Object collection;
		Collections.shuffle(pachet);
		/**
		 * jucatorul primeste in mana 2 carti de fiecare data cand jucatorul sau
		 * dealerul trag carti, respectivele carti sunt scoase din
		 * pachet(sterse)
		 */
		for (int j = 0; j < 2; j++) {
			jucator.cartiJucator.add(pachet.get(j));
			puncteTotaleJucator += pachet.get(j).valoare;
			pachet.remove(0);
		}
		System.out.println();
		System.out.println(
				"Madalina cartile tale sunt: " + jucator.cartiJucator.get(0) + " si " + jucator.cartiJucator.get(1));
		System.out.println();
		/**
		 * dealer primeste in mana 2 carti
		 */
		for (int i = 1; i <= 2; i++) {
			dealer.cartiDealer.add(pachet.get(i));
			puncteTotaleDealer += pachet.get(i).valoare;
			pachet.remove(0);
		}
		System.out.println("Una dintre cartile trase de dealer este: " + dealer.cartiDealer.get(0));
		System.out.println();
		/**
		 * Jucatorul are prioritate si poate trage oricate carti doreste atata
		 * timp cat punctele nu depasesc 21
		 */
		String raspuns = "";
		System.out.println("Madalina puncte tale de inceput sunt : " + puncteTotaleJucator);
		while ((!("nu".equalsIgnoreCase(raspuns))) && puncteTotaleJucator < 21) {
			System.out.println("Doresti sa mai tragi o carte? Raspunde cu \"DA\"sau \"NU\"");
			raspuns = sc.nextLine();
			if ("da".equalsIgnoreCase(raspuns)) {
				jucator.cartiJucator.add(pachet.get(0));
				puncteTotaleJucator += pachet.get(0).valoare;
				System.out.println("Ai tras cartea: " + pachet.get(0));
				System.out.println("Puncte totale: " + puncteTotaleJucator);
				System.out.println();
				pachet.remove(0);
			}
		}
		if (puncteTotaleJucator > 21) {
			System.out.println("Dealer-ul a castigat!!!");
		}
		/**
		 * cand jucatorul decide sa se opreasca, daca jucatorul a depasit 21 de
		 * puncte dealerul castiga, daca jucatorul nu a depasit 21 de puncte
		 * dealerul trage carti atata timp cat valoarea punctelor nu depasesc 17
		 */
		if (puncteTotaleJucator < 21) {
			while (puncteTotaleDealer <= 17) {
				dealer.cartiDealer.add(pachet.get(0));
				puncteTotaleDealer += pachet.get(0).valoare;
				pachet.remove(0);
				System.out.println();
			}
			System.out.println("Dealer a tras cartile : " + dealer.cartiDealer);
		}
		System.out.println();
		System.out.println("Jocul s-a incheiat!");
		System.out.println();
		/**
		 * jucatorul castiga doar daca are 21 puncte sau are mai putine puncte
		 * de 21 dar mai multe decat dealer-ul
		 */
		if ((puncteTotaleJucator > puncteTotaleDealer && puncteTotaleJucator <= 21)

				|| (puncteTotaleJucator == 21)) {
			System.out.println(jucator.nume + " Ai castigat!");
			System.out.println();
			/**
			 * dealer-ul castiga doar daca are 21 puncte sau are mai putine
			 * puncte de 21 dar mai multe decat jucatorul
			 */
		} else if ((puncteTotaleDealer > puncteTotaleJucator && puncteTotaleDealer <= 21)
				|| (puncteTotaleDealer == 21)) {
			System.out.println("Dealer ai castigat!");
			System.out.println();
		}
		/**
		 * afiseaza scorul final al ambilor jucatori
		 */
		System.out.println(
				"Scor Final: " + jucator.nume + ":" + puncteTotaleJucator + "\n" + "Dealer :" + puncteTotaleDealer);
		/**
		 * Jucatorul decide daca doreste sa joaca, daca doreste sa mai joace se
		 * reseteaza punctajul
		 */

	}

	/**
	 * metoda reseteaza punctajul jucatorului
	 */
	public static void reseteazaJucator() {
		puncteTotaleJucator = 0;
		Iterator<Carte> iter = jucator.cartiJucator.iterator();
		while (iter.hasNext()) {
			Carte jucator = (Carte) iter.next();
			iter.remove();
		}
	}

	/**
	 * metoda reseteaza punctajul dealerului
	 */
	public static void reseteazaDealer() {
		puncteTotaleDealer = 0;
		Iterator<Carte> iter = dealer.cartiDealer.iterator();
		while (iter.hasNext()) {
			Carte dealer = (Carte) iter.next();
			iter.remove();
		}
	}
}
