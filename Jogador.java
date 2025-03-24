import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.ActionListener;

public class Jogador extends Personagem {
    private int percepcao; // Percepção utilizada para se desviar de ataques
    protected List<Item> inventario; // Lista de itens coletados
    private Mapa mapa;
    private InterfaceMapa interfaceMapa;
    private Inventario inventarioGUI; // Referência para a interface gráfica

    public Jogador(int x, int y, int dificuldade) {
        super(5, x, y);
        this.percepcao = dificuldade;
        this.inventario = new ArrayList<>();
    }


    // Método para definir a referência do inventário gráfico
    public void setInventarioGUI(Inventario inventarioGUI) {
        this.inventarioGUI = inventarioGUI;
    }

    public void coletarItem(Item item) {
        // Lógica específica para revólver (acumula munição)
        if (item instanceof Revolver) {
            for (Item itemExistente : inventario) {
                if (itemExistente instanceof Revolver) {
                    Revolver revolverExistente = (Revolver) itemExistente;
                    revolverExistente.adicionarMunicao();
                    Mensagem.exibirMensagem("Munição do revólver aumentada para: " + revolverExistente.getMunicao());
                    atualizarInventarioGUI(); // Atualiza a interface
                    return;
                }
            }
        }

        inventario.add(item);
        Mensagem.exibirMensagem("Item coletado: " + item.getNome());
        atualizarInventarioGUI(); // Atualiza a interface
    }

    // Método para atualizar a interface gráfica do inventário
    private void atualizarInventarioGUI() {
        if (inventarioGUI != null) {
            inventarioGUI.atualizarBotoes();
        }
    }

    @Override
    public void mover(int novoX, int novoY) {
        Mapa mapa = getMapaAtual(); // Você precisa ter uma referência ao mapa

        if (mapa == null) {
            System.out.println("Erro: Mapa não vinculado ao jogador!");
            return;
        }

        // Calcula a distância do movimento
        int distanciaX = Math.abs(novoX - posicaoX);
        int distanciaY = Math.abs(novoY - posicaoY);

        // Só permite movimento de 1 casa (horizontal ou vertical)
        if ((distanciaX > 1 || distanciaY > 1) || (distanciaX + distanciaY != 1)) {
            Mensagem.exibirMensagem("Movimento inválido! Só pode mover 1 casa por vez.");
            return;
        }

        if (!mapa.posicaoValida(novoX, novoY)) {
            Mensagem.exibirMensagem("Movimento bloqueado!");
            return;
        }

        // Verifica colisão com zumbi
        List<Object> celulas = mapa.getCelula(novoX, novoY);
        for (Object elemento: celulas) {
            if (elemento instanceof Zumbi) {
                iniciarCombate((Zumbi) elemento);
                return;
            } else if (elemento instanceof Bau bau) {
                this.abrirBau(bau);
            }
        }

        // Atualiza posição
        mapa.atualizarPosicao(posicaoX, posicaoY, novoX, novoY);
        posicaoX = novoX;
        posicaoY = novoY;
        interfaceMapa.atualizarGrid();
    }

    public void vincularMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    private Mapa getMapaAtual() {
        return mapa;
    }

    // Método auxiliar para escolher arma (simplificado por enquanto)
    private Arma escolherArma() {
        // Por enquanto, retorna null (ataque com as mãos) ou a primeira arma do inventário
        if (inventario.isEmpty()) {
            return null; // Ataque com as mãos
        }
        for (Item item : inventario) {
            if (item instanceof Arma) {
                return (Arma) item; // Usa a primeira arma encontrada
            }
        }
        return null;
    }

