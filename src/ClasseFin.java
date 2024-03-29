import java.io.IOException;

/**
 * Chacune des commandes du langage sera représentée par une classe : Abstrait, ClasseDebut, ClasseFin, Attribut,
 * MethodeDebut, Parametre et MethodeFin.
 * <p>
 * Chacune de ces classes doit hériter de la classe Commande.
 * <p>
 * Elles vont donc automatiquement implémenter l’interface Expression.
 */
public class ClasseFin extends Commande implements Expression {
	@Override
	public void interprete( ContexteInterpretation contexte ) throws IOException {
		contexte.genFinClasse( this );
	}

	@Override
	public String toString() {
		return "ClasseFin";
	}
}
