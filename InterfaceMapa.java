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


    //cria o grid e coloca a ação de mover nos botões
    private void criarGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new JButton();
                int x = i;
                int y = j;

                grid[i][j].addActionListener(e -> {
                    Jogador.moverHeroi(x, y);
                    atualizarGrid();
                });

                add(grid[i][j]);
            }
        }
    }

    //atualiza o icone
    public void atualizarGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j].setIcon(obterIcone(i, j));
                if(Mapa.getCelula(i,j).equals("")){ System.out.print(" ,");}
                System.out.print(Mapa.getCelula(i,j) + ",");
            }
                System.out.println();
        }
    }

    //seleciona o icone correspondente ao grid
    private ImageIcon obterIcone(int x, int y) {
        if (x == Jogador.getHeroiX() && y == Jogador.getHeroiY()) {
            return new ImageIcon("sprites/hero.png");
        }

        switch (Mapa.getCelula(x, y)) {
            case "p": return new ImageIcon("sprites/parede.png");
            case "z": return new ImageIcon("sprites/zombie.png");
            case "b": return new ImageIcon("sprites/chest.jpg");
            case "hb": return new ImageIcon("sprites/chest2.png");
            case "zg": return new ImageIcon("sprites/giantzombie.png");
            case "zr": return new ImageIcon("sprites/runner.png");
            default: return new ImageIcon("sprites/chao.png");
        }
    }
}