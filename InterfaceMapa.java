import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class InterfaceMapa extends JPanel {
    private final Mapa mapa;
    private final JButton[][] grid;

    public InterfaceMapa(Mapa mapa) {
        this.mapa = mapa;
        setLayout(new BorderLayout()); // Muda para BorderLayout para acomodar a área de mensagens

        // Grid do mapa
        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        grid = new JButton[10][10];
        criarGrid(gridPanel);
        add(gridPanel, BorderLayout.CENTER);

        atualizarGrid();
    }

    // Adicionar método criarGrid
    private void criarGrid(JPanel gridPanel) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new JButton();
                int x = i;
                int y = j;

                // Passar referência do jogador
                Jogador jogador = (Jogador) mapa.getJogador();

                grid[i][j].addActionListener(e -> {
                    jogador.mover(x, y);
                    atualizarGrid(); // Atualiza a interface após o movimento
                });
                gridPanel.add(grid[i][j]);
            }
        }
    }

    // Atualiza o ícone
    public void atualizarGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j].setIcon(obterIcone(i, j));
            }
        }
    }

    //seleciona o icone correspondente ao grid
    private ImageIcon obterIcone(int x, int y) {
        List<Object> elementos = mapa.getCelula(x, y);

        // Prioridade: mostrar o jogador se estiver presente
        for (Object elemento : elementos) {
            if (elemento instanceof Jogador) {
                return new ImageIcon("sprites/hero.png");
            }
        }

        // Prioridade 2: Mostrar outros elementos
        for (Object elemento : elementos) {
            if (elemento instanceof Parede) {
                return new ImageIcon("sprites/parede.png");
            } else if (elemento instanceof ZumbiComum) {
                return new ImageIcon("sprites/zombie.png");
            } else if (elemento instanceof Bau) {
                Bau bau = (Bau) elemento;
                return bau.estaAberto()
                        ? new ImageIcon("sprites/chest2.png")
                        : new ImageIcon("sprites/chest.jpg");
            } else if (elemento instanceof ZumbiGigante) {
                return new ImageIcon("sprites/giantzombie.png");
            } else if (elemento instanceof ZumbiCorredor) {
                return new ImageIcon("sprites/runner.png");
            } else if (elemento instanceof ZumbiRastejante) {
                return new ImageIcon("sprites/rastejante.png");
            }
        }

        return new ImageIcon("sprites/chao.png");
    }
}