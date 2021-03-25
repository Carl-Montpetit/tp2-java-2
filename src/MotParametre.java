public class MotParametre extends Mot{

    @Override
    public void interprete(ContexteInterpretation contexte) {
        contexte.genParametre(this);
    }

    @Override
    public String toString() {
        return "MotParametre";
    }
}
