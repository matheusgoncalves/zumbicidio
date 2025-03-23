import java.util.Objects;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Mapa {
    private final Object[][] grid; // Agora aceita qualquer tipo de objeto
    private final int TAMANHO = 10;
    private final Jogador jogador;
    private List<Zumbi> zumbis = new ArrayList<>();

    public Mapa(String[][] mapaSimbolos, Jogador jogador) {
        this.grid = new Object[TAMANHO][TAMANHO];
        this.jogador = jogador;
        jogador.vincularMapa(this);
        inicializarGrid(mapaSimbolos);
    }

    // Converte símbolos para objetos
    private void inicializarGrid(String[][] mapaSimbolos) {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                switch (Objects.requireNonNull(mapaSimbolos[i])[j]) {
                    case "p":
                        assert grid[i] != null;
                        grid[i][j] = new Parede();
                        break;
                    case "z":
                        assert grid[i] != null;
                        grid[i][j] = new ZumbiComum(i, j);
                        break;
                    case "b":
                        assert grid[i] != null;
                        grid[i][j] = new Bau(gerarNumeroAleatorio());
                        break;
                    case "h": // Posição inicial do herói
                        assert grid[i] != null;
                        grid[i][j] = jogador;
                        jogador.posicaoX = i;
                        jogador.posicaoY = j;
                        break;
                    // Adicione outros casos conforme necessário
                    default:
                        assert grid[i] != null;
                        grid[i][j] = null;
                }
            }
        }
    }

    // Implementar gerarNumeroAleatorio
    private int gerarNumeroAleatorio() {
        return new Random().nextInt(4) + 1;
    }

    // Método para atualizar posições
    public void atualizarPosicao(int antigoX, int antigoY, int novoX, int novoY) {
        Object elemento = grid[antigoX][antigoY];

        // Atualiza as coordenadas do elemento (se for um Personagem)
        if (elemento instanceof Personagem) {
            ((Personagem) elemento).posicaoX = novoX;
            ((Personagem) elemento).posicaoY = novoY;
        }

        grid[antigoX][antigoY] = null;
        grid[novoX][novoY] = elemento;
    }

    // Método para verificar colisões
    public boolean posicaoValida(int x, int y) {
        if (x < 0 || x >= TAMANHO || y < 0 || y >= TAMANHO) {
            return false;
        }

        return !(grid[x][y] instanceof Parede);
    }

    // Método para acessar elementos
    public Object getCelula(int x, int y) {
        return grid[x][y];
    }

    public void removerZumbi(Zumbi zumbi) {
        grid[zumbi.getX()][zumbi.getY()] = null;
        zumbis.remove(zumbi);
    }

    public List<Zumbi> getZumbis() {
        return zumbis;
    }
}
