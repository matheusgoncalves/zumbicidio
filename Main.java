import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int percepcao = MenuDificuldade.exibirMenu();

        MenuPrincipal menu = MenuPrincipal.exibirMenu();

        System.out.println(menu.isDebugMode());

        Jogador jogador = new Jogador(0,0,percepcao);
        Inventario inventarioGUI = new Inventario(jogador);

        String[][] randomMap = Mapa.randomMap();

        Mapa mapa = new Mapa(randomMap, jogador);

        JFrame janela = new JFrame("Zumbicidio");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 600);
        janela.setResizable(false);

        InterfaceMapa painelMapa = new InterfaceMapa(mapa);
        jogador.vincularInterfaceMapa(painelMapa);
        janela.add(painelMapa, BorderLayout.CENTER);

        janela.setLocationRelativeTo(null);

        janela.setVisible(true);

        // Conecta o jogador à interface gráfica
        jogador.setInventarioGUI(inventarioGUI);

        // Exemplo de uso
        jogador.coletarItem(new Revolver());

        inventarioGUI.setVisible(true);

        jogador.coletarItem(new Revolver());
        jogador.coletarItem(new TacoDeBeisebol());
        jogador.coletarItem(new Cura());



        System.out.println(jogador.getItensDisponiveis());

    }

}
