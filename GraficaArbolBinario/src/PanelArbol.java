import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelArbol extends JPanel {
    private ArbolBinario arbol;
    private List<Integer> recorrido;

    public PanelArbol(ArbolBinario arbol) {
        this.arbol = arbol;
        setBackground(Color.WHITE);
    }

    public void setRecorrido(List<Integer> recorrido) {
        this.recorrido = recorrido;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arbol.raiz != null) {
            dibujarArbol(g, arbol.raiz, getWidth() / 2, 50, getWidth() / 4, 50);
        }
    }

    private void dibujarArbol(Graphics g, Nodo nodo, int x, int y, int xOffset, int yOffset) {
        if (nodo == null) return;

        boolean highlighted = recorrido != null && recorrido.contains(nodo.valor);
        if (highlighted) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.BLACK);
        }
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(nodo.valor), x - 5, y + 5);

        if (nodo.izquierdo != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - xOffset, y + yOffset);
            dibujarArbol(g, nodo.izquierdo, x - xOffset, y + yOffset, xOffset / 2, yOffset);
        }

        if (nodo.derecho != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x + xOffset, y + yOffset);
            dibujarArbol(g, nodo.derecho, x + xOffset, y + yOffset, xOffset / 2, yOffset);
        }
    }
}
