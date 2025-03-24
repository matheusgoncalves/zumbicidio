import java.util.Objects;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Mapa {
    private final List<Object>[][] grid; // Matriz de listas
    private final int TAMANHO = 10;
    private final Jogador jogador;
    private List<Zumbi> zumbis = new ArrayList<>();
    private List<Integer> numerosDisponiveis; // Lista de números disponíveis para baús

    @SuppressWarnings("unchecked")
    public Mapa(String[][] mapaSimbolos, Jogador jogador) {
        this.grid = new List[TAMANHO][TAMANHO];
        this.jogador = jogador;
        jogador.vincularMapa(this);
        inicializarNumerosDisponiveis();
        inicializarGrid(mapaSimbolos);
    }

    private static final String[][] MAPA1 = {
            {"h", "p", "", "", "", "", "", "z", "", ""},
            {"", "p", "", "p", "zr", "", "", "b", "", ""},
            {"", "p", "", "p", "", "", "p", "p", "", "z"},
            {"", "p", "", "p", "", "", "p", "p", "", ""},
            {"", "", "b", "p", "", "zc", "p", "p", "zc", ""},
            {"", "p", "p", "p", "", "", "p", "p", "zc", ""},
            {"z", "p", "", "", "", "z", "p", "p", "", ""},
            {"", "p", "", "zr", "", "b", "", "", "", ""},
            {"", "p", "p", "p", "p", "p", "", "", "p", ""},
            {"b", "", "", "z", "", "", "", "", "p", "zg"}
    };
    private static final String[][] MAPA2 = {
            {"h", "p", "", "", "p", "z", "", "b", "p", "p"},
            {"", "p", "b", "", "p", "", "p", "", "p", "p"},
            {"", "p", "", "", "p", "", "p", "zr", "", "p"},
            {"", "", "", "", "", "zc", "", "p", "z", ""},
            {"p", "p", "p", "p", "z", "p", "", "", "p", "zc"},
            {"", "", "", "", "", "p", "p", "b", "p", ""},
            {"z", "p", "", "p", "zc", "", "p", "", "p", ""},
            {"", "p", "z", "", "", "zr", "", "p", "", ""},
            {"b", "p", "p", "p", "", "p", "", "p", "z", ""},
            {"p", "p", "p", "p", "", "p", "", "p", "", "zg"}
    };
    private static final String[][] MAPA3 = {
            {"h", "p", "p", "", "", "z", "b", "", "", ""},
            {"", "", "", "p", "", "p", "", "p", "z", ""},
            {"", "", "b", "", "", "p", "p", "p", "p", "zc"},
            {"", "p", "p", "p", "", "zr", "", "", "", "p"},
            {"z", "", "", "p", "zc", "p", "", "", "p", ""},
            {"p", "p", "", "", "", "", "p", "z", "", "p"},
            {"", "", "p", "z", "", "", "", "p", "zr", ""},
            {"p", "", "", "", "", "zc", "", "", "p", "b"},
            {"p", "p", "", "p", "", "p", "", "z", "", ""},
            {"p", "p", "z", "p", "b", "p", "", "p", "p", "zg"}
    };

    static String[][] randomMap(){
        Random rand = new Random();
        int escolha = rand.nextInt(3);
        switch (escolha){
            case (0): return MAPA1;
            case (1): return MAPA2;
            case (2): return MAPA3;
            default: return MAPA1;
        }
    };

    // Inicializa a lista de números disponíveis
    private void inicializarNumerosDisponiveis() {
        numerosDisponiveis = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            numerosDisponiveis.add(i); // Adiciona 1, 2, 3, 4
        }
        Collections.shuffle(numerosDisponiveis); // Embaralha para aleatoriedade
    }

    // Converte símbolos para objetos
    private void inicializarGrid(String[][] mapaSimbolos) {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                grid[i][j] = new ArrayList<>();

                switch (Objects.requireNonNull(mapaSimbolos[i])[j]) {
                    case "p":
                        grid[i][j].add(new Parede());
                        break;
                    case "z":
                        grid[i][j].add(new ZumbiComum(i, j));
                        break;
                    case "zg":
                        grid[i][j].add(new ZumbiGigante(i, j));
                        break;
                    case "zr":
                        grid[i][j].add(new ZumbiCorredor(i, j));
                        break;
                    case "zc":
                        grid[i][j].add(new ZumbiRastejante(i, j));
                        break;
                    case "b":
                        grid[i][j].add(new Bau(gerarNumeroAleatorio()));
                        break;
                    case "h": // Posição inicial do herói
                        grid[i][j].add(jogador);
                        jogador.posicaoX = i;
                        jogador.posicaoY = j;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    // Gera um número único para cada baú, ou repete se necessário
    private int gerarNumeroAleatorio() {
        if (numerosDisponiveis.isEmpty()) {
            // Se todos os números foram usados, retorna um valor padrão ou reinicia
            return new Random().nextInt(4) + 1; // Repetição permitida após esgotar
        }
        // Remove e retorna o primeiro número disponível
        return numerosDisponiveis.remove(0);
    }

    // Método para atualizar posições
    public void atualizarPosicao(int antigoX, int antigoY, int novoX, int novoY) {
        // Remove o jogador da posição antiga
        grid[antigoX][antigoY].remove(jogador);
        // Adiciona o jogador à nova posição
        grid[novoX][novoY].add(jogador);
    }

    // Método para verificar colisões
    public boolean posicaoValida(int x, int y) {
        if (x < 0 || x >= TAMANHO || y < 0 || y >= TAMANHO) {
            return false;
        }
        // Verifica se há parede na lista
        for (Object obj : grid[x][y]) {
            if (obj instanceof Parede) {
                return false;
            }
        }
        return true;
    }

    // Método para acessar elementos
    public List<Object> getCelula(int x, int y) {
        return grid[x][y];
    }

    public void removerZumbi(Zumbi zumbi) {
        grid[zumbi.getX()][zumbi.getY()].remove(zumbi);
        zumbis.remove(zumbi);
    }

    public Jogador getJogador() {
        return jogador;
    }

    public List<Zumbi> getZumbis() {
        return zumbis;
    }
}
