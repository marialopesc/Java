/*
Student: Maria Bezerra
UIN: 676493398
Assignment: Extra Credit #1 - Generic Practice
Fall 2023
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;

public class GenericPractice<T extends Number & Comparable<T>> {
    ArrayList<T> numList;
    T initial;

    // Initialize with initial value
    GenericPractice(T init) {
        initial = init;
        numList = new ArrayList<>();
        numList.add(initial);
    }

    // Add value to list
    void storeValue(T value) {
        numList.add(value);
    }

    // Update value at specified index
    void changeValue(T value, int idx) {
        numList.set(idx, value);
    }

    // Display list contents; indicate if empty
    void printArray() {
        if (numList.isEmpty()) {
            System.out.println("empty list");
            return;
        }
        for (Iterator<T> it = numList.iterator(); it.hasNext(); ) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

    // Sort and display list contents
    void sortPrintArray() {
        Collections.sort(numList);
        for (T elem : numList) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    // Sort in reverse and display list
    void sortReversePrintArray() {
        Collections.sort(numList, Collections.reverseOrder());
        for (T elem : numList) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    // Clear list contents
    void clearList() {
        numList.clear();
    }
}
