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
    private JFrame janela;

    public Jogador(int x, int y, int dificuldade) {
        super(5, x, y);
        this.percepcao = dificuldade;
        this.inventario = new ArrayList<>();
    }

    public void setJanela(JFrame janela) {
        this.janela = janela;
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
            Mensagem.exibirMensagem("Movimento inválido! Certifique-se de mover-se apenas 1 casa por vez.");
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

    private Arma escolherArma() {
        List<Arma> armasDisponiveis = new ArrayList<>();
        boolean temTaco = false;

        // Coleta todas as armas do inventário e verifica se há Taco de Beisebol
        for (Item item : inventario) {
            if (item instanceof Arma) {
                armasDisponiveis.add((Arma) item);
                if (item instanceof TacoDeBeisebol) {
                    temTaco = true;
                }
            }
        }

        // Se não há armas, retorna null (mãos)
        if (armasDisponiveis.isEmpty()) {
            return null;
        }

        // Monta as opções para o diálogo
        List<String> opcoes = new ArrayList<>();
        for (Arma arma : armasDisponiveis) {
            opcoes.add(arma.getNome());
        }
        if (!temTaco) {
            opcoes.add("Usar as mãos");
        }

        // Exibe o diálogo de escolha
        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Escolha uma arma para atacar:",
                "Seleção de Arma",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes.toArray(),
                opcoes.get(0)
        );

        // Retorna a arma escolhida ou null para mãos
        if (escolha == null || escolha.equals("Usar as mãos")) {
            return null;
        }
        for (Arma arma : armasDisponiveis) {
            if (arma.getNome().equals(escolha)) {
                return arma;
            }
        }
        return null; // Fallback, caso algo dê errado
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
                verificarVitoria();
            }

            if (combateAtivo[0] && zumbi.estaVivo() && this.estaVivo()) {
                armaSelecionada[0] = escolherArma();
                atacar(zumbi, armaSelecionada[0]);

                if (!zumbi.estaVivo()) {
                    mapa.removerZumbi(zumbi);
                    interfaceMapa.atualizarGrid();
                    InterfaceCombate.fecharJanela();
                    combateAtivo[0] = false;
                    verificarVitoria();
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
                        Mensagem.exibirMensagem("Você foi derrotado pelo zumbi!\nVocê perdeu.");
                        InterfaceCombate.fecharJanela();
                        combateAtivo[0] = false;
                        System.exit(0); // Encerra a aplicação
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

    // Método para verificar vitória
    private void verificarVitoria() {
        if (mapa.getZumbis().isEmpty()) {
            Mensagem.exibirMensagem("Parabéns! Todos os zumbis foram derrotados.\nVocê venceu!");
            if (janela != null) {
                janela.dispose(); // Fecha a janela do jogo
            }
            System.exit(0); // Encerra a aplicação
        }
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
                    } else{dano = revolver.getDano();}
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
        // Verifica se o baú já está aberto antes de qualquer ação
        if (bau.estaAberto()) {
            return;
        }

        String resultado = bau.abrir();
        System.out.println(resultado);

        if ((bau.getConteudo() instanceof Revolver)) {
            coletarItem(bau.getConteudo());

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
