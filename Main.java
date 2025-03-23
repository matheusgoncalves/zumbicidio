import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        String[][] MAPA1 = {
                {"h", "p", "", "b", "", "", "", "z", "", ""},
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

        Jogador jogador = new Jogador(0,0,0);
        Mapa mapa = new Mapa(MAPA1, jogador);

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
