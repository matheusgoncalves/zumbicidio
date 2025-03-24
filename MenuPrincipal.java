import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private boolean iniciarJogo = false;
    private boolean debugMode = false;

    public MenuPrincipal() {
        setTitle("Menu Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton btnJogar = new JButton("Jogar");
        JButton btnDebug = new JButton("DEBUG");
        JButton btnSair = new JButton("Sair");

        btnJogar.addActionListener(e -> {
            iniciarJogo = true;
            dispose(); // Fecha a janela
        });

        btnDebug.addActionListener(e -> {
            debugMode = true; // Ativa o modo debug
            iniciarJogo = true;
            dispose(); // Fecha a janela
        });

        btnSair.addActionListener(e -> System.exit(0));

        add(btnJogar);
        add(btnDebug);
        add(btnSair);
    }

    public boolean isIniciarJogo() {
        return iniciarJogo;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public static MenuPrincipal exibirMenu() {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);

        while (!menu.isIniciarJogo()) {
            try {
                Thread.sleep(100); // Aguarda a escolha do usuário
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return menu;
    }
}