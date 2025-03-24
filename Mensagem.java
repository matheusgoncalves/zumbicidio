import javax.swing.*;
import java.awt.*;

public class Mensagem {
    public static void exibirMensagem(String mensagem) {
        // Cria o JOptionPane com a mensagem
        JOptionPane pane = new JOptionPane(mensagem, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog("Zumbicídio");

        // Estiliza o JOptionPane
        pane.setBackground(new Color(50, 50, 50)); // Fundo cinza-escuro
        pane.setForeground(Color.WHITE); // Texto branco
        pane.setFont(new Font("Arial", Font.PLAIN, 14)); // Fonte maior e legível

        // Personaliza os componentes internos do JOptionPane
        UIManager.put("Panel.background", new Color(50, 50, 50)); // Fundo do painel
        UIManager.put("OptionPane.background", new Color(50, 50, 50)); // Fundo geral
        UIManager.put("OptionPane.messageForeground", Color.WHITE); // Texto da mensagem
        UIManager.put("Button.background", new Color(161, 135, 0)); // Botão vermelho-escuro
        UIManager.put("Button.foreground", Color.WHITE); // Texto do botão branco
        UIManager.put("Button.font", new Font("Arial", Font.BOLD, 12)); // Fonte do botão

        // Calcula a posição x para centralizar horizontalmente
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.pack(); // Ajusta o tamanho ao conteúdo antes de calcular
        int dialogHeight = dialog.getHeight(); // Mantém a altura ajustada ao conteúdo
        int dialogWidth = 400; // Define a largura fixa (aumentada para 400px)
        int x = (screenSize.width - dialogWidth) / 2; // Centraliza x com a nova largura

        // Define a posição e tamanho do JDialog
        dialog.setSize(dialogWidth, dialogHeight); // Largura fixa, altura ajustada
        dialog.setLocation(x, 150); // Centralizado em x, 150px do topo
        dialog.setBackground(new Color(50, 50, 50)); // Fundo do diálogo
        dialog.setForeground(Color.WHITE); // Texto geral

        dialog.setVisible(true);
    }
}
