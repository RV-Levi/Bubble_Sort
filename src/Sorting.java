
/*

Algoritmos y complejidad IST 4310 - 01
NRC: 3264
@author Elkin Luis Arteaga Sánchez
Código: 200153212
Actividad: Exam Prep: Analysis of a Sorting Algorithm
Fecha: 15/11/2022
Descripción: Algoritmo que ordena un array, intercambiando de tal modo que resulte ascendente.

 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 *
 */
/**
 *
 * @author elarteaga
 */
public class Sorting {

    public static void main(String args[]) {
        int[] array = {6, 2, 4, 7, 1, 6, 8, 5};
        int len = array.length;
        bubbleSort(array, len);

        /*
        for (int i = 0; i < len; ++i) {
            System.out.print(array[i] + " ");
        }
         */
    }

    public static void mostrar(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
            if (i == array.length - 1) {
                System.out.println("");
            }
        }
    }

    public static void bubbleSort(int[] sort_arr, int len) {
        double inicio = System.nanoTime();
        for (int i = 0; i < len - 1; ++i) {
            for (int j = 0; j < len - i - 1; ++j) {
                if (sort_arr[j + 1] < sort_arr[j]) {
                    int swap = sort_arr[j];
                    sort_arr[j] = sort_arr[j + 1];
                    sort_arr[j + 1] = swap;
                }
                mostrar(sort_arr);
            }
        }
        double fin = System.nanoTime();
        double tiempo = (fin - inicio) / 1000000;
    }

    public static double read(String filename) {
        File f = new File(filename);
        double tiempo = 0;

        try {
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(" ");
                tiempo += Double.parseDouble(data[1]);
            }
            sc.close();
        } catch (FileNotFoundException | NumberFormatException err) {
        }

        return tiempo;
    }

    public static void create(int n, double tiempo, String filename) {
        try {
            File f = new File(filename);
            // Si no existe el archivo txt, lo creamos
            if (!f.exists()) {
                f.createNewFile();
            }

            double tiempo_acumulado = read(filename);
            FileWriter w = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            // Escribimos en el archivo
            wr.write(n + " ");
            wr.append((tiempo + tiempo_acumulado) + "\n");
            wr.close();
            bw.close();
        } catch (IOException err) {
        }
    }

}
