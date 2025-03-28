/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolAVL;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Paola Carmona
 */
// Clase genérica ArbolAVL que implementa un árbol AVL para mantener el equilibrio.
public class ArbolAVL<T extends Comparable<T>> {
    private NodoAVL<T> raiz; // Nodo raíz del árbol.
    private int cont;        // Contador de nodos del árbol.

    // Método para imprimir el árbol en recorrido inorden.
    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        imprimir(raiz, sb);
        return sb.toString();
    }

    // Método recursivo para recorrer el árbol en inorden.
    private void imprimir(NodoAVL<T> actual, StringBuilder sb) {
        if (actual == null) {
            return;
        }
        imprimir(actual.izq, sb);                      // Recorre el subárbol izquierdo.
        sb.append(actual.getElem()).append(" ");       // Agrega el elemento actual.
        imprimir(actual.der, sb);                      // Recorre el subárbol derecho.
    }

    // Método para buscar un elemento en el árbol.
    public NodoAVL<T> busca(T elem, NodoAVL<T> actual) {
        if (actual == null || actual.getElem().equals(elem)) {
            return actual; // Devuelve el nodo si se encuentra o si es nulo.
        }
        if (elem.compareTo(actual.getElem()) < 0) {
            return busca(elem, actual.izq);  // Busca en el subárbol izquierdo.
        } else {
            return busca(elem, actual.der);  // Busca en el subárbol derecho.
        }
    }

    // Método público para insertar un elemento en el árbol.
    public void insertaAVL(T elem) {
        raiz = insertaAVL(elem, raiz);
    }

    // Método recursivo para insertar un elemento y balancear el árbol.
    private NodoAVL<T> insertaAVL(T elem, NodoAVL<T> actual) {
        if (actual == null) {
            cont++; // Incrementa el contador al insertar un nuevo nodo.
            return new NodoAVL<>(elem);
        }

        if (elem.compareTo(actual.getElem()) < 0) {
            actual.izq = insertaAVL(elem, actual.izq);   // Inserta en el subárbol izquierdo.
            actual.izq.setPadre(actual);
        } else if (elem.compareTo(actual.getElem()) > 0) {
            actual.der = insertaAVL(elem, actual.der);   // Inserta en el subárbol derecho.
            actual.der.setPadre(actual);
        } else {
            return actual; // No permite duplicados.
        }

        actual.actualizarAltura();      // Actualiza la altura del nodo.
        return balancear(actual);       // Balancea el nodo tras la inserción.
    }

    // Método para balancear el nodo actual.
    private NodoAVL<T> balancear(NodoAVL<T> actual) {
        int fe = actual.getFactorEquilibrio(); // Obtiene el factor de equilibrio.

        // Rotación simple a la derecha (caso izquierda-izquierda).
        if (fe > 1 && actual.izq.getFactorEquilibrio() >= 0) {
            return rotacionDerecha(actual);
        }

        // Rotación doble izquierda-derecha.
        if (fe > 1 && actual.izq.getFactorEquilibrio() < 0) {
            actual.izq = rotacionIzquierda(actual.izq);
            return rotacionDerecha(actual);
        }

        // Rotación simple a la izquierda (caso derecha-derecha).
        if (fe < -1 && actual.der.getFactorEquilibrio() <= 0) {
            return rotacionIzquierda(actual);
        }

        // Rotación doble derecha-izquierda.
        if (fe < -1 && actual.der.getFactorEquilibrio() > 0) {
            actual.der = rotacionDerecha(actual.der);
            return rotacionIzquierda(actual);
        }

        return actual; // Devuelve el nodo balanceado.
    }

    // Rotación simple a la derecha.
    private NodoAVL<T> rotacionDerecha(NodoAVL<T> nodo) {
        NodoAVL<T> aux = nodo.izq;
        NodoAVL<T> T2 = aux.der;

        aux.der = nodo;
        nodo.izq = T2;

        nodo.actualizarAltura(); // Actualiza la altura de los nodos rotados.
        aux.actualizarAltura();

        return aux; // Nuevo nodo raíz tras la rotación.
    }

    // Rotación simple a la izquierda.
    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> nodo) {
        NodoAVL<T> aux = nodo.der;
        NodoAVL<T> T2 = aux.izq;

        aux.izq = nodo;
        nodo.der = T2;

        nodo.actualizarAltura(); // Actualiza la altura de los nodos rotados.
        aux.actualizarAltura();

        return aux; // Nuevo nodo raíz tras la rotación.
    }

    // Método para eliminar un elemento del árbol.
    public void borraAVL(T elem) {
        raiz = borraAVL(elem, raiz);
    }

    // Método recursivo para eliminar un elemento y rebalancear el árbol.
    private NodoAVL<T> borraAVL(T elem, NodoAVL<T> actual) {
        if (actual == null) {
            return actual; // Retorna si el nodo no se encuentra.
        }

        if (elem.compareTo(actual.getElem()) < 0) {
            actual.izq = borraAVL(elem, actual.izq); // Busca en el subárbol izquierdo.
        } else if (elem.compareTo(actual.getElem()) > 0) {
            actual.der = borraAVL(elem, actual.der); // Busca en el subárbol derecho.
        } else {
            // Caso 1: Nodo hoja o con un solo hijo.
            if (actual.izq == null || actual.der == null) {
                NodoAVL<T> temp = (actual.izq != null) ? actual.izq : actual.der;
                if (temp == null) {
                    temp = actual;
                    actual = null;
                } else {
                    actual = temp;
                }
                cont--; // Decrementa el contador al eliminar.
            } else {
                // Caso 2: Nodo con dos hijos, obtiene el sucesor.
                NodoAVL<T> sucesor = obtenerSucesor(actual.der);
                actual.setElem(sucesor.getElem());
                actual.der = borraAVL(sucesor.getElem(), actual.der);
            }
        }

        if (actual == null) {
            return null;
        }

        actual.actualizarAltura(); // Actualiza la altura tras la eliminación.
        return balancear(actual);  // Rebalancea el nodo.
    }

    // Obtiene el sucesor del nodo actual (el menor del subárbol derecho).
    private NodoAVL<T> obtenerSucesor(NodoAVL<T> actual) {
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }

    // Imprime el árbol por niveles (BFS).
    public void imprimirPorNivel() {
        if (raiz == null) {
            System.out.println("Árbol vacío");
            return;
        }

        Queue<NodoAVL<T>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            NodoAVL<T> actual = cola.poll();
            int fe = actual.getFactorEquilibrio();
            System.out.println(actual.getElem() + " (FE: " + fe + ")");

            if (actual.izq != null) {
                cola.add(actual.izq);
            }
            if (actual.der != null) {
                cola.add(actual.der);
            }
        }
    }

    // Devuelve la altura del árbol.
    public int altura() {
        return altura(raiz);
    }

    // Método para calcular la altura de un nodo.
    private int altura(NodoAVL<T> actual) {
        if (actual == null)
            return 0;
        else
            return actual.altura;
    }
}
