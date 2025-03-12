import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Jogador extends Personagem {
    private int percepcao; // Percepção utilizada para se desviar de ataques
    protected List<Item> inventario; // Lista de itens coletados

    public Jogador(int x, int y, int dificuldade) {
        super(5, x, y);
        this.percepcao = dificuldade;
        this.inventario = new ArrayList<>();
    }

    // Coletar qualquer tipo de item
    public void coletarItem(Item item) {
        // Lógica específica para revólver (acumula munição)
        if (item instanceof Revolver) {
            for (Item itemExistente : inventario) {
                if (itemExistente instanceof Revolver) {
                    Revolver revolverExistente = (Revolver) itemExistente;
                    revolverExistente.adicionarMunicao();
                    System.out.println("Munição do revólver aumentada para: " + revolverExistente.getMunicao());
                    return;
                }
            }
        }

        inventario.add(item);

        System.out.println("Item coletado: " + item.getNome());
    }

    public void usarItem(Item item) {
        if (item instanceof Usavel) {
            ((Usavel) item).usar(this);
            inventario.remove(item); // Remove o item após o uso
        } else {
            System.out.println("Este item não pode ser usado!");
        }
    }

    // Método para recuperar vida (ao utilizar uma atadura)
    public void recuperarVida(int vida) {
        saude += vida;
        System.out.println("O jogador recuperou " + vida + " de vida! Saúde atual: " + saude);
    }

    // Método para atacar um zumbi
    public void atacar(Zumbi zumbi, Arma arma) {
        if (zumbi.validarAtaque(arma)) {
            int dano = 0;

            if (arma != null) {
                if (arma instanceof Revolver) {
                    Revolver revolver = (Revolver) arma;

                    if (!revolver.atirar()) {
                        System.out.println("Falha ao atirar com o revólver.");
                        return;
                    }
                } else {
                    dano = arma.getDano();
                }
            } else {
                // Ataque com as mãos
                Random random = new Random();
                int dado = random.nextInt(6) + 1;
                dano = (dado == 6) ? 2 : 1;
            }

            zumbi.receberDano(dano);
            System.out.println("O zumbi recebeu " + dano + " de dano!"); 
        } else {
            System.out.println(zumbi.getMensagemErro());
        }
    }

    // Método para esquivar
    public boolean esquivar(int dado) {
        return dado <= percepcao;
    }

    // Método para receber dano
    public void receberDano(int dano) {
        saude -= dano;
        System.out.println("O jogador recebeu " + dano + " de dano! Saúde atual: " + saude);
    }

    // Método para abrir um baú
    public void abrirBau(Bau bau) {
        String resultado = bau.abrir();
        System.out.println(resultado);

        if (bau.getConteudo() instanceof Revolver) {
            // Encontra um zumbi rastejante
            System.out.println("Um Zumbi Rastejante apareceu!");

            // Tentar esquivar
            Random random = new Random();
            int dado = random.nextInt(6) + 1;
            if (esquivar(dado)) {
                System.out.println("Você conseguiu esquivar do Zumbi Rastejante!");
            } else {
                System.out.println("Você não conseguiu esquivar do Zumbi Rastejante!");
                receberDano(1); // Recebe dano do zumbi rastejante
            }
        } else if (bau.getConteudo() != null) {
            coletarItem(bau.getConteudo());
        }
    }

    // Método para exibir a percepção em determinada rolagem
    public int getPercepcao() {
        return percepcao;
    }

    // Método para listar itens disponíveis
    public List<Item> getItensDisponiveis() {
        return new ArrayList<>(inventario);
    }
}
