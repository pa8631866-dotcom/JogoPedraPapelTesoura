package jogopedrapapeltesoura;

public class Motor {
    public static int PontosAteVitoria = 10;

    public static Opcao Pedra = new Pedra();
    public static Opcao Papel = new Papel();
    public static Opcao Tesoura = new Tesoura();

    public static Opcao[] Opcoes = { Pedra, Papel, Tesoura };

    private Jogador jogador1;
    private Jogador jogador2;
    private GerenciadorDeEventos gerenciadorDeEventos;
    private Persistencia persistencia;
    private Placar placar;
    private UI ui;

    public Motor() {
        jogador1 = new Pessoa();
        jogador2 = new Computador();
        gerenciadorDeEventos = new GerenciadorDeEventos();
        persistencia = new Persistencia();
        placar = new Placar(jogador1, jogador2, persistencia);
        ui = new UI(gerenciadorDeEventos, placar);

        gerenciadorDeEventos.registrar((Ouvinte) jogador1);

        fazerEmbate();
    }

    public void fazerEmbate() {
        jogador1.fazerEscolha().thenAccept(escolhaJogador1 -> {
            jogador2.fazerEscolha().thenAccept(escolhaJogador2 -> {
                var jogador1Ganhou = Jogador1GanhaDoJogador2(escolhaJogador1, escolhaJogador2);

                if (jogador1Ganhou != null) {
                    placar.pontoParaJogador(jogador1Ganhou ? jogador1 : jogador2);
                }

                ui.atualizarResultado(escolhaJogador1, escolhaJogador2);

                if (placar.getPontuacao().containsValue(PontosAteVitoria)) {
                    finalizar(jogador1Ganhou ? jogador1 : jogador2);
                } else {
                    fazerEmbate();
                }
            });
        });
    }

    public void finalizar(Jogador jogadorQueGanhou) {
        ui.mostrarResultadoFinal(jogadorQueGanhou);
        placar.reiniciar();
        ui.resetar();
        fazerEmbate();
    }

    public static Boolean Jogador1GanhaDoJogador2(Opcao escolhaJogador1,  Opcao escolhaJogador2) {
        if (escolhaJogador1 == escolhaJogador2) {
            return null;
        }

        if (escolhaJogador1 == Pedra && escolhaJogador2 == Tesoura) {
            return true;
        }

        if (escolhaJogador1 == Papel && escolhaJogador2 == Pedra) {
            return true;
        }

        if (escolhaJogador1 == Tesoura && escolhaJogador2 == Papel) {
            return true;
        }

        return false;
    }
}
