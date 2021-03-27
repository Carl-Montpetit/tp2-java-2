// Il va implémenter ContexteInterpretation
// C'est le deuxième interpréteur
// pile de fichier
public class GenerateurCode implements ContexteInterpretation{
    @Override
    public void genDebutClasse(ClasseDebut classeDebut) {

    }

    @Override
    public void genFinClasse(ClasseFin classeFin) {

    }

    @Override
    public void genDebutMethode(MethodeDebut methodeDebut) {

    }

    @Override
    public void genAttribut(Attribut attribut) {

    }

    @Override
    public void genAbstrait(Abstrait abstrait) {

    }

    @Override
    public void genParametre(Parametre parametre) {

    }

    @Override
    public void genFinMethode(MethodeFin methodeFin) {

    }

    @Override
    public void interpreteLogiciel(Logiciel logiciel) {
        logiciel.forEach((e) -> e.interprete(this));
    }
}
