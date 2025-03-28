package ArbolAVL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anapaolacarmona
 * @param <T>
 */
public class NodoAVL <T extends Comparable<T>>{
    private T elem;
    NodoAVL<T> izq, der, padre;
    int altura;

    public NodoAVL(T elem) {
        this.elem = elem;
        izq = der = padre = null;
        altura = 1;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoAVL<T> getPadre() {
        return padre;
    }

    public void setPadre(NodoAVL<T> padre) {
        this.padre = padre;
    }

    public int getFactorEquilibrio() {
        int alturaIzq = (izq == null) ? 0 : izq.altura;     //si es null se pone en 0
        int alturaDer = (der == null) ? 0 : der.altura;
        return alturaIzq - alturaDer;
    }

    public void actualizarAltura() {
        int alturaIzq = (izq == null) ? 0 : izq.altura;
        int alturaDer = (der == null) ? 0 : der.altura;
        altura = Math.max(alturaIzq, alturaDer) + 1;
    }

}
