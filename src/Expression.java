/**
 * Créé par Carl.M le 19/Mar/2021 à 4:46 a.m.
 */

/**
 * Cette interface contient une méthode dont le contrat est de lancer l’interprétation d’une commande
 * dans un contexte d’interprétation. C’est dans cette méthode que nous appliquons une fonction de
 * rappel (callback).
 */
public interface Expression {
	/**
	 * Va contenir cette methode -> void interprete( ContexteInterpretation contexte );
	 *
	 * Le code de la méthode interprete doit simplement faire appel à la méthode équivalente dans le contexte
	 * d’interprétation. Elle devra donner this en argument afin que le code appelé connaisse l’identité de
	 * l’appelant.
	 *
	 * La méthode interprete dans les Expression de type Commande va simplement appeler les
	 * méthodes définies par le contexte d’interprétation reçu en argument. Pour l’interprétation d’un
	 * programme, vous devez appeler la méthode interprete sur chaque Commande, une après l’autre,
	 * en utilisant le même contexte d’interprétation pour chacune.
	 */

	void interprete( ContexteInterpretation contexte );
}
