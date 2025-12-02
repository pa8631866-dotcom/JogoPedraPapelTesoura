package jogopedrapapeltesoura;

import java.net.URL;

public abstract class Opcao {
    private URL icone;

    protected Opcao(String icone) {
        this.icone = getClass().getResource(icone);
    }

    public URL getIcone() {
        return icone;
    }
}
