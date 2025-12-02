package jogopedrapapeltesoura;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Computador extends Jogador {
    private Random random;

    public Computador() {
        super("../static/robot.png", "../static/crying.png", "Você perdeu!");

        random = new Random();
    }

    @Override
    public CompletableFuture<Opcao> fazerEscolha() {
        int indiceEscolha = random.nextInt(Motor.Opcoes.length);
        Opcao escolha = Motor.Opcoes[indiceEscolha];
        var future = new CompletableFuture<Opcao>();
        future.complete(escolha);
        return future;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
