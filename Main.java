import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Mapa mapa = new Mapa(Mapa.MAPA1, 0, 0);

        JFrame janela = new JFrame("Mapa Grid");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(600, 600);

        InterfaceMapa painelMapa = new InterfaceMapa(mapa);
        janela.add(painelMapa, BorderLayout.CENTER);

        janela.setVisible(true);
    }
}