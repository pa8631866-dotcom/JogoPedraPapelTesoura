package jogopedrapapeltesoura;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDeEventos implements ActionListener {
    private Map<JButton, Opcao> botaoDeCadaOpcao;
    private List<Ouvinte> ouvintes;

    public GerenciadorDeEventos() {
        botaoDeCadaOpcao = new HashMap<>();
        ouvintes = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton botao) {
            Opcao opcao = botaoDeCadaOpcao.get(botao);
            if (opcao != null) {
                for (int i = 0; i < ouvintes.size(); i++) {
                    ouvintes.get(i).alertarEscolha(opcao);
                }
            }
        }
    }

    public void registrar(JButton botao, Opcao opcao) {
        botaoDeCadaOpcao.put(botao, opcao);
        botao.addActionListener(this);
    }

    public void registrar(Ouvinte ouvinte) {
        ouvintes.add(ouvinte);
    }
}