    public void iniciarCombate(Zumbi zumbi) {
        Random random = new Random();
        Mensagem.exibirMensagem("Combate iniciado contra " + zumbi.getClass().getSimpleName() + "!");

        Arma[] armaSelecionada = {escolherArma()};
        atacar(zumbi, armaSelecionada[0]);
        interfaceMapa.atualizarGrid();
        InterfaceCombate.atualizarInterface(this, zumbi);

        // Configura os listeners para as ações de atacar e fugir
        final boolean[] combateAtivo = {true}; // Controle local do combate
        ActionListener onAtacar = e -> {
            if (!zumbi.estaVivo()) {
                mapa.removerZumbi(zumbi);
                interfaceMapa.atualizarGrid();
                InterfaceCombate.fecharJanela();
                combateAtivo[0] = false;
            }

            if (combateAtivo[0] && zumbi.estaVivo() && this.estaVivo()) {
                armaSelecionada[0] = escolherArma();
                atacar(zumbi, armaSelecionada[0]);

                if (!zumbi.estaVivo()) {
                    mapa.removerZumbi(zumbi);
                    interfaceMapa.atualizarGrid();
                    InterfaceCombate.fecharJanela();
                    combateAtivo[0] = false;
                } else {
                    // Turno do zumbi
                    int dadoEsquiva = random.nextInt(6) + 1;

                    if (esquivar(dadoEsquiva)) {
                        Mensagem.exibirMensagem("(O zumbi está atacando...)");
                    } else {
                        zumbi.atacar(this);
                    }

                    InterfaceCombate.atualizarInterface(this, zumbi);
                    interfaceMapa.atualizarGrid();

                    if (!this.estaVivo()) {
                        Mensagem.exibirMensagem("Derrota! Você foi derrotado pelo zumbi!");
                        InterfaceCombate.fecharJanela();
                        combateAtivo[0] = false;
                        return;
                    }
                }
            }
        };

        ActionListener onFugir = e -> {
            if (combateAtivo[0]) {
                Mensagem.exibirMensagem("Você fugiu do combate!");
                combateAtivo[0] = false;
            }
        };

        // Mostra a janela de combate com os listeners
        InterfaceCombate.mostrarJanelaCombate(this, zumbi, onAtacar, onFugir);
    }


    public void usarItem(Item item) {
        if (item instanceof Usavel) {
            ((Usavel) item).usar(this);
            inventario.remove(item); // Remove o item após o uso
        } else {
            Mensagem.exibirMensagem("Este item não pode ser usado!");
        }
    }

    // Método para recuperar vida (ao utilizar uma atadura)
    public void recuperarVida(int vida) {
        saude += vida;
        Mensagem.exibirMensagem("O jogador recuperou " + vida + " de vida! Saúde atual: " + saude);
    }

    // Método para atacar um zumbi
    public void atacar(Zumbi zumbi, Arma arma) {
        if (zumbi.validarAtaque(arma)) {
            int dano = 0;

            if (arma != null) {
                if (arma instanceof Revolver) {
                    Revolver revolver = (Revolver) arma;

                    if (!revolver.atirar()) {
                        Mensagem.exibirMensagem("Falha ao atirar com o revólver.");
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
            if (!zumbi.estaVivo()) {
                mapa.removerZumbi(zumbi);
            }
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
    }

    public void vincularInterfaceMapa(InterfaceMapa interfaceMapa) {
        this.interfaceMapa = interfaceMapa;
    }

    // Método para abrir um baú
    public void abrirBau(Bau bau) {
        String resultado = bau.abrir();
        System.out.println(resultado);

        if ((bau.getConteudo() instanceof Revolver)) {
            // Cria e adiciona o Zumbi Rastejante ao mapa
            ZumbiRastejante zumbiRastejante = new ZumbiRastejante(posicaoX, posicaoY); // Aparece na posição do jogador
            mapa.getCelula(posicaoX, posicaoY).add(zumbiRastejante); // Adiciona ao grid
            mapa.getZumbis().add(zumbiRastejante); // Adiciona à lista de zumbis
            Mensagem.exibirMensagem("Um Zumbi Rastejante apareceu!");

            // Inicia combate com o Zumbi Rastejante
            iniciarCombate(zumbiRastejante);

            // Remove o zumbi se estiver morto após o combate
            if (!zumbiRastejante.estaVivo()) {
                mapa.removerZumbi(zumbiRastejante);
            }
        } else if (bau.getConteudo() != null) {
            coletarItem(bau.getConteudo());
        }

        interfaceMapa.atualizarGrid();
    }

    // Método para exibir a percepção em determinada rolagem
    public int getPercepcao() {
        return percepcao;
    }

    // Método para listar itens disponíveis
    public List<Item> getItensDisponiveis() {
        return new ArrayList<>(inventario);
    }

    public List<Item> getInventario() {
        return inventario;
    }
}
