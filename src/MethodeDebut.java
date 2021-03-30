import java.io.IOException;

/**
 * Chacune des commandes du langage sera représentée par une classe : Abstrait, ClasseDebut, ClasseFin, Attribut,
 * MethodeDebut, Parametre et MethodeFin.
 * <p>
 * Chacune de ces classes doit hériter de la classe Commande.
 * <p>
 * Elles vont donc automatiquement implémenter l’interface Expression.
 */
public class MethodeDebut extends Commande implements Expression {

	public String typeMethode;
	public String nomMethode;

	public MethodeDebut( String typeMethode, String nomMethode ) {
		this.typeMethode = typeMethode;
		this.nomMethode = nomMethode;
	}

	@Override
	public void interprete( ContexteInterpretation contexte ) throws IOException {
		contexte.genDebutMethode( this );
	}

	@Override
	public String toString() {
		return "MethodeDebut{" +
				"typeMethode='" + typeMethode + '\'' +
				", nomMethode='" + nomMethode + '\'' +
				'}';
	}
}
