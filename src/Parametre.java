/**
 * Créé par Carl.M le 19/Mar/2021 à 5:08 a.m.
 */

/**
 * Chacune des commandes du langage sera représentée par une classe : Abstrait, ClasseDebut, ClasseFin, Attribut,
 * MethodeDebut, Parametre et MethodeFin.
 * <p>
 * Chacune de ces classes doit hériter de la classe Commande.
 * <p>
 * Elles vont donc automatiquement implémenter l’interface Expression.
 */
public class Parametre extends Commande implements Expression {

	public String typeParametre;
	public String nomParametre;

	public Parametre( String typeParametre, String nomParametre ) {
		this.typeParametre = typeParametre;
		this.nomParametre = nomParametre;
	}

	@Override
	public void interprete( ContexteInterpretation contexte ) {
		contexte.genParametre( this );
	}

	@Override
	public String toString() {
		return "Parametre{" +
				"typeParametre='" + typeParametre + '\'' +
				", nomParametre='" + nomParametre + '\'' +
				'}';
	}
}
