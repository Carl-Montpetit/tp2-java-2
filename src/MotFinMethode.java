public class MotFinMethode extends Mot{

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genFinMethode(this);
    }

    @Override
    public String toString() {
        return "MotFinMethode";
    }
}
