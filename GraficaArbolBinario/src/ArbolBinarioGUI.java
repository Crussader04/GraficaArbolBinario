//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArbolBinarioGUI extends JFrame {
    private ArbolBinario arbol;
    private JTextArea areaResultado;
    private PanelArbol panelArbol;

    public ArbolBinarioGUI() {
        arbol = new ArbolBinario();
        inicializarArbol();

        setTitle("Visualización de Recorridos de Árbol");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton botonPreOrden = new JButton("PreOrden");
        JButton botonInOrden = new JButton("InOrden");
        JButton botonPostOrden = new JButton("PostOrden");

        botonPreOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.obtenerPreOrden();
                mostrarExplicacion("Recorrido PreOrden", "En el recorrido PreOrden, se visita primero el nodo raíz, luego el subárbol izquierdo y finalmente el subárbol derecho.");
                mostrarRecorrido(recorrido);
                panelArbol.setRecorrido(recorrido);
            }
        });

        botonInOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.obtenerInOrden();
                mostrarExplicacion("Recorrido InOrden", "En el recorrido InOrden, se visita primero el subárbol izquierdo, luego el nodo raíz y finalmente el subárbol derecho.");
                mostrarRecorrido(recorrido);
                panelArbol.setRecorrido(recorrido);
            }
        });

        botonPostOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.obtenerPostOrden();
                mostrarExplicacion("Recorrido PostOrden", "En el recorrido PostOrden, se visita primero el subárbol izquierdo, luego el subárbol derecho y finalmente el nodo raíz.");
                mostrarRecorrido(recorrido);
                panelArbol.setRecorrido(recorrido);
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonPreOrden);
        panelBotones.add(botonInOrden);
        panelBotones.add(botonPostOrden);


        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultado);

        panelArbol = new PanelArbol(arbol);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.SOUTH);
        panel.add(panelArbol, BorderLayout.CENTER);

        add(panel);
    }

    private void inicializarArbol() {
        arbol.raiz = new Nodo(1);
        arbol.raiz.izquierdo = new Nodo(2);
        arbol.raiz.derecho = new Nodo(3);
        arbol.raiz.izquierdo.izquierdo = new Nodo(4);
        arbol.raiz.izquierdo.derecho = new Nodo(5);
        arbol.raiz.derecho.izquierdo = new Nodo(6);
        arbol.raiz.derecho.derecho = new Nodo(7);
    }

    private void mostrarRecorrido(List<Integer> recorrido) {
        StringBuilder sb = new StringBuilder();
        for (int valor : recorrido) {
            sb.append(valor).append(" ");
        }
        areaResultado.setText(sb.toString());
    }

    private void mostrarExplicacion(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ArbolBinarioGUI().setVisible(true);
            }
        });
    }
}

