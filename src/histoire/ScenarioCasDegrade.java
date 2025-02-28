package histoire;

import villagegaulois.Etal;
import personnages.Gaulois;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		//etal.occuperEtal(new Gaulois("blabla", 10), "produitTest", 52);
		try {
			etal.acheterProduit(5, null);
		} catch (IllegalArgumentException e) {
			System.err.println("Exception capturée : " + e.getMessage());
		}
		try {
			etal.acheterProduit(0, new Gaulois("Asterix", 10));
		} catch (IllegalArgumentException e) {
			System.err.println("Exception capturée : " + e.getMessage());
		}
		try {
			etal.acheterProduit(5, new Gaulois("Asterix", 10));
		} catch (IllegalStateException e) {
			System.err.println("Exception capturée : " + e.getMessage());
		}
		System.out.println("Fin du test");
	}
}
