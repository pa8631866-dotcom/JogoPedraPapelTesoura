package jogopedrapapeltesoura;

import java.util.concurrent.CompletableFuture;

public class Pessoa extends Jogador implements Ouvinte {
    private CompletableFuture<Opcao> future;

    public Pessoa() {
        super("../static/wink.png", "../static/tongue-out.png", "Você ganhou!");

        future = new CompletableFuture<>();
    }

    @Override
    public CompletableFuture<Opcao> fazerEscolha() {
        return future;
    }

    @Override
    public void alertarEscolha(Opcao opcao) {
        var futureAntiga = future;
        future = new CompletableFuture<>();
        futureAntiga.complete(opcao);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
