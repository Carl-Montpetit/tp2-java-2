/**
 * Créé par Carl.M le 19/Mar/2021 à 5:54 a.m.
 */

import java.io.IOException;
import java.util.Stack;

/**
 * Ensuite, il vous reste à construire une classe implémentant le ContexteInterpretation pour chaque interpréteur. Cette
 * classe va contenir les variables d’instance représentant l’état et va implémenter les méthodes pour chacune des
 * actions.
 * <p>
 * TODO Je suis pas certain de tout ca encore peut etre que ca va aller dans un main??
 */
public class GenerateurUML implements ContexteInterpretation {
	int nbrClasse = 0;
	Stack<Etat> pileEtat = new Stack<Etat>();
	boolean estAbstrait = false;
	boolean estPremierParametre = false;

	@Override
	public void genAbstrait( Abstrait abstrait ) {
		estAbstrait = true;
	}

	@Override
	public void genDebutClasse( ClasseDebut classeDebut ) {

	}

	@Override
	public void genFinClasse( ClasseFin classeFin ) {

	}

	@Override
	public void genAttribut( Attribut attribut ) {

	}

	@Override
	public void genDebutMethode( MethodeDebut methodeDebut ) {

	}

	@Override
	public void genParametre( Parametre parametre ) {

	}

	@Override
	public void genFinMethode( MethodeFin methodeFin ) {

	}

	@Override
	public void interpreteLogiciel( Logiciel logiciel ) {
		logiciel.forEach( ( e ) -> {
			try {
				e.interprete( this );
			} catch ( IOException ioException ) {
				ioException.printStackTrace();
			}
		} );
	}
	/**
	 * Finalement, pour lancer votre interpréteur, il suffit de construire une instance de la classe de contexte
	 * d’interprétation de l’interpréteur voulu. Ensuite, il reste à démarrer l’interprétation du programme avec
	 * le contexte d’interprétation.
	 */
}
