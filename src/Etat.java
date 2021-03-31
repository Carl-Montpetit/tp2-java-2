/**
 * <p>
 * Cette classe permet de decrire l'etat.
 * </p>
 * Cette classe est utilisée par GenerateurUML.
 */
public class Etat {
	public boolean premierAttribut = true;
	public boolean premierMethode = true;
	public boolean premierClasse = true;

	@Override
	public String toString() {
		return "Etat{" +
				"premierAttribut=" + premierAttribut +
				", premierMethode=" + premierMethode +
				", premierClasse=" + premierClasse +
				'}';
	}
}
