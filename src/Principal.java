import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Tp2 INF2120
 *
 * @Groupe: 010
 * @Date 2021-03-19 06:09
 *
 * @nom Montpetit, Carl
 * @code_permanent MONC08069000
 *
 * @nom Chagnon, Lysanne
 * @code_permanent CHAL65550003
 */

public class Principal {

    /**
     * Retourne le nom du fichier qui sera lu.
     * @param scanner Indique l'emplacement où le nom du fichier sera lu.
     * @return la chaîne de caractères qui sera le nom du fichier.
     */
    public static String demanderNomFichier(Scanner scanner){
        String nomFichier;

        System.out.println("Entrez le nom du fichier à utiliser : ");
        nomFichier = scanner.nextLine();
        return nomFichier;
    }


    /**
     * Retourne les lignes du fichier non vides auxquelles on a retiré les espaces sour la forme d'une liste.
     * @param nomFichier le nom du fichier.
     * @return la liste des lignes du fichier sans les espaces.
     */
    public static ArrayList<String> lireFichier(String nomFichier){
        ArrayList<String> ligneFichier = new ArrayList<>();
        File fichier = new File( nomFichier );
        String ligne;
        String ligneSansEspace;
        Scanner scanner = null;

        try {
            scanner = new Scanner( fichier );
        } catch ( FileNotFoundException e ) {
            System.err.println("Le fichier n'existe pas.");
            System.exit(0);
        }
        while(scanner.hasNext()){
            ligne = scanner.nextLine();
            if(!ligne.equals("")){
            ligneSansEspace = ligne.replaceAll("\\s+","");
            ligneFichier.add(ligneSansEspace);
            }
        }
        scanner.close();
        return ligneFichier;
    }

    public static void main(String[] args) {
//        Demander le nom du fichier et créer un ArrayList contenant les lignes non vides du fichier sans les espaces.
        Scanner sc = new Scanner(System.in);
        String nomFichier = demanderNomFichier(sc);

        ArrayList<String> fichier = lireFichier(nomFichier);
        System.out.println(fichier);



    }
}
