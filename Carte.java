package blackjack;

import java.util.Arrays;

/**
 * Cartile au o denumire, un simbol si o valoare
 */
public class Carte {
	String denumire = "";
	String simboluri = "";
	int valoare = 0;

	/**
	 * Constructorul construieste cartile in functie de atributele cerute
	 */
	
	
	public Carte(String denumire, String simboluri,int valoare) {
		this.denumire = denumire;
		this.simboluri = simboluri;
		this.valoare = valoare;
			
		
	}

	/**
	 * Afiseaza denumirea si simbolul cartilor
	 */
	@Override
	public String toString() {
		return "Carte [" + denumire + "" + simboluri + "]";
	}

}
