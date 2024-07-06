import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }
        if (valor < nodo.valor) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = insertarRecursivo(nodo.derecho, valor);
        }
        return nodo;
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return null;
        }
        if (valor < nodo.valor) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, valor);
        } else if (valor > nodo.valor) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, valor);
        } else {
            if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            nodo.valor = valorMinimo(nodo.derecho);
            nodo.derecho = eliminarRecursivo(nodo.derecho, nodo.valor);
        }
        return nodo;
    }

    private int valorMinimo(Nodo nodo) {
        int minimo = nodo.valor;
        while (nodo.izquierdo != null) {
            minimo = nodo.izquierdo.valor;
            nodo = nodo.izquierdo;
        }
        return minimo;
    }

    public List<Integer> obtenerPreOrden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoPreOrden(raiz, resultado);
        return resultado;
    }

    private void recorridoPreOrden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) return;
        resultado.add(nodo.valor);
        recorridoPreOrden(nodo.izquierdo, resultado);
        recorridoPreOrden(nodo.derecho, resultado);
    }

    public List<Integer> obtenerInOrden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoInOrden(raiz, resultado);
        return resultado;
    }

    private void recorridoInOrden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) return;
        recorridoInOrden(nodo.izquierdo, resultado);
        resultado.add(nodo.valor);
        recorridoInOrden(nodo.derecho, resultado);
    }

    public List<Integer> obtenerPostOrden() {
        List<Integer> resultado = new ArrayList<>();
        recorridoPostOrden(raiz, resultado);
        return resultado;
    }

    private void recorridoPostOrden(Nodo nodo, List<Integer> resultado) {
        if (nodo == null) return;
        recorridoPostOrden(nodo.izquierdo, resultado);
        recorridoPostOrden(nodo.derecho, resultado);
        resultado.add(nodo.valor);
    }
}
