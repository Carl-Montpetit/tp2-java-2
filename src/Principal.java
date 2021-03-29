import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Tp2 INF2120
 *
 * @Groupe: 010
 * @Date 2021-03-19 06:09
 * @nom Montpetit, Carl
 * @code_permanent MONC08069000
 * @nom Chagnon, Lysanne
 * @code_permanent CHAL65550003
 */
//TODO Quand j'ai ajoute le filewriter pour 2ieme interpreteur ya des exceptions (throws/try/catch) -> **IOException**
// qui se sont ajouter dans le projet. Bon a noter pour informer. On va peut-etre pouvoir en enlever plus tard.
public class Principal {

	public static void main( String[] args ) {
//        Demander le nom du fichier et créer un ArrayList contenant les lignes non vides du fichier sans les espaces.
		Scanner sc = new Scanner( System.in );
		String nomFichier = demanderNomFichier( sc );

		ArrayList<String> fichier = lireFichier( nomFichier );
//		System.out.println( fichier );

//        Vérifie la syntaxe du fichier
		verificationNbCommandes( fichier );

		verificationCommandeSyntaxe( fichier );

//        Crée le logiciel à l'aide du fichier
		Logiciel logiciel = transformerListe( fichier );
//		System.out.println( logiciel );

//        Applique le 1er interpréteur sur le logiciel
//		Interpreteur choix1 = Interpreteur.ORDRE;
//		ContexteInterpretation etat;
//		etat = choix1.supplier.get();
//		logiciel.interprete( etat );
//		System.out.println( etat );

//        Applique le 2e interpréteur sur le logiciel
		Interpreteur choix2 = Interpreteur.GENERATEUR_CODE;
		ContexteInterpretation etat2;
		etat2 = choix2.supplier.get();
		logiciel.interprete( etat2 );
		System.out.println( etat2 );

//        Applique le 3e interpréteur sur le logiciel

	}

	/**
	 * Cette classe contient les suppliers de tous les interpréteurs.
	 */
	public enum Interpreteur {
		ORDRE( Constantes.NOM_INTERPRETEUR_ORDRE, Ordre::new ),
		GENERATEUR_CODE( Constantes.NOM_INTERPRETEUR_CODE, GenerateurCode::new ),
		GENERATEUR_UML( Constantes.NOM_INTERPRETEUR_UML, GenerateurUML::new );

		public String nom;
		public Supplier<ContexteInterpretation> supplier;

		private Interpreteur( String nom, Supplier<ContexteInterpretation> supplier ) {
			this.nom = nom;
			this.supplier = supplier;
		}
	}

	/******************************************************************************************************************/
	// Méthodes d'affichage
	/******************************************************************************************************************/

	/**
	 * Affiche le message d'arrêt de programme.
	 */
	public static void affichageArret() {
		System.err.println( Constantes.MSG_ARRET_PROGRAMME );
		System.exit( 0 );
	}

	/******************************************************************************************************************/
	// Méthodes d'entrées et de vérification de la syntaxe
	/******************************************************************************************************************/

	/**
	 * Retourne le nom du fichier qui sera lu.
	 *
	 * @param scanner Indique l'emplacement où le nom du fichier sera lu.
	 * @return la chaîne de caractères qui sera le nom du fichier.
	 */
	public static String demanderNomFichier( Scanner scanner ) {
		String nomFichier;

		System.out.println( "Entrez le nom du fichier à utiliser : " );
		nomFichier = scanner.nextLine();
		return nomFichier;
	}


	/**
	 * Retourne les lignes du fichier non vides auxquelles on a retiré les espaces sour la forme d'une liste.
	 *
	 * @param nomFichier le nom du fichier.
	 * @return la liste des lignes du fichier sans les espaces.
	 */
	public static ArrayList<String> lireFichier( String nomFichier ) {
		ArrayList<String> ligneFichier = new ArrayList<>();
		File fichier = new File( nomFichier );
		String ligne;
		String ligneSansEspace;
		Scanner scanner = null;

		try {
			scanner = new Scanner( fichier );
		} catch ( FileNotFoundException e ) {
			System.err.println( Constantes.MSG_ERR_FICHIER );
			affichageArret();
		}
		while ( scanner.hasNext() ) {
			ligne = scanner.nextLine();
			if ( !ligne.equals( "" ) ) {
				ligneSansEspace = ligne.replaceAll( "\\s+", "" );
				ligneFichier.add( ligneSansEspace );
			}
		}
		scanner.close();
		return ligneFichier;
	}

