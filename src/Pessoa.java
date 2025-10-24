import java.util.concurrent.CompletableFuture;

public class Pessoa extends Jogador implements Ouvinte {
    private CompletableFuture<Opcao> future = new CompletableFuture<>();

    public Pessoa() {
        super("src/wink.png", "src/tongue-out.png", "Você ganhou!");
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
}
