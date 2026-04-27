import javax.swing.*;
import java.awt.*;

public class TreePanel extends JPanel {
    private BinarySearchTree tree;

    public TreePanel(BinarySearchTree tree) {
        this.tree = tree;
        setBackground(Color.WHITE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tree.getRoot() != null) {
            desenharNo(g, tree.getRoot(), getWidth() / 2, 50, getWidth() / 4);
        }
    }

    private void desenharNo(Graphics g, Node node, int x, int y, int distancia) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            int filhoX = x - distancia;
            int filhoY = y + 80;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, filhoX, filhoY);
            desenharNo(g, node.getLeft(), filhoX, filhoY, distancia / 2);
        }

        if (node.getRight() != null) {
            int filhoX = x + distancia;
            int filhoY = y + 80;
            g.setColor(Color.BLACK);
            g.drawLine(x, y, filhoX, filhoY);
            desenharNo(g, node.getRight(), filhoX, filhoY, distancia / 2);
        }

        g.setColor(new Color(70, 150, 220));
        g.fillOval(x - 30, y - 30, 60, 60);

        g.setColor(Color.BLACK);
        g.drawOval(x - 30, y - 30, 60, 60);

        String texto = node.getPlayer().getNickname();
        FontMetrics fm = g.getFontMetrics();
        int textoX = x - fm.stringWidth(texto) / 2;
        int textoY = y + 5;

        g.drawString(texto, textoX, textoY);
    }
}