import java.io.IOException;

/**
 * Un contexte d’interprétation est une interface qui représente le fonctionnement d’un interpréteur. Cette interface
 * encapsule le fonctionnement des commandes sous un même type. Il contient une méthode pour chaque commande. Les
 * fonctions de rappel seront dans cette classe.
 */
public interface ContexteInterpretation {
	void genDebutClasse( ClasseDebut classeDebut ) throws IOException;

	void genFinClasse( ClasseFin classeFin ) throws IOException;

	void genDebutMethode( MethodeDebut methodeDebut ) throws IOException;

	void genAttribut( Attribut attribut ) throws IOException;

	void genAbstrait( Abstrait abstrait );

	void genParametre( Parametre parametre );

	void genFinMethode( MethodeFin methodeFin ) throws IOException;

	void interpreteLogiciel( Logiciel logiciel );
}
