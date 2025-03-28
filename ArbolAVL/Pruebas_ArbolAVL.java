/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ArbolAVL;

/**
 *
 * @author anapaolacarmona
 */
public class Pruebas_ArbolAVL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolAVL<Integer> arbol = new ArbolAVL<>();

        for (int i = 0; i < 100; i++) {
            arbol.insertaAVL(i);
        }

        System.out.println("Árbol en orden:");
        System.out.println(arbol.imprimir());

        System.out.println("\nÁrbol por nivel con factores de equilibrio:");
        arbol.imprimirPorNivel();

        System.out.println("\nAltura del árbol: " + arbol.altura());

        System.out.println("\nBorrando 10 elemetos");
        arbol.borraAVL(10);
        arbol.borraAVL(63);
        arbol.borraAVL(50);
        arbol.borraAVL(99);
        arbol.borraAVL(3);
        arbol.borraAVL(20);
        arbol.borraAVL(80);
        arbol.borraAVL(70);
        arbol.borraAVL(88);
        arbol.borraAVL(43);
        arbol.imprimirPorNivel();

        System.out.println("\nAltura del árbol después de borrar: " + arbol.altura());
    }
}
    

