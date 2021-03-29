/**
 * Créé par Carl.M le 19/Mar/2021 à 5:54 a.m.
 */

import java.io.File;
import java.io.FileWriter;
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
	File fichierUML = new File("uml.tex");
	Etat etat = new Etat();

	@Override
	public void genAbstrait( Abstrait abstrait ) {
		estAbstrait = true;
	}

	@Override
	public void genDebutClasse( ClasseDebut classeDebut ) {
		try {
			FileWriter monFileWriter = new FileWriter(fichierUML,true);

			if( nbrClasse == 0){
				monFileWriter.write(DescriptionLatex14.PAGE_DEBUT);

			}else{

				if(pileEtat.peek().premierClasse){
					monFileWriter.write(DescriptionLatex14.CLASSE_DEBUT);
					pileEtat.peek().premierClasse = false;

				}
				monFileWriter.write(DescriptionLatex14.CLASSE_INTERNE_PREFIX);

			}

			pileEtat.push(etat);
			monFileWriter.write(DescriptionLatex14.CLASSE_DEBUT);

			if(estAbstrait){
				monFileWriter.write(DescriptionLatex14.ABSTRAIT_DEBUT);

			}

			monFileWriter.write(classeDebut.nomClasse);

			if(estAbstrait){
				monFileWriter.write(DescriptionLatex14.ABSTRAIT_FIN);
				estAbstrait = false;
			}

			nbrClasse++;

		} catch (IOException e) {
			e.printStackTrace();
		}

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
