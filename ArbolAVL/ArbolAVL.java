/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArbolAVL;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author anapaolacarmona
 */
public class ArbolAVL <T extends Comparable<T>>{
    private NodoAVL<T> raiz;
    private int cont;

    public String imprimir() {
        StringBuilder sb = new StringBuilder();
        imprimir(raiz, sb);
        return sb.toString();
    }

    private void imprimir(NodoAVL<T> actual, StringBuilder sb) {
        if (actual == null) {
            return;
        }
        imprimir(actual.izq, sb);
        sb.append(actual.getElem()).append(" ");
        imprimir(actual.der, sb);
    }

    public NodoAVL<T> busca(T elem, NodoAVL<T> actual) {
        if (actual == null || actual.getElem().equals(elem)) {
            return actual;
        }
        if (elem.compareTo(actual.getElem()) < 0) {
            return busca(elem, actual.izq);
        } else {
            return busca(elem, actual.der);
        }
    }

    public void insertaAVL(T elem) {
        raiz = insertaAVL(elem, raiz);
    }

    private NodoAVL<T> insertaAVL(T elem, NodoAVL<T> actual) {
        if (actual == null) {
            cont++;
            return new NodoAVL<>(elem);
        }

        if (elem.compareTo(actual.getElem()) < 0) {
            actual.izq = insertaAVL(elem, actual.izq);
            actual.izq.setPadre(actual);
        } else if (elem.compareTo(actual.getElem()) > 0) {
            actual.der = insertaAVL(elem, actual.der);
            actual.der.setPadre(actual);
        } else {
            return actual; // No permite duplicados
        }

        actual.actualizarAltura();
        return balancear(actual);
    }

    private NodoAVL<T> balancear(NodoAVL<T> actual) {
        int fe = actual.getFactorEquilibrio();

        // Rotación simple a la derecha
        if (fe > 1 && actual.izq.getFactorEquilibrio() >= 0) {
            return rotacionDerecha(actual);
        }

        // Rotación doble izquierda-derecha
        if (fe > 1 && actual.izq.getFactorEquilibrio() < 0) {
            actual.izq = rotacionIzquierda(actual.izq);
            return rotacionDerecha(actual);
        }

        // Rotación simple a la izquierda
        if (fe < -1 && actual.der.getFactorEquilibrio() <= 0) {
            return rotacionIzquierda(actual);
        }

        // Rotación doble derecha-izquierda
        if (fe < -1 && actual.der.getFactorEquilibrio() > 0) {
            actual.der = rotacionDerecha(actual.der);
            return rotacionIzquierda(actual);
        }

        return actual;
    }

    private NodoAVL<T> rotacionDerecha(NodoAVL<T> nodo) {
        NodoAVL<T> aux = nodo.izq;
        NodoAVL<T> T2 = aux.der;

        aux.der = nodo;
        nodo.izq = T2;

        nodo.actualizarAltura();
        aux.actualizarAltura();

        return aux;
    }

    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> nodo) {
        NodoAVL<T> aux = nodo.der;
        NodoAVL<T> T2 = aux.izq;

        aux.izq = nodo;
        nodo.der = T2;

        nodo.actualizarAltura();
        aux.actualizarAltura();

        return aux;
    }

    public void borraAVL(T elem) {
        raiz = borraAVL(elem, raiz);
    }

    private NodoAVL<T> borraAVL(T elem, NodoAVL<T> actual) {
        if (actual == null) {
            return actual;
        }

        if (elem.compareTo(actual.getElem()) < 0) {
            actual.izq = borraAVL(elem, actual.izq);
        } else if (elem.compareTo(actual.getElem()) > 0) {
            actual.der = borraAVL(elem, actual.der);
        } else {
            // Nodo hoja o con un solo hijo
            if (actual.izq == null || actual.der == null) {
                NodoAVL<T> temp = (actual.izq != null) ? actual.izq : actual.der;
                if (temp == null) {
                    temp = actual;
                    actual = null;
                } else {
                    actual = temp;
                }
                cont--;
            } else {
                // Nodo con dos hijos (sucesor)
                NodoAVL<T> sucesor = obtenerSucesor(actual.der);
                actual.setElem(sucesor.getElem());
                actual.der = borraAVL(sucesor.getElem(), actual.der);
            }
        }

        if (actual == null) {
            return null;
        }

        actual.actualizarAltura();
        return balancear(actual);
    }

    private NodoAVL<T> obtenerSucesor(NodoAVL<T> actual) {
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }

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

    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoAVL<T> actual) {     //evitar errores de Null point exception 
        if (actual == null)
            return 0;
        else 
            return actual.altura;
    
    }
}