	/**
	 * Retourne le nombre d'occurrences d'une certaine commande dans la ligne (du fichier).
	 *
	 * @param ligne    La ligne dans laquelle il peut y avoir zero, une ou des commandes.
	 * @param commande La commande qu'on cherche dans la ligne.
	 * @return Le nomnbre d'occurrence de la commande dans la ligne.
	 */
	public static int nbCommandeSpec( String ligne, String commande ) {
		int dernierOcc = 0;
		int nombreDOccurrences = 0;
		while ( dernierOcc != -1 ) {
			dernierOcc = ligne.indexOf( commande, dernierOcc );
			if ( dernierOcc != -1 ) {
				nombreDOccurrences++;
				dernierOcc += commande.length();
			}
		}
		return nombreDOccurrences;
	}

	/**
	 * Vérifie si chaque ligne du fichier n'a qu'une seule commande.
	 *
	 * @param fichier La liste qui contient les lignes de commande du fichier.
	 */
	public static void verificationNbCommandes( ArrayList<String> fichier ) {
		int nbCommande;
		ArrayList<String> commande = new ArrayList<>( Arrays.asList( Constantes.COMMANDE_ABSTRAIT,
				Constantes.COMMANDE_ATTRIBUT, Constantes.COMMANDE_CLASSE_DEBUT, Constantes.COMMANDE_CLASSE_FIN,
				Constantes.COMMANDE_METHODE_DEBUT, Constantes.COMMANDE_METHODE_FIN, Constantes.COMMANDE_PARAMETRE ) );
		for ( int i = 0; i < fichier.size(); i++ ) {
			String ligne = fichier.get( i );
			nbCommande = 0;
			for ( int j = 0; j < commande.size(); j++ ) {
				nbCommande = nbCommande + nbCommandeSpec( ligne, commande.get( j ) );
			}
			if ( nbCommande != 1 ) {
				System.err.println( Constantes.MSG_ERR_COMMANDES );
				affichageArret();
			}
		}

	}

	/**
	 * Vérifie la syntaxe de la commande de type unaire.
	 *
	 * @param commande La commande à vérifier.
	 */
	public static void verificationUnaire( String commande ) {
		if ( !commande.startsWith( Constantes.COMMANDE_CLASSE_DEBUT + "(" ) || !commande.endsWith( ")" ) ) {
			System.err.println( Constantes.MSG_ERR_TYPE_COMMANDE );
			affichageArret();
		}

		String identificateur = commande.substring( 12, commande.length() - 1 );
		if ( !identificateur.matches( "^[a-zA-Z_][a-zA-Z0-9_]*$" ) ) {
			System.err.println( Constantes.MSG_ERR_FORMAT_IDENTIFICATEUR );
			affichageArret();
		}
	}


	/**
	 * Vérifie la syntaxe de la commande de type binaire.
	 *
	 * @param commande La commande à vérifier.
	 */
	public static void verificationBinaire( String commande ) {
		if ( ( !commande.startsWith( Constantes.COMMANDE_METHODE_DEBUT + "(" ) &&
				!commande.startsWith( Constantes.COMMANDE_ATTRIBUT + "(" ) &&
				!commande.startsWith( Constantes.COMMANDE_PARAMETRE + "(" ) ) || !commande.endsWith( ")" ) ) {
			System.err.println( Constantes.MSG_ERR_TYPE_COMMANDE );
			affichageArret();
		}

		String identificateur;
		if ( commande.contains( Constantes.COMMANDE_METHODE_DEBUT ) ) {
			identificateur = commande.substring( 13, commande.length() - 1 );

		} else if ( commande.contains( Constantes.COMMANDE_ATTRIBUT ) ) {
			identificateur = commande.substring( 9, commande.length() - 1 );

		} else {
			identificateur = commande.substring( 10, commande.length() - 1 );

		}

		if ( !identificateur.matches( "^[a-zA-Z_][a-zA-Z0-9_]*(,)[a-zA-Z_][a-zA-Z0-9_]*$" ) ) {
			System.err.println( Constantes.MSG_ERR_FORMAT_IDENTIFICATEUR );
			affichageArret();
		}
	}

