import java.io.IOException;

/**
 * <p>
 * Cette interface contient une méthode dont le contrat est de lancer l’interprétation d’une commande dans un contexte
 * d’interprétation.
 * </p>
 * C’est dans cette méthode que nous appliquons une fonction de rappel (callback).
 */
public interface Expression {

	/**
	 * La méthode interprete dans les Expression de type Commande va simplement appeler les méthodes définies par le
	 * contexte d’interprétation reçu en argument.
	 *
	 * @throws IOException
	 */
	void interprete( ContexteInterpretation contexte ) throws IOException;
}
