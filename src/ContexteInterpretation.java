/**
 * Un contexte d’interprétation est une interface qui représente le fonctionnement d’un interpréteur. Cette interface
 * encapsule le fonctionnement des commandes sous un même type. Il contient une méthode pour chaque commande. Les
 * fonctions de rappel seront dans cette classe.
 */
public interface ContexteInterpretation {
	void genDebutClasse( ClasseDebut classeDebut );

	void genFinClasse( ClasseFin classeFin );

	void genDebutMethode( MethodeDebut methodeDebut );

	void genAttribut( Attribut attribut );

	void genAbstrait( Abstrait abstrait );

	void genParametre( Parametre parametre );

	void genFinMethode( MethodeFin methodeFin );

	void interpreteLogiciel( Logiciel logiciel );
}
