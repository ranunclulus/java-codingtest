package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class one11286 {
    private int[] heap;
    private int size;

    public void insert(int item) {
        heap[size] = item;
        reHeapUp(size);
        size++;
    }

    public void reHeapUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (Math.abs(heap[index]) > Math.abs(heap[parentIndex])) break;
            if (Math.abs(heap[index]) == Math.abs(heap[parentIndex]) && heap[index] > heap[parentIndex]) break;
            int temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            index = parentIndex;
        }
    }

    public int remove() {
        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        reHeapDown(0);
        return root;
    }

    public void reHeapDown(int index) {
        while (index < size) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int minIndex = index;

            // leftChild > root
            if (leftChild < size &&
                    (Math.abs(heap[leftChild]) < Math.abs(heap[minIndex]) ||
                            (Math.abs(heap[leftChild]) == Math.abs(heap[minIndex]) && heap[leftChild] < heap[minIndex])))
                minIndex = leftChild;

            // rightChild > root
            if (rightChild < size &&
                    (Math.abs(heap[rightChild]) < Math.abs(heap[minIndex]) ||
                            (Math.abs(heap[rightChild]) == Math.abs(heap[minIndex]) && heap[rightChild] < heap[minIndex])))
                minIndex = rightChild;

            if (minIndex == index) break;

            int temp = heap[index];
            heap[index] = heap[minIndex];
            heap[minIndex] = temp;
            index = minIndex;
        }
    }

    public void solution() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] cal = new int[n];
        int zeroCnt = 0;

        for (int i = 0; i < n; i++) {
            cal[i] = Integer.parseInt(reader.readLine());
            if (cal[i] == 0) zeroCnt++;
        }

        heap = new int[n - zeroCnt];
        size = 0;

        for (int calculation:cal) {
            if (calculation == 0) {
                if (size == 0) System.out.println(0);
                else System.out.println(remove());
            }
            if (calculation != 0) {
                insert(calculation);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new one11286().solution();
    }
}
