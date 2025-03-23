import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDificuldade extends JFrame {
    private JButton btnFacil, btnMedio, btnDificil;
    private int percepcaoEscolhida = -1;

    public MenuDificuldade() {
        setTitle("Escolha a Dificuldade");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        btnFacil = new JButton("Fácil");
        btnMedio = new JButton("Médio");
        btnDificil = new JButton("Difícil");

        btnFacil.addActionListener(new DificuldadeListener(3));
        btnMedio.addActionListener(new DificuldadeListener(2));
        btnDificil.addActionListener(new DificuldadeListener(1));

        add(btnFacil);
        add(btnMedio);
        add(btnDificil);
    }

    private class DificuldadeListener implements ActionListener {
        private int percepcao;

        public DificuldadeListener(int percepcao) {
            this.percepcao = percepcao;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            percepcaoEscolhida = percepcao;
            JOptionPane.showMessageDialog(null, "Dificuldade escolhida! Percepção: " + percepcaoEscolhida);
            dispose(); // Fecha a janela após a escolha
        }
    }

    public int getPercepcaoEscolhida() {
        return percepcaoEscolhida;
    }

    public static int exibirMenu() {
        MenuDificuldade menu = new MenuDificuldade();
        menu.setVisible(true);

        while (menu.getPercepcaoEscolhida() == -1) {
            try {
                Thread.sleep(100); // Aguarda a escolha do usuário
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return menu.getPercepcaoEscolhida();
    }
}