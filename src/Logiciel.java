import java.util.ArrayList;

/**
 * Cette classe représente un logiciel qui contient des commandes.
 * Le logiciel va être interprété par Ordre, GenerateurCode et GenerateurUML.
 */
public class Logiciel extends ArrayList<Commande> implements Expression {
	@Override
	public void interprete( ContexteInterpretation contexte ) {
		contexte.interpreteLogiciel( this );
	}

	@Override
	public String toString() {
		String a = "";

		for ( Commande i : this ) {
			a += i;
			a += "\n";
		}
		return a;
	}
}
