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
            janela.setSize(350, 200);
            janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            janela.setLocationRelativeTo(null);
            janela.setResizable(false); // Impede redimensionamento
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
        private JLabel labelTitulo;
        private JLabel labelSaudeJogador;
        private JLabel labelSaudeZumbi;
        private JButton btnAtacar;
        private JButton btnFugir;

        public PainelCombate(Jogador jogador, Zumbi zumbi, ActionListener onAtacar, ActionListener onFugir) {
            // Configura o layout com bordas e fundo
            setLayout(new BorderLayout(10, 10)); // Espaçamento entre componentes
            setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Margens internas
            setBackground(new Color(20, 20, 20)); // Fundo cinza escuro (tema sombrio)

            // Título estilizado
            labelTitulo = new JLabel("Combate contra: " + zumbi.getClass().getSimpleName());
            labelTitulo.setFont(new Font("Arial", Font.BOLD, 16)); // Fonte maior e negrito
            labelTitulo.setForeground(Color.RED); // Texto vermelho
            labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            add(labelTitulo, BorderLayout.NORTH);

            // Painel de saúde
            JPanel saudePanel = new JPanel(new GridLayout(2, 1, 5, 5));
            saudePanel.setOpaque(false); // Fundo transparente para usar o do PainelCombate
            labelSaudeJogador = new JLabel("Sua saúde: " + jogador.getSaude());
            labelSaudeZumbi = new JLabel("Saúde do zumbi: " + zumbi.getSaude());
            labelSaudeJogador.setFont(new Font("Arial", Font.PLAIN, 14));
            labelSaudeZumbi.setFont(new Font("Arial", Font.PLAIN, 14));
            labelSaudeJogador.setForeground(Color.GREEN); // Verde para saúde do jogador
            labelSaudeZumbi.setForeground(Color.ORANGE); // Laranja para saúde do zumbi
            saudePanel.add(labelSaudeJogador);
            saudePanel.add(labelSaudeZumbi);
            add(saudePanel, BorderLayout.CENTER);

            // Painel de botões
            JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
            botoes.setOpaque(false);
            btnAtacar = new JButton("Atacar");
            btnFugir = new JButton("Fugir");

            // Estiliza os botões
            btnAtacar.setFont(new Font("Arial", Font.BOLD, 12));
            btnFugir.setFont(new Font("Arial", Font.BOLD, 12));
            btnAtacar.setBackground(new Color(139, 0, 0)); // Vermelho-escuro
            btnFugir.setBackground(new Color(105, 105, 105)); // Cinza
            btnAtacar.setForeground(Color.WHITE);
            btnFugir.setForeground(Color.WHITE);
            btnAtacar.setFocusPainted(false); // Remove borda de foco
            btnFugir.setFocusPainted(false);
            btnAtacar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            btnFugir.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            // Ajuste para garantir padding
            btnAtacar.setMargin(new Insets(10, 20, 10, 20)); // Padding interno
            btnFugir.setMargin(new Insets(10, 20, 10, 20));
            btnAtacar.setPreferredSize(new Dimension(100, 40)); // Tamanho fixo para respeitar margens
            btnFugir.setPreferredSize(new Dimension(100, 40));

            btnAtacar.addActionListener(onAtacar);
            btnFugir.addActionListener(e -> {
                onFugir.actionPerformed(e);
                fecharJanela();
            });

            botoes.add(btnAtacar);
            botoes.add(btnFugir);
            add(botoes, BorderLayout.SOUTH);
        }

        private void atualizarDados(Jogador jogador, Zumbi zumbi) {
            labelSaudeJogador.setText("Sua saúde: " + jogador.getSaude());
            labelSaudeZumbi.setText("Saúde do zumbi: " + zumbi.getSaude());
            revalidate();
            repaint();
        }
    }
}

