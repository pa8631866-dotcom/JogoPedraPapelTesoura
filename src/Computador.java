import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Computador extends Jogador {
    private Random random = new Random();

    public Computador() {
        super("src/robot.png", "src/crying.png", "Você perdeu!");
    }

    @Override
    public CompletableFuture<Opcao> fazerEscolha() {
        int indiceEscolha = random.nextInt(Motor.Opcoes.length);
        Opcao escolha = Motor.Opcoes[indiceEscolha];
        var future =  new CompletableFuture<Opcao>();
        future.complete(escolha);
        return future;
    }
}
