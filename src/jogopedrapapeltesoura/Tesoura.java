package jogopedrapapeltesoura;

public class Tesoura extends Opcao {
    public Tesoura() {
        super("../static/victory.png");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
