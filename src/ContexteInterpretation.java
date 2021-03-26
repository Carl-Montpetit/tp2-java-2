/**
 * Créé par Carl.M le 19/Mar/2021 à 4:52 a.m.
 */

/**
 * Un contexte d’interprétation est une interface qui représente le fonctionnement d’un interpréteur.
 * Cette interface encapsule le fonctionnement des commandes sous un même type. Il va contenir une
 * méthode pour chaque commande. Les fonctions de rappel seront dans cette classe.
 *
 * C’est cette interface qui sera implémentée par chaque interpréteur que vous voulez ajouter.
 */
public interface ContexteInterpretation {
	void genDebutClasse( ClasseDebut classeDebut );
	void genFinClasse( ClasseFin classeFin);
	void genDebutMethode( MethodeDebut methodeDebut);
	void genAttribut( Attribut attribut);
	void genAbstrait( Abstrait abstrait);
	void genParametre( Parametre parametre);
	void genFinMethode( MethodeFin methodeFin);
}
