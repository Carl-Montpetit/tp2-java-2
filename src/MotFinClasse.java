public class MotFinClasse extends Mot{

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genFinClasse(this);
    }

    @Override
    public String toString() {
        return "MotFinClasse";
    }
}
