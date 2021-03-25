public class MotDebutClasse extends Mot{

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genDebutClasse(this);
    }

    @Override
    public String toString() {
        return "MotDebutClasse";
    }
}
