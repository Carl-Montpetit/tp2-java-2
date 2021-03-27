/**
 * Créé par Carl.M le 19/Mar/2021 à 5:07 a.m.
 */

/**
 * Chacune des commandes du langage
 * sera représentée par une classe : Abstrait, ClasseDebut, ClasseFin, Attribut,
 * MethodeDebut, Parametre et MethodeFin.
 *
 * Chacune de ces classes doit hériter de la classe
 * Commande.
 *
 * Elles vont donc automatiquement implémenter l’interface Expression.
 */
public class MethodeDebut extends Commande implements Expression {

    public String typeMethode;
    public String nomMethode;

    public MethodeDebut(String typeMethode, String nomMethode) {
        this.typeMethode = typeMethode;
        this.nomMethode = nomMethode;
    }

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genDebutMethode(this);
    }

    @Override
    public String toString() {
        return "MethodeDebut{" +
                "typeMethode='" + typeMethode + '\'' +
                ", nomMethode='" + nomMethode + '\'' +
                '}';
    }
}
