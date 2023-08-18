package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11279
public class two11279 {
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
            if (heap[index] <= heap[parentIndex]) break;
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
            int maxIndex = index;

            // leftChild > root
            if (leftChild < size && heap[leftChild] > heap[maxIndex])
                maxIndex = leftChild;

            // rightChild > root
            if (rightChild < size && heap[rightChild] > heap[maxIndex])
                maxIndex = rightChild;

            if (maxIndex == index) break;

            int temp = heap[index];
            heap[index] = heap[maxIndex];
            heap[maxIndex] = temp;
            index = maxIndex;
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
        new two11279().solution();
    }
}
