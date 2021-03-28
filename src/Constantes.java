/**
 * Cette classe contient des constantes qui sont utilisé par les classes Principal et Interpreteur.
 */

public class Constantes {
	//  Messages du programme
	public static final String MSG_ARRET_PROGRAMME = "Arrêt du programme.";
	public static final String MSG_ERR_FICHIER = "Le fichier n'existe pas.";
	public static final String MSG_ERR_COMMANDES = "Le fichier contient une ligne qui a 0 ou plus d'une commande.";
	public static final String MSG_ERR_TYPE_COMMANDE = "Le fichier contient une ligne de commande mal écrite.";
	public static final String MSG_ERR_FORMAT_IDENTIFICATEUR = "Un ou des identificateurs d'une des commandes ne " +
            "sont" +
			" pas écrits dans le bon format.";
	public static final String MSG_ERR_ORDRE = "L'ordre des commandes est incorrecte dans le logiciel.";

	//  Noms des commandes
	public static final String COMMANDE_CLASSE_FIN = "classeFin";
	public static final String COMMANDE_METHODE_FIN = "methodeFin";
	public static final String COMMANDE_ABSTRAIT = "abstrait";
	public static final String COMMANDE_CLASSE_DEBUT = "classeDebut";
	public static final String COMMANDE_METHODE_DEBUT = "methodeDebut";
	public static final String COMMANDE_ATTRIBUT = "attribut";
	public static final String COMMANDE_PARAMETRE = "parametre";

	//	Noms des interpréteurs
	public static final String NOM_INTERPRETEUR_ORDRE = "Ordre";
	public static final String NOM_INTERPRETEUR_CODE = "GenerateurCode";
	public static final String NOM_INTERPRETEUR_UML = "GenerateurUML";
}
