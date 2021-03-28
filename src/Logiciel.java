import java.util.ArrayList;

// Il faudra l'implémenter d'Expression
// Il va être similaire à Programme dans l'exemple du prof
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
