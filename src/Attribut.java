/**
 * Créé par Carl.M le 19/Mar/2021 à 5:06 a.m.
 */

import java.io.IOException;

/**
 * Chacune des commandes du langage sera représentée par une classe : Abstrait, ClasseDebut, ClasseFin, Attribut,
 * MethodeDebut, Parametre et MethodeFin.
 * <p>
 * Chacune de ces classes doit hériter de la classe Commande.
 * <p>
 * Elles vont donc automatiquement implémenter l’interface Expression.
 */
public class Attribut extends Commande implements Expression {

	public String typeAttribut;
	public String nomAttribut;

	public Attribut( String typeAttribut, String nomAttribut ) {
		this.typeAttribut = typeAttribut;
		this.nomAttribut = nomAttribut;
	}

	@Override
	public void interprete( ContexteInterpretation contexte ) throws IOException {
		contexte.genAttribut( this );
	}

	@Override
	public String toString() {
		return "Attribut{" +
				"typeAttribut='" + typeAttribut + '\'' +
				", nomAttribut='" + nomAttribut + '\'' +
				'}';
	}
}
