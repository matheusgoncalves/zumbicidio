import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

public class Inventario extends JFrame {
    public JButton btnRevolver, btnTaco, btnAtadura;
    private List<Item> itens;
    private Jogador jogador;

    public Inventario(Jogador jogador) {
        this.jogador = jogador;
        this.itens = jogador.getInventario();

        configurarJanela();
        inicializarComponentes();
        atualizarBotoes(); // Atualiza inicialmente
    }

    private void configurarJanela() {
        setTitle("Inventário");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
    }

    private void inicializarComponentes() {
        btnRevolver = new JButton("Revólver");
        btnTaco = new JButton("Taco de Beisebol");
        btnAtadura = new JButton("Usar Atadura");

        btnAtadura.addActionListener(e -> usarCura(jogador));

        add(btnRevolver);
        add(btnTaco);
        add(btnAtadura);
    }

    // Método público para acesso externo
    public void atualizarBotoes() {
        boolean temRevolver = possuiItem(Revolver.class);
        boolean temTaco = possuiItem(TacoDeBeisebol.class);
        boolean temCura = possuiItem(Cura.class);

        btnRevolver.setVisible(temRevolver);
        btnRevolver.setEnabled(temRevolver);

        btnTaco.setVisible(temTaco);
        btnTaco.setEnabled(temTaco);

        btnAtadura.setVisible(temCura);
        btnAtadura.setEnabled(temCura);

        revalidate();
        repaint();
    }

    private boolean possuiItem(Class<? extends Item> tipo) {
        for (Item item : jogador.getInventario()) {
            if (item != null && item.getClass().equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    private void usarCura(Jogador jogador) {
        for (Item item : itens) {
            if (item instanceof Cura) {
                ((Cura) item).usar(jogador);
                itens.remove(item); // Remove a atadura usada
                atualizarBotoes(); // Atualiza a interface
                JOptionPane.showMessageDialog(this, "Atadura usada!");
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Sem ataduras!");
    }
}