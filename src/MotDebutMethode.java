public class MotDebutMethode extends Mot{

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genDebutMethode(this);
    }

    @Override
    public String toString() {
        return "MotDebutMethode";
    }
}
