package jogopedrapapeltesoura;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI extends JFrame {
    private Placar placar;
    private JLabel placarJogador1;
    private JLabel placarJogador2;
    private JProgressBar progressoJogador1;
    private JProgressBar progressoJogador2;
    private JLabel escolhaJogador1;
    private JLabel escolhaJogador2;
    private JLabel indicador;

    public UI(GerenciadorDeEventos gerenciadorDeEventos, Placar placar) {
        this.placar = placar;

        definirGerais();
        inserirPlacar(placar);
        inserirResultado();
        inserirOpcoes(gerenciadorDeEventos);
    }

    private void definirGerais() {
        setTitle("Pedra, Papel e Tesoura");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void inserirPlacar(Placar placar) {
        JPanel painel1 = new JPanel();
        painel1.setLayout(new BoxLayout(painel1, BoxLayout.Y_AXIS));
        painel1.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel painel2 = new JPanel(new GridLayout(1, 5, 10, 10));
        var jogadores = placar.getPontuacao().entrySet().iterator();

        var pontosJogador1 = jogadores.next();
        painel2.add(new JLabel(new ImageIcon(pontosJogador1.getKey().getAvatar())));
        placarJogador1 = new JLabel(pontosJogador1.getValue().toString(), SwingConstants.RIGHT);
        placarJogador1.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        painel2.add(placarJogador1);

        JLabel vs = new JLabel("vs",  SwingConstants.CENTER);
        vs.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        painel2.add(vs);

        var pontosJogador2 = jogadores.next();
        placarJogador2 = new JLabel(pontosJogador2.getValue().toString(), SwingConstants.LEFT);
        placarJogador2.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        painel2.add(placarJogador2);
        painel2.add(new JLabel(new ImageIcon(pontosJogador2.getKey().getAvatar())));

        painel1.add(painel2);

        JPanel painel3 = new JPanel(new GridLayout(1, 2, 10, 10));

        progressoJogador1 = new JProgressBar(SwingConstants.HORIZONTAL);
        progressoJogador1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        progressoJogador1.setValue(pontosJogador1.getValue());
        progressoJogador1.setMaximum(Motor.PontosAteVitoria);
        painel3.add(progressoJogador1);

        progressoJogador2 = new JProgressBar(SwingConstants.HORIZONTAL);
        progressoJogador2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        progressoJogador2.setValue(pontosJogador2.getValue());
        progressoJogador2.setMaximum(Motor.PontosAteVitoria);
        painel3.add(progressoJogador2);

        painel1.add(painel3);

        add(painel1, BorderLayout.NORTH);
    }

    private void inserirResultado() {
        JPanel painel = new JPanel(new GridLayout(1, 3, 10, 10));

        escolhaJogador1 = new JLabel("", SwingConstants.RIGHT);
        painel.add(escolhaJogador1);

        indicador = new JLabel("",  SwingConstants.CENTER);
        indicador.setFont(new Font("Arial Unicode MS", Font.BOLD, 36));
        painel.add(indicador);

        escolhaJogador2 = new JLabel("", SwingConstants.LEFT);
        painel.add(escolhaJogador2);

        add(painel, BorderLayout.CENTER);
    }

    private void inserirOpcoes(GerenciadorDeEventos gerenciadorDeEventos) {
        JPanel painel = new JPanel(new GridLayout(1, 3, 10, 10));

        for (int i = 0; i < Motor.Opcoes.length; i++) {
            Opcao opcao = Motor.Opcoes[i];
            JButton botao = new JButton(new ImageIcon(opcao.getIcone()));
            botao.setBorderPainted(false);
            botao.setFocusPainted(false);
            botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            painel.add(botao);
            gerenciadorDeEventos.registrar(botao, opcao);
        }

        add(painel, BorderLayout.SOUTH);
    }

    public void atualizarResultado(Opcao escolhaJogador1, Opcao escolhaJogador2) {
        if (escolhaJogador1 != null) {
            this.escolhaJogador1.setIcon(new ImageIcon(escolhaJogador1.getIcone()));
        } else {
            this.escolhaJogador1.setIcon(null);
        }

        if (escolhaJogador2 != null) {
            this.escolhaJogador2.setIcon(new ImageIcon(escolhaJogador2.getIcone()));
        } else {
            this.escolhaJogador2.setIcon(null);
        }

        if (escolhaJogador1 != null && escolhaJogador2 != null) {
            var jogador1Ganhou = Motor.Jogador1GanhaDoJogador2(escolhaJogador1, escolhaJogador2);

            if (jogador1Ganhou == null) {
                indicador.setText("–");
            } else if (jogador1Ganhou) {
                indicador.setText("←");
            } else {
                indicador.setText("→");
            }
        } else {
            indicador.setText("");
        }

        var jogadores = placar.getPontuacao().entrySet().iterator();

        var pontosJogador1 = jogadores.next().getValue();
        placarJogador1.setText(pontosJogador1.toString());
        progressoJogador1.setValue(pontosJogador1);

        var pontosJogador2 = jogadores.next().getValue();
        placarJogador2.setText(pontosJogador2.toString());
        progressoJogador2.setValue(pontosJogador2);
    }

    public void mostrarResultadoFinal(Jogador jogadorQueGanhou) {
        Object[] opcoes = { "Reiniciar" };
        var icone = new ImageIcon(jogadorQueGanhou.getIconeVitoria());
        JOptionPane pane = new JOptionPane(jogadorQueGanhou.getMensagemVitoria(), JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION, icone, opcoes, opcoes[0]);
        JDialog dialog = pane.createDialog(this, "Fim de jogo!");
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
    }

    public void resetar() {
        atualizarResultado(null, null);
    }
}
