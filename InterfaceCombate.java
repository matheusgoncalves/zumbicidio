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
        public PainelCombate(Jogador jogador, Zumbi zumbi) {
            setLayout(new GridLayout(4, 1));

//            add(new JLabel("Combate contra: " + zumbi.getTipo()));
            add(new JLabel("Sua saúde: " + jogador.getSaude()));
            add(new JLabel("Saúde do zumbi: " + zumbi.getSaude()));

            JButton atacarBtn = new JButton("Atacar");
//            atacarBtn.addActionListener(e -> {
//                jogador.atacar(zumbi, jogador.getArmaEquipada());
//                atualizarDados(jogador, zumbi);
//            });
            add(atacarBtn);
        }

        private void atualizarDados(Jogador jogador, Zumbi zumbi) {
            // Atualiza labels
        }
    }
}

