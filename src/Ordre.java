import java.io.IOException;

/**
 * Cette classe est le premier interpréteur. Elle va vérifier l'ordre des commandes dans le logiciel.
 */
public class Ordre implements ContexteInterpretation {
	protected String mode = "FClasse";
	protected boolean estAbstrait = false;
	protected int nbrOuverture = 0;

	@Override
	public void genDebutClasse( ClasseDebut classeDebut ) {
		if ( !mode.equals( "DClasse" ) && !mode.equals( "FClasse" ) && !mode.equals( "DAttribut" ) && !mode.equals(
				"FMethode" ) ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
		nbrOuverture += 1;
		mode = "DClasse";
		estAbstrait = false;
	}

	@Override
	public void genFinClasse( ClasseFin classeFin ) {
		if ( !mode.equals( "DClasse" ) && !mode.equals( "FClasse" ) && !mode.equals( "DAttribut" ) && !mode.equals(
				"FMethode" )
				|| estAbstrait || nbrOuverture <= 0 ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
		nbrOuverture -= 1;
		mode = "FClasse";
	}

	@Override
	public void genDebutMethode( MethodeDebut methodeDebut ) {
		if ( !mode.equals( "DClasse" ) && !mode.equals( "DAttribut" ) && !mode.equals( "FMethode" ) ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
		mode = "DMethode";
		estAbstrait = false;
	}

	@Override
	public void genAttribut( Attribut attribut ) {
		if ( !mode.equals( "DClasse" ) && !mode.equals( "DAttribut" ) || estAbstrait ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
		mode = "DAttribut";
	}

	@Override
	public void genAbstrait( Abstrait abstrait ) {
		if ( estAbstrait ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
		estAbstrait = true;
	}

	@Override
	public void genParametre( Parametre parametre ) {
		if ( !mode.equals( "DMethode" ) && !mode.equals( "DParametre" ) || estAbstrait ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
		mode = "DParametre";
	}

	@Override
	public void genFinMethode( MethodeFin methodeFin ) {
		if ( !mode.equals( "DMethode" ) && !mode.equals( "DParametre" ) || estAbstrait ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
		mode = "FMethode";
	}

	@Override
	public void interpreteLogiciel( Logiciel logiciel ) {
		logiciel.forEach( e -> {
			try {
				e.interprete( this );
			} catch ( IOException ioException ) {
				ioException.printStackTrace();
			}
		} );
		if ( !mode.equals( "FClasse" ) || estAbstrait || nbrOuverture != 0 ) {
			System.err.println( Constantes.MSG_ERR_ORDRE );
			Principal.affichageArret();
		}
	}

	@Override
	public String toString() {
		return "Ordre{" +
				"mode='" + mode + '\'' +
				", estAbstrait=" + estAbstrait +
				", nbrOuverture=" + nbrOuverture +
				'}';
	}
}
