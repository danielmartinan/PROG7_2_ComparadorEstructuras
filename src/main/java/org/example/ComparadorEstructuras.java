package org.example;

import java.util.*;
import java.time.Duration;
import java.time.Instant;

public class ComparadorEstructuras {
    private static final int TAMANO_DATOS = 500000;
    private static final int BUSQUEDAS = 10000;

    public static void main(String[] args) {
        // Preparar datos
        Integer[] elementos = new Integer[TAMANO_DATOS];
        Integer[] elementosDesordenados = new Integer[TAMANO_DATOS];

        Random random = new Random();

        for (int i = 0; i < TAMANO_DATOS; i++) {
            elementos[i] = i;
            elementosDesordenados[i] = random.nextInt(TAMANO_DATOS);
        }

        // Estructuras a comparar
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        // Medir inserción
        System.out.println("PRUEBAS DE INSERCIÓN (" + TAMANO_DATOS + " elementos)");
        System.out.println("-----------------------------------------");

        medirTiempo("ArrayList - Inserción", () -> {
            for (int elemento : elementos) arrayList.add(elemento);
        });

        medirTiempo("LinkedList - Inserción", () -> {
            for (int elemento : elementos) linkedList.add(elemento);
        });

        medirTiempo("HashSet - Inserción", () -> {
            for (int elemento : elementos) hashSet.add(elemento);
        });

        medirTiempo("TreeSet - Inserción", () -> {
            for (int elemento : elementos) treeSet.add(elemento);
        });

        medirTiempo("HashMap - Inserción", () -> {
            for (int elemento : elementos) hashMap.put(elemento, elemento);
        });

        medirTiempo("TreeMap - Inserción", () -> {
            for (int elemento : elementos) treeMap.put(elemento, elemento);
        });

        // Medir búsquedas
        System.out.println("\nPRUEBAS DE BÚSQUEDA (" + BUSQUEDAS + " búsquedas aleatorias)");
        System.out.println("-----------------------------------------");



        medirTiempo("ArrayList - Búsqueda", () -> {
            for (int i = 0; i < BUSQUEDAS; i++) {
                int buscar = random.nextInt(TAMANO_DATOS);
                arrayList.contains(buscar);
            }
        });

        medirTiempo("LinkedList - Búsqueda", () -> {
            for (int i = 0; i < BUSQUEDAS; i++) {
                int buscar = random.nextInt(TAMANO_DATOS);
                linkedList.contains(buscar);
            }
        });

        medirTiempo("HashSet - Búsqueda", () -> {
            for (int i = 0; i < BUSQUEDAS; i++) {
                int buscar = random.nextInt(TAMANO_DATOS);
                hashSet.contains(buscar);
            }
        });

        medirTiempo("TreeSet - Búsqueda", () -> {
            for (int i = 0; i < BUSQUEDAS; i++) {
                int buscar = random.nextInt(TAMANO_DATOS);
                treeSet.contains(buscar);
            }
        });

        medirTiempo("HashMap - Búsqueda", () -> {
            for (int i = 0; i < BUSQUEDAS; i++) {
                int buscar = random.nextInt(TAMANO_DATOS);
                hashMap.containsKey(buscar);
            }
        });

        medirTiempo("TreeMap - Búsqueda", () -> {
            for (int i = 0; i < BUSQUEDAS; i++) {
                int buscar = random.nextInt(TAMANO_DATOS);
                treeMap.containsKey(buscar);
            }
        });

        // Medir inserción al inicio (solo para listas)
        System.out.println("\nPRUEBAS DE INSERCIÓN AL INICIO (1000 elementos)");
        System.out.println("-----------------------------------------");

        medirTiempo("ArrayList - Inserción al inicio", () -> {
            for (int i = 0; i < 1000; i++) {
                arrayList.add(0, i);
            }
        });

        medirTiempo("LinkedList - Inserción al inicio", () -> {
            for (int i = 0; i < 1000; i++) {
                linkedList.add(0, i);
            }
        });

        // Medir ordenación
        System.out.println("\nPRUEBAS DE ORDENACIÓN (500000 elementos)");
        System.out.println("-----------------------------------------");

        ArrayList<Integer> arrayListDesordenado = new ArrayList<>();
        LinkedList<Integer> linkedListDesordenado = new LinkedList<>();

        for (int i = 0; i < TAMANO_DATOS; i++) {
            arrayListDesordenado.add(elementosDesordenados[i]);
            linkedListDesordenado.add(elementosDesordenados[i]);
        }

        medirTiempo("ArrayList - Ordenación (Collections.sort())", () -> {
            Collections.sort(arrayListDesordenado);
        });

        medirTiempo("LinkedList - Ordenación (Collections.sort())", () -> {
            Collections.sort(linkedListDesordenado);
        });

        // Medir ordenación (TreeSet y TreeMap ya están ordenados)
        System.out.println("TreeSet - Estructura ya ordenada por naturaleza");
        System.out.println("TreeMap - Estructura ya ordenada por naturaleza");

        // Medir ordenacion (HashMap y HashSet no tienen orden)
        System.out.println("HashMap - Estructura sin orden");
        System.out.println("HashSet - Estructura sin orden");

        // Medir eliminación
        System.out.println("\nPRUEBAS DE ELIMINACIÓN (500000 elementos)");
        System.out.println("-----------------------------------------");

        medirTiempo("ArrayList - Eliminación (primera mitad, desde el principio)", () -> {
            for (int i = 0; i < TAMANO_DATOS/2; i++) {
                arrayList.removeFirst();
            }
        });

        medirTiempo("ArrayList - Eliminación (segunda mitad desde el final)", () -> {
            for (int i = 0; i < TAMANO_DATOS/2; i++) {
                arrayList.removeLast();
            }
        });

        medirTiempo("LinkedList - Eliminación", () -> {
            for (int i = 0; i < TAMANO_DATOS; i++) {
                linkedList.removeFirst();
            }
        });

        medirTiempo("HashSet - Eliminación", () -> {
            for (int i = 0; i < TAMANO_DATOS; i++) {
                hashSet.remove(i);
            }
        });

        medirTiempo("TreeSet - Eliminación", () -> {
            for (int i = 0; i < TAMANO_DATOS; i++) {
                treeSet.remove(i);
            }
        });

        medirTiempo("HashMap - Eliminación", () -> {
            for (int i = 0; i < TAMANO_DATOS; i++) {
                hashMap.remove(i);
            }
        });

        medirTiempo("TreeMap - Eliminación", () -> {
            for (int i = 0; i < TAMANO_DATOS; i++) {
                treeMap.remove(i);
            }
        });
    }

    private static void medirTiempo(String operacion, Runnable tarea) {
        Instant inicio = Instant.now();
        tarea.run();
        Instant fin = Instant.now();
        Duration duracion = Duration.between(inicio, fin);
        System.out.printf("%-30s: %d ms%n", operacion, duracion.toMillis());
    }
}