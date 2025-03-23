import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        int percepcao = MenuDificuldade.exibirMenu();
        MenuPrincipal menu = MenuPrincipal.exibirMenu();

        System.out.println(menu.isDebugMode());

        Jogador jogador = new Jogador(0,0,percepcao);

        String[][] randomMap = Mapa.randomMap();

        Mapa mapa = new Mapa(randomMap, jogador);

        JFrame janela = new JFrame("Mapa Grid");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 600);

        InterfaceMapa painelMapa = new InterfaceMapa(mapa);
        jogador.vincularInterfaceMapa(painelMapa);
        janela.add(painelMapa, BorderLayout.CENTER);

        janela.setLocationRelativeTo(null);

        janela.setVisible(true);
    }
}
