/**
 * Créé par Carl.M le 19/Mar/2021 à 5:05 a.m.
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
public class ClasseDebut extends Commande implements Expression {
    @Override
    public void interprete(ContexteInterpretation contexte) {

    }
}
