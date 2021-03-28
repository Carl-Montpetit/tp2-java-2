/**
 * Créé par Carl.M le 19/Mar/2021 à 5:05 a.m.
 */

/**
 * Chacune des commandes du langage sera représentée par une classe : Abstrait, ClasseDebut, ClasseFin, Attribut,
 * MethodeDebut, Parametre et MethodeFin.
 * <p>
 * Chacune de ces classes doit hériter de la classe Commande.
 * <p>
 * Elles vont donc automatiquement implémenter l’interface Expression.
 */
public class ClasseDebut extends Commande implements Expression {

	public String nomClasse;

	public ClasseDebut( String nomClasse ) {
		this.nomClasse = nomClasse;
	}

	@Override
	public void interprete( ContexteInterpretation contexte ) {
		contexte.genDebutClasse( this );
	}

	@Override
	public String toString() {
		return "ClasseDebut{" +
				"nomClasse='" + nomClasse + '\'' +
				'}';
	}
}
