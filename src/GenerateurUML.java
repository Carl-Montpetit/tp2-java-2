import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/**
 * Cette classe est le troisième interpréteur. Elle va construire un fichier LaTeX qui contient un diagramme UML.
 */
public class GenerateurUML implements ContexteInterpretation {
	int nbrClasse = 0;
	Stack<Etat> pileEtat = new Stack<Etat>();
	boolean estAbstrait = false;
	boolean estPremierParametre = false;
	File fichierUML = new File( "uml.tex" );
	Etat etat = new Etat();
	Etat dernierEtat = new Etat();
	boolean debut = true;

	@Override
	public void genAbstrait( Abstrait abstrait ) {
		estAbstrait = true;
	}

	@Override
	public void genDebutClasse( ClasseDebut classeDebut ) {
		try {
			FileWriter monFileWriter = new FileWriter( fichierUML, true );
			if ( nbrClasse == 0 ) {
				monFileWriter.write( DescriptionLatex14.PAGE_DEBUT );
			} else {
				if ( pileEtat.peek().premierClasse ) {
					monFileWriter.write( DescriptionLatex14.CLASSE_FIN );
					monFileWriter.write( DescriptionLatex14.LISTE_CLASSE_DEBUT );
					pileEtat.peek().premierClasse = false;
				}
				monFileWriter.write( DescriptionLatex14.CLASSE_INTERNE_PREFIX );
			}

			pileEtat.push( etat );
			monFileWriter.write( DescriptionLatex14.CLASSE_DEBUT );

			if ( estAbstrait ) {
				monFileWriter.write( DescriptionLatex14.ABSTRAIT_DEBUT );
			}

			monFileWriter.write( classeDebut.nomClasse );

			if ( estAbstrait ) {
				monFileWriter.write( DescriptionLatex14.ABSTRAIT_FIN );
				estAbstrait = false;
			}
			nbrClasse++;
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( Constantes.MSG_ERR_GEN_CODE );
			e.printStackTrace();
		}
	}

	@Override
	public void genFinClasse( ClasseFin classeFin ) {
		try {
			FileWriter monFileWriter = new FileWriter( fichierUML, true );

			nbrClasse--;
			if ( pileEtat.peek().premierClasse ) {
				monFileWriter.write( DescriptionLatex14.CLASSE_FIN );
			} else {
				monFileWriter.write( DescriptionLatex14.LISTE_CLASSE_FIN );
			}
			if ( nbrClasse == 0 ) {
				monFileWriter.write( DescriptionLatex14.PAGE_FIN );
			} else {
				monFileWriter.write( DescriptionLatex14.CLASSE_INTERNE_SUFFIX );
			}
			dernierEtat = pileEtat.pop();
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( Constantes.MSG_ERR_GEN_CODE );
			e.printStackTrace();
		}
	}

	@Override
	public void genAttribut( Attribut attribut ) {
		try {
			FileWriter monFileWriter = new FileWriter( fichierUML, true );

			if ( pileEtat.peek().premierAttribut ) {
				monFileWriter.write( DescriptionLatex14.LISTE_ATTRIBUT_DEBUT );
				pileEtat.peek().premierAttribut = false;
			} else {
				monFileWriter.write( DescriptionLatex14.LISTE_ATTRIBUT_SEP );
			}
//			TODO -> **important** -> A noter, pas de fin de ligne a la fin du ~write~ ci bas!
			monFileWriter.write( attribut.nomAttribut + " : " + attribut.typeAttribut );
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( Constantes.MSG_ERR_GEN_CODE );
			e.printStackTrace();
		}
	}

	@Override
	public void genDebutMethode( MethodeDebut methodeDebut ) {
		try {
			FileWriter monFileWriter = new FileWriter( fichierUML, true );

			if ( pileEtat.peek().premierMethode ) {
				monFileWriter.write( DescriptionLatex14.LISTE_METHODE_DEBUT );
				pileEtat.peek().premierMethode = false;
			} else {
				monFileWriter.write( DescriptionLatex14.LISTE_METHODE_SEP );
			}
			if ( estAbstrait ) {
				monFileWriter.write( DescriptionLatex14.ABSTRAIT_DEBUT );
			}
//			TODO -> Ici, "" represente le -> " void " dans l'enonce
			if (!methodeDebut.typeMethode.equals("")) {
//				TODO -> A verifier si c'est bon pour -> " '' "
				monFileWriter.write( methodeDebut.typeMethode + " '' " );
			}
			monFileWriter.write( methodeDebut.nomMethode );
			monFileWriter.write( DescriptionLatex14.PARAMETRE_DEBUT );
			estPremierParametre = true;
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( Constantes.MSG_ERR_GEN_CODE );
			e.printStackTrace();
		}
	}

	@Override
	public void genParametre( Parametre parametre ) {
		try {
			FileWriter monFileWriter = new FileWriter( fichierUML, true );

			if ( estPremierParametre ) {
				estPremierParametre = false;
			} else {
				monFileWriter.write( DescriptionLatex14.PARAMETRE_SEP );
			}
			monFileWriter.write( parametre.typeParametre );
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( Constantes.MSG_ERR_GEN_CODE );
			e.printStackTrace();
		}
	}

	@Override
	public void genFinMethode( MethodeFin methodeFin ) {
		try {
			FileWriter monFileWriter = new FileWriter( fichierUML, true );

			monFileWriter.write( DescriptionLatex14.PARAMETRE_FIN );
			if ( estAbstrait ) {
				monFileWriter.write( DescriptionLatex14.ABSTRAIT_FIN );
			}
			monFileWriter.close();
			estAbstrait = false;
		} catch ( IOException e ) {
			System.err.println( Constantes.MSG_ERR_GEN_CODE );
			e.printStackTrace();
		}
	}

	@Override
	public void interpreteLogiciel( Logiciel logiciel ) {
		try {
			FileWriter fileWriter = new FileWriter( fichierUML);
			fileWriter.write(DescriptionLatex14.FICHIER_DEBUT);
			fileWriter.close();
			logiciel.forEach( ( e ) -> {
				try {
					e.interprete( this );
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			} );
			fileWriter = new FileWriter(fichierUML,true);
			fileWriter.write(DescriptionLatex14.FICHIER_FIN);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	@Override
	public String toString() {
		return "GenerateurUML{" +
				"nbrClasse=" + nbrClasse +
				", pileEtat=" + pileEtat +
				", estAbstrait=" + estAbstrait +
				", estPremierParametre=" + estPremierParametre +
				", fichierUML=" + fichierUML +
				", etat=" + etat +
				'}';
	}
}
