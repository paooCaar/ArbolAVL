# 🌳 ArbolAVL

Este repositorio contiene una implementación genérica de un **árbol AVL** en Java. El árbol AVL es un tipo de árbol binario de búsqueda auto-balanceado, lo que significa que mantiene su equilibrio automáticamente tras inserciones o eliminaciones de nodos para garantizar un rendimiento óptimo.

## 📝 Características

- Implementación genérica: permite trabajar con cualquier tipo de dato que implemente `Comparable<T>`.
- Inserción con rebalanceo automático.
- Eliminación con rebalanceo automático.
- Impresión en inorden y por niveles.
- Métodos auxiliares como búsqueda, altura del árbol y rotaciones.

## 📂 Archivos

- `ArbolAVL.java`: Clase principal que implementa el árbol AVL.
- `NodoAVL.java`: Clase que representa un nodo dentro del árbol.
- `Pruebas_ArbolAVL.java`: Archivo de pruebas para validar el funcionamiento del árbol AVL (opcionalmente puedes incluir algunos ejemplos de uso aquí).

## 💻 Uso básico

```java
ArbolAVL<Integer> arbol = new ArbolAVL<>();

arbol.insertaAVL(10);
arbol.insertaAVL(5);
arbol.insertaAVL(15);
arbol.insertaAVL(2);

System.out.println(arbol.imprimir()); // Imprime en inorden

arbol.borraAVL(5); // Elimina el nodo con valor 5

arbol.imprimirPorNivel(); // Imprime el árbol por niveles mostrando el factor de equilibrio
```

## 🧠 Estructura del código

- `insertaAVL`: Inserta un nuevo elemento y mantiene el árbol balanceado.
- `borraAVL`: Elimina un nodo y reequilibra el árbol.
- `balancear`: Se encarga de aplicar rotaciones según el factor de equilibrio.
- `rotacionIzquierda` / `rotacionDerecha`: Métodos de rotación para mantener el equilibrio.
- `imprimir`, `imprimirPorNivel`: Métodos para recorrer e imprimir el árbol.
- `altura`, `obtenerSucesor`: Métodos auxiliares.

## 📜 Requisitos

- JDK 8 o superior.
- Un entorno de desarrollo como NetBeans, IntelliJ, Eclipse o VSCode.

## 🚀 Objetivo

Este proyecto fue creado con fines educativos para ilustrar el funcionamiento de los árboles AVL y cómo implementarlos desde cero en Java.

---

