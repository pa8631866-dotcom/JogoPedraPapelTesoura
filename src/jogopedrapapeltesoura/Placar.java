package jogopedrapapeltesoura;

import java.util.LinkedHashMap;
import java.util.Map;

public class Placar {
    private Map<Jogador, Integer> pontuacao;
    private Persistencia persistencia;

    public Placar(Jogador jogador1, Jogador jogador2, Persistencia persistencia) {
        this.persistencia = persistencia;
        this.pontuacao = new LinkedHashMap<>();

        var pontos = persistencia.getPontos();

        pontuacao.put(jogador1, pontos[0]);
        pontuacao.put(jogador2, pontos[1]);
    }

    public void pontoParaJogador(Jogador jogador) {
        pontuacao.put(jogador, pontuacao.get(jogador) + 1);
        var pontos = pontuacao.values().toArray(new Integer[0]);
        persistencia.setPontos(pontos[0], pontos[1]);
    }

    public void reiniciar() {
        pontuacao.replaceAll((jogador, pontos) -> 0);
    }

    public Map<Jogador, Integer> getPontuacao() {
        return pontuacao;
    }

    @Override
    public String toString() {
        return String.join(" | ", pontuacao.entrySet().stream().map(
                x -> "[" + x.getKey() + "] "+ x.getValue()).toArray(String[]::new));
    }
}
