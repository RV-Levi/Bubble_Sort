
/*

Algorithms and complexity IST 4310 - 01
NRC: 3264
@author Elkin Luis Arteaga Sánchez
Code: 200153212
Activity: Analysis of a sorting algorithm
Date: 17/11/2022
Description: Algorithm that sorts an ls, swapping in an ascending order.

https://github.com/RV-Levi/Bubble_Sort

 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/*
 *
 */
/**
 *
 * @author elarteaga
 */
public class Bubble_Sort {

    public static void main(String args[]) {

		//Test data
		int n = 10;
		int limite_n = 50;
        int limite_experimentos = 50;
		int minValue = -100;
        int maxValue = 100 + 1; // It has a "+ 1" because it is an exclusive limit in the ints() method.
		
        //int[] ls = {6, 2, 4, 7, 1, 6, 8, 5}; //Case given in the statement
		//int[] ls = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; //Best case
		//int[] ls = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Worst case
		//int[] ls = random(n, minValue, maxValue);
		//bubbleSort(n, ls);
		
		
		//Experiments
        for (int i = 0; i < limite_n; i++) {
			double avg = 0;
			int[] ls = random(n, minValue, maxValue);
            for(int j = 0; j < limite_experimentos; j++) {
                avg = bubbleSort(n, ls);
            }
			//Average execution time
            avg /= limite_experimentos;
			//Creation and verification of .txt files
            create(n, avg, "dataBubbleSort.txt");
            n += 10;
        }
		
    }

	//Organizes the calls to the pseudo-random integer generator.
    //Input: n (int, array size ls), minValue (int, minimum value of each number in the array), maxValue (int, maximum value of each number in the array)
    //Output: ls (Array int, an array with integers)
    public static int[] random(int n, int minValue, int maxValue) {
        int[] ls = new int[n];
        for (int i = 0; i < n; i++) {
            ls[i] = intRandom(minValue, maxValue);
        }
        return ls;
    }

	//Generate random integers using java.util.Random
	//Input: minValue (int, minimum value of each number of the array), maxValue (int, maximum value of each number of the array)
    //Output: int value
    public static int intRandom(int minValue, int maxValue) {
        return (new Random()).ints(minValue, maxValue).findFirst().getAsInt();
    }

	//Print iterations of bubbleSort method
	//Input: ls (Array int, an array with integers)
	//Output: N/A
    public static void mostrar(int[] ls) {
        for (int i = 0; i < ls.length; ++i) {
            System.out.print(ls[i] + " ");
            if (i == ls.length - 1) {
                System.out.println("");
            }
        }
    }

	//Method that implements sorting
	//Input: n (int, array size ls), ls (Array int, an array with integers)
	//Output: time (double, method execution time)
    public static double bubbleSort(int n, int[] ls) {
        double start = System.nanoTime();
		int pairs = n;
		boolean swapped = true;
		while (swapped == true) {
			pairs -= 1;
			swapped = false;
			for (int i = 0; i < pairs; i++) {
				/*
				if (i == 0 && pairs == n - 1) {
					mostrar(ls);
				}*/
				if (ls[i] > ls[i + 1]) {
					swap(ls[i], ls[i + 1], ls, i);
					swapped = true;
				}
				//mostrar(ls);
			}
		}
        double end = System.nanoTime();
        double time = (end - start) / 1000000;
		return time;
    }

	//Number exchange, needed for bubbleSort method
	//Input: num1 (int, number to be exchanged), num2 (int, number to be exchanged), ls (Array int, an array with integers), i (int, index)
	//Output: N/A
	public static void swap(int num1, int num2, int[] ls, int i) {
		int swap = ls[i];
        ls[i] = ls[i + 1];
        ls[i + 1] = swap;
	}

	//Read the .txt file
	//Input: filename (String, file name)
    //Output: time (double, execution time)
    public static double read(String filename) {
        File f = new File(filename);
        double time = 0;
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(" ");
                time += Double.parseDouble(data[1]);
            }
            sc.close();
        } catch (IOException err) {
        }
        return time;
    }

	//Checks if .txt files exist, if not, creates them, also sets the way the information is organized
	//Input: n (int, array size ls), time (double, execution time), filename (String, file name)
    //Output: N/A
    public static void create(int n, double time, String filename) {
        try {
            File f = new File(filename);
            // Si no existe el archivo txt, lo creamos
            if (!f.exists()) {
                f.createNewFile();
            }
            double time_acumulado = read(filename);
            FileWriter w = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(w);
            PrintWriter wr = new PrintWriter(bw);
            // Escribimos en el archivo
            wr.write(n + " ");
            wr.append((time + time_acumulado) + "\n");
            wr.close();
            bw.close();
        } catch (IOException err) {
        }
    }

}
