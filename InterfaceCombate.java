import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfaceCombate extends JPanel {
    private static JFrame janela;
    private static PainelCombate painelAtual;

    public InterfaceCombate(Jogador jogador) {
        setLayout(new GridLayout());
    }

    public static void mostrarJanelaCombate(Jogador jogador, Zumbi zumbi, ActionListener onAtacar, ActionListener onFugir) {
        if (janela == null) {
            janela = new JFrame("Combate");
            janela.setSize(300, 200);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            janela.setLocationRelativeTo(null);
        }

        janela.setContentPane(new PainelCombate(jogador, zumbi, onAtacar, onFugir));
        janela.setVisible(true);
    }

    public static void atualizarInterface(Jogador jogador, Zumbi zumbi) {
        if (painelAtual != null) {
            painelAtual.atualizarDados(jogador, zumbi);
        }
    }

    public static void fecharJanela() {
        if (janela != null) {
            janela.dispose();
            janela = null;
        }
    }

    private static class PainelCombate extends JPanel {
        private JLabel labelSaudeJogador;
        private JLabel labelSaudeZumbi;
        private JButton btnAtacar;
        private JButton btnFugir;

        public PainelCombate(Jogador jogador, Zumbi zumbi, ActionListener onAtacar, ActionListener onFugir) {
            setLayout(new GridLayout(4, 1));

            labelSaudeJogador = new JLabel("Sua saúde: " + jogador.getSaude());
            labelSaudeZumbi = new JLabel("Saúde do zumbi: " + zumbi.getSaude());
            add(new JLabel("Combate contra: " + zumbi.getClass().getSimpleName()));

            JPanel botoes = new JPanel(new FlowLayout());
            btnAtacar = new JButton("Atacar");
            btnFugir = new JButton("Fugir");

            btnAtacar.addActionListener(onAtacar);
            btnFugir.addActionListener(e -> {
                onFugir.actionPerformed(e);
                fecharJanela();
            });

            botoes.add(btnAtacar);
            botoes.add(btnFugir);
            add(botoes);
        }

        private void atualizarDados(Jogador jogador, Zumbi zumbi) {
            labelSaudeJogador.setText("Sua saúde: " + jogador.getSaude());
            labelSaudeZumbi.setText("Saúde do zumbi: " + zumbi.getSaude());
            revalidate();
            repaint();
        }
    }
}

