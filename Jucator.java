package blackjack;

import java.util.ArrayList;

/**
 * Jucatorul si dealer-ul au un nume si un array de carti (cartile din mana)
 */
public class Jucator {
	String nume;
	ArrayList<Carte> cartiJucator = new ArrayList<Carte>();
	ArrayList<Carte> cartiDealer = new ArrayList<Carte>();

	public Jucator(String nume) {
		super();
		this.nume = nume;
	}

}
