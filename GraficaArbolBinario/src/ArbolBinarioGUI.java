import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ArbolBinarioGUI extends JFrame {
    private ArbolBinario arbol;
    private JTextArea areaResultado;
    private PanelArbol panelArbol;
    private Timer timer;

    public ArbolBinarioGUI() {
        arbol = new ArbolBinario();

        setTitle("Visualización de Recorridos de Árbol");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton botonPreOrden = new JButton("PreOrden");
        JButton botonInOrden = new JButton("InOrden");
        JButton botonPostOrden = new JButton("PostOrden");
        JButton botonAgregarNodo = new JButton("Agregar Nodo");
        JButton botonEliminarNodo = new JButton("Eliminar Nodo");

        botonPreOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.obtenerPreOrden();
                mostrarExplicacion("Recorrido PreOrden", "En el recorrido PreOrden, se visita primero el nodo raíz, luego el subárbol izquierdo y finalmente el subárbol derecho.");
                mostrarRecorrido(recorrido);
                iniciarAnimacionRecorrido(recorrido);
            }
        });

        botonInOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.obtenerInOrden();
                mostrarExplicacion("Recorrido InOrden", "En el recorrido InOrden, se visita primero el subárbol izquierdo, luego el nodo raíz y finalmente el subárbol derecho.");
                mostrarRecorrido(recorrido);
                iniciarAnimacionRecorrido(recorrido);
            }
        });

        botonPostOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> recorrido = arbol.obtenerPostOrden();
                mostrarExplicacion("Recorrido PostOrden", "En el recorrido PostOrden, se visita primero el subárbol izquierdo, luego el subárbol derecho y finalmente el nodo raíz.");
                mostrarRecorrido(recorrido);
                iniciarAnimacionRecorrido(recorrido);
            }
        });

        botonAgregarNodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorString = JOptionPane.showInputDialog(null, "Ingrese el valor del nuevo nodo:");
                try {
                    int valor = Integer.parseInt(valorString);
                    arbol.insertar(valor);
                    panelArbol.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor válido.");
                }
            }
        });

        botonEliminarNodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorString = JOptionPane.showInputDialog(null, "Ingrese el valor del nodo a eliminar:");
                try {
                    int valor = Integer.parseInt(valorString);
                    arbol.eliminar(valor);
                    panelArbol.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un valor válido.");
                }
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonPreOrden);
        panelBotones.add(botonInOrden);
        panelBotones.add(botonPostOrden);
        panelBotones.add(botonAgregarNodo);
        panelBotones.add(botonEliminarNodo);

        areaResultado = new JTextArea(5, 30);
        areaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultado);

        panelArbol = new PanelArbol(arbol);

        panel.add(panelArbol, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.EAST);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);
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

    private void iniciarAnimacionRecorrido(List<Integer> recorrido) {
        panelArbol.setRecorrido(recorrido);
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelArbol.avanzarRecorrido();
                if (panelArbol.indiceRecorrido >= recorrido.size()) {
                    timer.stop();
                }
            }
        });
        timer.start();
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

