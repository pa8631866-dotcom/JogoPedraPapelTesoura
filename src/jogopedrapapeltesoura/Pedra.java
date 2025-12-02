package jogopedrapapeltesoura;

public class Pedra extends Opcao {
    public Pedra() {
        super("../static/fist.png");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
