import java.util.concurrent.CompletableFuture;

public abstract class Jogador {
    private String avatar;
    private String iconeVitoria;
    private String mensagemVitoria;

    protected Jogador(String avatar, String iconeVitoria, String mensagemVitoria) {
        this.avatar = avatar;
        this.iconeVitoria = iconeVitoria;
        this.mensagemVitoria = mensagemVitoria;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getIconeVitoria() {
        return iconeVitoria;
    }

    public String getMensagemVitoria() {
        return mensagemVitoria;
    }

    public abstract CompletableFuture<Opcao> fazerEscolha();
}
