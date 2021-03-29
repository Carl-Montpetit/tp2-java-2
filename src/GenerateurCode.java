import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Stack;

// Il va implémenter ContexteInterpretation
// C'est le deuxième interpréteur
// pile de fichier
public class GenerateurCode implements ContexteInterpretation {
	protected File topFichier = null;
	protected String topNom = null;
	protected Stack<File> pileFichier = new Stack<File>();
	protected Stack<String> pileNom = new Stack<String>();
	protected boolean estAbstrait = false;
	protected boolean estPremierParametre = false;

	//	 TODO -> Pas encore parfait, mais ca avance! Va rester a bien gerer la progression de l'interpreteur par
	//	  rapport a la pile de fichiers et celle de noms (des fichiers).
	@Override
	public void genAbstrait( Abstrait abstrait ) {
		estAbstrait = true;
	}

	@Override
	public void genDebutClasse( ClasseDebut classeDebut ) throws IOException {
		File monFichier = new File( classeDebut.nomClasse + ".java" );
		String nomMonFichier = classeDebut.nomClasse;
		// Ajout un nouveau fichier sur la pile
		pileFichier.push( monFichier );
		// Ajout un nouveau nom sur l'autre pile (nom)
		pileNom.push( nomMonFichier );
		try {
			FileWriter monFileWriter = new FileWriter( pileFichier.peek() );
			monFileWriter.write( "public " );
			if ( estAbstrait ) {
				monFileWriter.write( "abstract " );
			}
			monFileWriter.write( "class " + classeDebut.nomClasse + " " );
			if ( pileNom.size() > 1 ) {
				monFileWriter.write( "extends " + pileNom.elementAt( pileNom.size() - 2 ) );
			}
			monFileWriter.write( " {\n" );
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( "Une erreur d'ecriture est survenue." );
			e.printStackTrace();
		}
		estAbstrait = false;
	}

	@Override
	public void genFinClasse( ClasseFin classeFin ) throws IOException {
		FileWriter monFileWriter = new FileWriter( pileFichier.peek(), true );
		try {
			monFileWriter.write( "}" );
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( "Une erreur d'ecriture est survenue." );
			e.printStackTrace();
		}
		topFichier = pileFichier.pop();
		topNom = pileNom.pop();
	}

	@Override
	public void genAttribut( Attribut attribut ) throws IOException {
		try {
			FileWriter monFileWriter = new FileWriter( pileFichier.peek(), true );
			monFileWriter.write( "\tprivate " + attribut.typeAttribut + " " + attribut.nomAttribut + ";\n");
			monFileWriter.write( "\tpublic " + attribut.typeAttribut + " get" + attribut.nomAttribut + "() " + "{\n" );
			monFileWriter.write( "\t\treturn " + attribut.nomAttribut + " ;" );
			monFileWriter.write( "\t}\n" );
			monFileWriter.write( "\tpublic void set" + attribut.nomAttribut + " (" + attribut.typeAttribut + " "
					+ attribut.nomAttribut + " ) " + "{\n" );
			monFileWriter.write( "\t\tthis." + attribut.nomAttribut + " = " + attribut.nomAttribut + "; " );
			monFileWriter.write( "\t}\n " );
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( "Une erreur d'ecriture est survenue." );
			e.printStackTrace();
		}
	}

	@Override
	public void genDebutMethode( MethodeDebut methodeDebut ) throws IOException {
		try {
			FileWriter monFileWriter = new FileWriter( pileFichier.peek(), true );

			monFileWriter.write( "\tpublic " );
			if ( estAbstrait ) {
				monFileWriter.write( "\tabstract " );
			}
			monFileWriter.write( methodeDebut.typeMethode + " " + methodeDebut.nomMethode + " ( " );
			estPremierParametre = true;
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( "Une erreur d'ecriture est survenue." );
			e.printStackTrace();
		}
	}

	@Override
	public void genParametre( Parametre parametre ) {
		try {
			FileWriter monFileWriter = new FileWriter( pileFichier.peek(), true );
			if ( !estPremierParametre ) {
				monFileWriter.write( " , " );
			}
			monFileWriter.write( parametre.typeParametre + " " + parametre.nomParametre );
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( "Une erreur d'ecriture est survenue." );
			e.printStackTrace();
		}
		estPremierParametre = false;
	}

	@Override
	public void genFinMethode( MethodeFin methodeFin ) throws IOException {
		try {
			FileWriter monFileWriter = new FileWriter( pileFichier.peek(), true );
			monFileWriter.write( " ) " );
			if ( estAbstrait ) {
				monFileWriter.write( " ;\n " );
			} else {
				monFileWriter.write( " {}\n " );
			}
			monFileWriter.close();
		} catch ( IOException e ) {
			System.err.println( "Une erreur d'ecriture est survenue." );
			e.printStackTrace();
		}
		estAbstrait = false;
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
