package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbMarche) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbMarche); 
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	public class Marche {
		private Etal[] etals;
		
		public Marche(int nbEtals) {
			etals = new Etal[nbEtals];
			for (int i=0; i<nbEtals; i++) {
				etals[i] = new Etal();
			}
		}
		
		public void utiliserEtal (int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			
		}
		
		public int trouverEtalLibre() {
			for (int i=0; i<etals.length; i++) {
				if (!(etals[i].isEtalOccupe())) {
					return i;
				}
			} return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
			int x=0;
			for (int i=0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					x+=1;
				}
			}
			
			Etal[] EtalProduit = new Etal[x];
			int y = x;
			
			for (int i=0; i<etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					EtalProduit[i] = etals[x-y];
					y--;
				}
			}
			return EtalProduit;
			
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i=0; i<etals.length; i++) {
				if (gaulois.equals(etals[i].getVendeur())) {
					return etals[i];
				}
			}
			return null;
			
		}
		
		public String afficherMarche () {
			StringBuilder chaine = new StringBuilder();
			int x =0;
			for (int i=0; i<etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
					chaine.append("\n");
			}
				else {
					x++;
				}
			}
			chaine.append("Il reste "+x+" étals non utilisés dans le marché.");
			return chaine.toString();
		}
		
	}
}