import java.io.IOException;

/**
 * <p>
 * Un contexte d’interprétation est une interface qui représente le fonctionnement d’un interpréteur.
 * <p>
 * Cette interface encapsule le fonctionnement des commandes sous un même type.
 * Il contient une méthode pour chaque commande.
 * <p>
 * Les fonctions de rappel seront dans cette classe.
 */
public interface ContexteInterpretation {
	/**
	 * Cette commande (unaire) commence par son nom.
	 * <p>
	 * Il est suivi d’une parenthèse
	 * ouvrante, d’un identificateur et se termine par une parenthèse fermante.
	 * @param classeDebut
	 * @throws IOException
	 */
	void genDebutClasse( ClasseDebut classeDebut ) throws IOException;

	/**
	 * Cette commande (nullaire) est simplement composée de son nom.
	 * @param classeFin
	 * @throws IOException
	 */
	void genFinClasse( ClasseFin classeFin ) throws IOException;

	/**
	 * Cette commande (binaire) commence par son nom.
	 * <p>
	 * Il est suivi d’une parenthèse ouvrante et de deux identificateurs séparés par une virgule.
	 * <p>
	 * Elles se terminent par une parenthèse fermante.
	 * @param methodeDebut
	 * @throws IOException
	 */
	void genDebutMethode( MethodeDebut methodeDebut ) throws IOException;

	/**
	 * Cette commande (binaire) commence par son nom.
	 * <p>
	 * Il est suivi d’une parenthèse ouvrante et de deux identificateurs séparés par une virgule.
	 * <p>
	 * Elles se terminent par une parenthèse fermante.
	 * @param attribut
	 * @throws IOException
	 */
	void genAttribut( Attribut attribut ) throws IOException;

	/**
	 * Cette commande (nullaire) est simplement composée de son nom.
	 * @param abstrait
	 */
	void genAbstrait( Abstrait abstrait );

	/**
	 * Cette commande (binaire) commence par son nom.
	 * <p>
	 * Il est suivi d’une parenthèse ouvrante et de deux identificateurs séparés par une virgule.
	 * <p>
	 * Elles se terminent par une parenthèse fermante.
	 * @param parametre
	 */
	void genParametre( Parametre parametre );

	/**
	 * Cette commande (nullaire) est simplement composée de son nom.
	 * @param methodeFin
	 * @throws IOException
	 */
	void genFinMethode( MethodeFin methodeFin ) throws IOException;

	/**
	 * Interprete le logiciel.
	 * @param logiciel
	 */
	void interpreteLogiciel( Logiciel logiciel );
}
