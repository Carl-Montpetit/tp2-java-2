public class MotAbstrait extends Mot{

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genAbstrait(this);
    }

    @Override
    public String toString() {
        return "MotAbstrait";
    }
}
