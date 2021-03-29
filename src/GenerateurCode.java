import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Stack;

// Il va implémenter ContexteInterpretation
// C'est le deuxième interpréteur
// pile de fichier
public class GenerateurCode implements ContexteInterpretation {

	protected Stack<File> pileFichier = new Stack<File>();
	protected Stack<String> pileNom = new Stack<String>();
	protected boolean estAbstrait = false;
	protected boolean estPremierParametre = false;

	@Override
	public void genAbstrait( Abstrait abstrait ) {
		estAbstrait = true;
	}

	@Override
	public void genDebutClasse( ClasseDebut classeDebut ) throws IOException {
		File monFichier = new File( classeDebut.nomClasse + ".java" );
		FileWriter monFileWriter = new FileWriter( classeDebut.nomClasse + ".java" );
		String nomMonFichier = classeDebut.nomClasse;

		pileFichier.push( monFichier );
		pileNom.push( nomMonFichier );
		try {
		monFileWriter.write( "public " );
			if ( estAbstrait ) {
				monFileWriter.write( "abstract " );
			}
			monFileWriter.write( "class " + classeDebut.nomClasse + " ");
			if ( pileNom.size() > 1 ) {
				monFileWriter.write( "extends " + pileNom.elementAt( pileNom.size() - 2 ) );
			}
			monFileWriter.write( " { " );
		} catch (IOException e) {
			System.out.println("Une erreur d'ecriture est survenue.");
			e.printStackTrace();
		}
		monFileWriter.close();
		estAbstrait = false;
	}

	@Override
	public void genFinClasse( ClasseFin classeFin ) throws IOException {
		FileWriter monFileWriter = new FileWriter( classeFin.toString() + ".java" );

	}

	@Override
	public void genDebutMethode( MethodeDebut methodeDebut ) {

	}

	@Override
	public void genAttribut( Attribut attribut ) {

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

	@Override
	public String toString() {
		return "GenerateurCode{" +
				"pileFichier=" + pileFichier +
				", pileNom=" + pileNom +
				", estAbstrait=" + estAbstrait +
				", estPremierParametre=" + estPremierParametre +
				'}';
	}
}
