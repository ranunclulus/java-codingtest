package com.example.javacodingtest.boj.platinum;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.08
 @link https://www.acmicpc.net/problem/2568
 @timecomplex
 @performance 47280	528
 @category
 @note
 */
public class five2568 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n;
    static Wire[] wires;
    static int[] lis;

    class Wire implements Comparable<Wire>{
        int start;
        int end;

        Wire(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Wire o) {
            return Integer.compare(this.start, o.start);
        }

        @Override
        public String toString() {
            return "Wire{" +
                    "start=" + start +
                    ", end=" + end +
                   '}';
        }
    }

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        wires = new Wire[n];
        lis = new int[n];
        boolean[] visited = new boolean[500001];
        Wire[] trace = new Wire[n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            wires[i] = new Wire(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            visited[wires[i].start] = true;
        }

        Arrays.sort(wires);

        int index = 0;
        lis[index] = wires[0].end;
        trace[0] = new Wire(0, wires[0].start);
        for (int i = 1; i < n; i++) {
            if (lis[index] < wires[i].end) {
                lis[++index] = wires[i].end;
                trace[i] = new Wire(index, wires[i].start);
            } else {
                int lowerIndex = binarySearch(wires[i].end, 0, index);
                lis[lowerIndex - 1] = wires[i].end;

                trace[i] = new Wire(lowerIndex - 1, wires[i].start);
            }
        }
        builder.append(n - index - 1).append("\n");

        List<Integer> list = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            if (trace[i].start == index) {
                list.add(trace[i].end);
                index--;
            }
        }

        for(int start : list) {
            visited[start] = false;
        }

        for (int i = 0; i <= 500000; i++) {
            if (visited[i]) builder.append(i).append("\n");
        }

        writer.write(builder.toString());
        writer.flush();
    }

    private int binarySearch(int value, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] >= value) right = mid;
            else left = mid + 1;
        }
        return right + 1;
    }


    public static void main(String[] args) throws IOException {
        new five2568().solution();
    }
}

