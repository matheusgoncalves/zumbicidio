import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceCombate extends JPanel  {

    private JTextArea log;
    private JButton btnAtacar, btnFugir;

        public InterfaceCombate(Jogador jogador, Zumbi zumbi) {
            setLayout(new GridLayout());

            JPanel panelBotoes = new JPanel();
            btnAtacar = new JButton("Atacar");
            btnFugir = new JButton("Fugir");

            panelBotoes.add(btnAtacar);
            panelBotoes.add(btnFugir);
            add(panelBotoes, BorderLayout.SOUTH);

            btnAtacar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //jogador.atacar();
                }
            });

            btnFugir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //jogador.fugir();
                }
            });

        }
}

