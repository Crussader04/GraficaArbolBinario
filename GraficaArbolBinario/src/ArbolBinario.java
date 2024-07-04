import java.util.ArrayList;
import java.util.List;

public class ArbolBinario {
    Nodo raiz;

    public ArbolBinario() {
        raiz = null;
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
