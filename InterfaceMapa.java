import javax.swing.*;
import java.awt.*;

public class InterfaceMapa extends JPanel {
    private final Mapa mapa;
    private final JButton[][] grid;

    public InterfaceMapa(Mapa mapa) {
        this.mapa = mapa;
        setLayout(new GridLayout(10, 10));
        grid = new JButton[10][10];
        criarGrid();
        atualizarGrid();
    }

    // Adicionar método criarGrid
    private void criarGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new JButton();
                int x = i;
                int y = j;

                // Passar referência do jogador
                Jogador jogador = (Jogador) mapa.getCelula(0, 0); // Supondo que o jogador está em (0,0)

                grid[i][j].addActionListener(e -> {
                    jogador.mover(x, y);
                    atualizarGrid(); // Atualiza a interface após o movimento
                });
                add(grid[i][j]);
            }
        }
    }

    // Atualiza o ícone
    public void atualizarGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j].setIcon(obterIcone(i, j));

                Object celula = mapa.getCelula(i, j);
                if (celula == null) {
                    System.out.print(" ,");
                } else {
                    System.out.print(celula.getClass().getSimpleName() + ",");
                }
            }
                System.out.println();
        }
    }

    //seleciona o icone correspondente ao grid
    private ImageIcon obterIcone(int x, int y) {
        Object elemento = mapa.getCelula(x, y);

        if (elemento instanceof Jogador) {
            return new ImageIcon("sprites/hero.png");
        } else if (elemento instanceof Parede) {
            return new ImageIcon("sprites/parede.png");
        } else if (elemento instanceof ZumbiComum) {
            return new ImageIcon("sprites/zombie.png");
        } else if (elemento instanceof Bau) {
            Bau bau = (Bau) elemento;

            if (bau.estaAberto()) {
                return new ImageIcon("sprites/chest2.png");
            } else {
                return new ImageIcon("sprites/chest.png");
            }
        } else if (elemento instanceof ZumbiGigante) {
            return new ImageIcon("sprites/giantzombie.png");
        } else if (elemento instanceof ZumbiCorredor) {
            return new ImageIcon("sprites/runner.png");
        }

        return new ImageIcon("sprites/chao.png");
    }
}