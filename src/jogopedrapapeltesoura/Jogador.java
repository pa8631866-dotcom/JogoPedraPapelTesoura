package jogopedrapapeltesoura;

import java.net.URL;
import java.util.concurrent.CompletableFuture;

public abstract class Jogador {
    private URL avatar;
    private URL iconeVitoria;
    private String mensagemVitoria;

    protected Jogador(String avatar, String iconeVitoria, String mensagemVitoria) {
        this.avatar = getClass().getResource(avatar);
        this.iconeVitoria = getClass().getResource(iconeVitoria);
        this.mensagemVitoria = mensagemVitoria;
    }

    public URL getAvatar() {
        return avatar;
    }

    public URL getIconeVitoria() {
        return iconeVitoria;
    }

    public String getMensagemVitoria() {
        return mensagemVitoria;
    }

    public abstract CompletableFuture<Opcao> fazerEscolha();
}
