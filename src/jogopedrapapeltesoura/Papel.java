package jogopedrapapeltesoura;

public class Papel extends Opcao {
    public Papel() {
        super("../static/hand.png");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