	/**
	 * Vérifie si la commande est écrite de manière valide.
	 *
	 * @param fichier La liste qui contient les lignes de commande du fichier.
	 */
	public static void verificationCommandeSyntaxe( ArrayList<String> fichier ) {
		String commande;
		for ( int i = 0; i < fichier.size(); i++ ) {
			commande = fichier.get( i );
			if ( commande.contains( Constantes.COMMANDE_CLASSE_FIN ) || commande.contains( Constantes.COMMANDE_METHODE_FIN )
					|| commande.contains( Constantes.COMMANDE_ABSTRAIT ) ) {
				if ( !commande.equals( Constantes.COMMANDE_CLASSE_FIN ) && !commande.equals( Constantes.COMMANDE_METHODE_FIN )
						&& !commande.equals( Constantes.COMMANDE_ABSTRAIT ) ) {
					System.err.println( Constantes.MSG_ERR_TYPE_COMMANDE );
					affichageArret();
				}
			} else if ( commande.contains( Constantes.COMMANDE_CLASSE_DEBUT ) ) {
				verificationUnaire( commande );
			} else {
				verificationBinaire( commande );
			}
		}
	}

	/**
	 * Retourne la commande nullaire décrite par la chaîne de caractères.
	 *
	 * @param nullaire La chaîne de caractères qui décrit une commande nullaire.
	 * @return La commande nullaire.
	 */
	public static Commande transformerNullaire( String nullaire ) {
		Commande nom;
		if ( nullaire.contains( Constantes.COMMANDE_CLASSE_FIN ) ) {
			nom = new ClasseFin();
		} else if ( nullaire.contains( Constantes.COMMANDE_METHODE_FIN ) ) {
			nom = new MethodeFin();
		} else {
			nom = new Abstrait();
		}
		return nom;
	}

	/**
	 * Retourne la commande unaire décrite par la chaîne de caractères.
	 *
	 * @param unaire La chaîne de caractères qui décrit une commande unaire (ClasseDebut).
	 * @return La commande unaire.
	 */
	public static Commande transformerUnaire( String unaire ) {
		String identificateur = unaire.substring( 12, unaire.length() - 1 );
		Commande nom = new ClasseDebut( identificateur );

		return nom;
	}


	/**
	 * Retourne la commande unaire décrite par la chaîne de caractères.
	 *
	 * @param binaire La chaîne de caractères qui décrit une commande binaire.
	 * @return La commande binaire.
	 */
	public static Commande transformerBinaire( String binaire ) {
		Commande nom;
		String identificateur;
		String[] elements;
		String element1;
		String element2;

		if ( binaire.contains( Constantes.COMMANDE_METHODE_DEBUT ) ) {
			identificateur = binaire.substring( 13, binaire.length() - 1 );
			elements = identificateur.split( "," );
			element1 = elements[ 0 ];
			element2 = elements[ 1 ];
			nom = new MethodeDebut( element1, element2 );

		} else if ( binaire.contains( Constantes.COMMANDE_ATTRIBUT ) ) {
			identificateur = binaire.substring( 9, binaire.length() - 1 );
			elements = identificateur.split( "," );
			element1 = elements[ 0 ];
			element2 = elements[ 1 ];
			nom = new Attribut( element1, element2 );

		} else {
			identificateur = binaire.substring( 10, binaire.length() - 1 );
			elements = identificateur.split( "," );
			element1 = elements[ 0 ];
			element2 = elements[ 1 ];
			nom = new Parametre( element1, element2 );

		}
		return nom;
	}

	/**
	 * Retourne un logiciel contenant les commandes du fichier.
	 *
	 * @param fichier La liste de commande se trouvant dans le fichier.
	 * @return Le logiciel du fichier.
	 */
	public static Logiciel transformerListe( ArrayList<String> fichier ) {
		Logiciel logiciel = new Logiciel();
		String commande;
		Commande nom;

		for ( int i = 0; i < fichier.size(); i++ ) {
			commande = fichier.get( i );

			if ( commande.contains( Constantes.COMMANDE_CLASSE_FIN ) || commande.contains( Constantes.COMMANDE_METHODE_FIN )
					|| commande.contains( Constantes.COMMANDE_ABSTRAIT ) ) {
				nom = transformerNullaire( commande );
				logiciel.add( nom );

			} else if ( commande.contains( Constantes.COMMANDE_CLASSE_DEBUT ) ) {
				nom = transformerUnaire( commande );
				logiciel.add( nom );

			} else {
				nom = transformerBinaire( commande );
				logiciel.add( nom );

			}

		}
		return logiciel;
	}
}
