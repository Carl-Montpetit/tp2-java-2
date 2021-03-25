public class MotAttribut extends Mot{

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genAttribut(this);
    }

    @Override
    public String toString() {
        return "MotAttribut";
    }
}
