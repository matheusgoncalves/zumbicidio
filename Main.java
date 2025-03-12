import javax.swing.*;
import java.awt.*;

public class Main {

    // transformar em um json
    private static final String[][] MAPA1 = {
            {"", "p", "", "b", "", "", "", "z", "", ""},
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

    public static void main(String[] args) {
        Mapa Mapa = new Mapa(MAPA1, 0, 0);

        JFrame janela = new JFrame("Mapa Grid");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 600);

        InterfaceMapa painelMapa = new InterfaceMapa(Mapa);
        janela.add(painelMapa, BorderLayout.CENTER);

        janela.setVisible(true);
    }
}