import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Main extends JFrame implements ActionListener {
    private JButton btnPedra, btnPapel, btnTesoura, btnReiniciar;
    private JLabel lblResultado, lblComputador, lblJogador, lblPlacar;
    private Random random;

    private int vitorias = 0;
    private int derrotas = 0;
    private int empates = 0;

    public Main() {
        super("🎮 Pedra, Papel e Tesoura");
        random = new Random();

        setLayout(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 10, 10));
        btnPedra = new JButton("🪨 Pedra");
        btnPapel = new JButton("📄 Papel");
        btnTesoura = new JButton("✂️ Tesoura");

        btnPedra.addActionListener(this);
        btnPapel.addActionListener(this);
        btnTesoura.addActionListener(this);

        painelBotoes.add(btnPedra);
        painelBotoes.add(btnPapel);
        painelBotoes.add(btnTesoura);

        JPanel painelInfo = new JPanel(new GridLayout(4, 1, 5, 5));
        lblJogador = new JLabel("Sua escolha: ", SwingConstants.CENTER);
        lblComputador = new JLabel("Computador: ", SwingConstants.CENTER);
        lblResultado = new JLabel("Escolha uma opção para começar!", SwingConstants.CENTER);
        lblPlacar = new JLabel("Placar → Vitórias: 0 | Derrotas: 0 | Empates: 0", SwingConstants.CENTER);

        lblJogador.setFont(new Font("Arial", Font.BOLD, 16));
        lblComputador.setFont(new Font("Arial", Font.BOLD, 16));
        lblResultado.setFont(new Font("Arial", Font.BOLD, 18));
        lblPlacar.setFont(new Font("Arial", Font.BOLD, 15));

        painelInfo.add(lblJogador);
        painelInfo.add(lblComputador);
        painelInfo.add(lblResultado);
        painelInfo.add(lblPlacar);

        JPanel painelInferior = new JPanel();
        btnReiniciar = new JButton("🔄 Reiniciar");
        btnReiniciar.addActionListener(e -> reiniciarPlacar());
        painelInferior.add(btnReiniciar);

        add(painelInfo, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.NORTH);
        add(painelInferior, BorderLayout.SOUTH);

        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] opcoes = {"Pedra", "Papel", "Tesoura"};
        int escolhaComputador = random.nextInt(3);
        String jogadaComputador = opcoes[escolhaComputador];
        String jogadaJogador = "";

        if (e.getSource() == btnPedra) jogadaJogador = "Pedra";
        else if (e.getSource() == btnPapel) jogadaJogador = "Papel";
        else if (e.getSource() == btnTesoura) jogadaJogador = "Tesoura";

        lblJogador.setText("Você escolheu: " + jogadaJogador);
        lblComputador.setText("Computador escolheu: " + jogadaComputador);

        String resultado;
        if (jogadaJogador.equals(jogadaComputador)) {
            resultado = "Empate!";
            empates++;
        } else if (
                (jogadaJogador.equals("Pedra") && jogadaComputador.equals("Tesoura")) ||
                        (jogadaJogador.equals("Tesoura") && jogadaComputador.equals("Papel")) ||
                        (jogadaJogador.equals("Papel") && jogadaComputador.equals("Pedra"))
        ) {
            resultado = "Você venceu! 🎉";
            vitorias++;
        } else {
            resultado = "Você perdeu! 😢";
            derrotas++;
        }

        lblResultado.setText(resultado);
        atualizarPlacar();
    }

    private void atualizarPlacar() {
        lblPlacar.setText(String.format("Placar → Vitórias: %d | Derrotas: %d | Empates: %d",
                vitorias, derrotas, empates));
    }

    private void reiniciarPlacar() {
        vitorias = derrotas = empates = 0;
        lblResultado.setText("Placar reiniciado. Faça sua jogada!");
        atualizarPlacar();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}
