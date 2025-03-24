import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Inventario extends JFrame {
    private JButton btnRevolver, btnTaco, btnAtadura;
    private List<Item> itens;
    private Jogador jogador;

    public Inventario(List<Item> itens, Jogador jogador) {
        this.itens = itens;
        this.jogador = jogador;

        setTitle("Inventário");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        setLocation(1130, 201);
        setResizable(false);

        JPanel painel = new JPanel(new GridLayout(3, 1, 0, 10));

        btnRevolver = new JButton("Revólver");
        btnTaco = new JButton("Taco de Beisebol");
        btnAtadura = new JButton("Usar Atadura");

        // Verifica se o jogador tem o item correspondente antes de habilitar o botão
        btnRevolver.setVisible(possuiItem(Revolver.class));
        btnTaco.setVisible(possuiItem(TacoDeBeisebol.class));
        btnAtadura.setEnabled(possuiItem(Cura.class));

        btnRevolver.setEnabled(false);
        btnTaco.setEnabled(false);


        btnRevolver.addActionListener(new ItemAction("Revólver usado!"));
        btnTaco.addActionListener(new ItemAction("Taco de Beisebol usado!"));
        btnAtadura.addActionListener(e -> usarCura(jogador));

        add(btnRevolver);
        add(btnTaco);
        add(btnAtadura);
    }

    private boolean possuiItem(Class<?> tipo) {
        for (Item item : itens) {
            if (tipo.isInstance(item)) {
                return true;
            }
        }
        return false;
    }

    private void usarCura(Jogador jogador) {
        for (Item item : itens) {
            if (item instanceof Cura) {
                ((Cura) item).usar(jogador);
                JOptionPane.showMessageDialog(null, "Atadura usada! Saúde restaurada.");
                itens.remove(item);
                btnAtadura.setVisible(false);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Você não tem uma atadura!");
    }

    private class ItemAction implements ActionListener {
        private String mensagem;

        public ItemAction(String mensagem) {
            this.mensagem = mensagem;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, mensagem);
        }
    }
}