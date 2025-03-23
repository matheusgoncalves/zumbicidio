import javax.swing.*;
import java.awt.*;

public class InterfaceCombate extends JPanel  {
    private static JFrame janela;

    public InterfaceCombate(Jogador jogador) {
        setLayout(new GridLayout());
    }

    public static void mostrarJanelaCombate(Jogador jogador, Zumbi zumbi) {
        if (janela == null) {
            janela = new JFrame("Combate");
            janela.setSize(300, 200);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

        janela.setContentPane(new PainelCombate(jogador, zumbi));
        janela.setVisible(true);
    }

    private static class PainelCombate extends JPanel {
        private JLabel labelSaudeJogador;
        private JLabel labelSaudeZumbi;

        public PainelCombate(Jogador jogador, Zumbi zumbi) {
            setLayout(new GridLayout(4, 1));

            labelSaudeJogador = new JLabel("Sua saúde: " + jogador.getSaude());
            labelSaudeZumbi = new JLabel("Saúde do zumbi: " + zumbi.getSaude());
            add(new JLabel("Combate contra: " + zumbi.getClass().getSimpleName()));

            JPanel botoes = new JPanel(new FlowLayout());
            JButton atacarBtn = new JButton("Atacar");
            JButton fugirBtn = new JButton("Fugir");
        }
    }
}

