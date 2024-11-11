package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.11.11
 @link https://www.acmicpc.net/problem/15681
 @timecomplex
 @performance 74240kb 616ms
 @category
 @note
 */
public class five15681 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, r, q;
    static List<Integer>[] adjList;
    static int[] childCount;

    public void solution() throws IOException {
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        r = Integer.parseInt(tokenizer.nextToken());
        q = Integer.parseInt(tokenizer.nextToken());

        adjList = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int one = Integer.parseInt(tokenizer.nextToken());
            int two = Integer.parseInt(tokenizer.nextToken());

            adjList[one].add(two);
            adjList[two].add(one);
        }

        childCount = new int[n + 1];
        Arrays.fill(childCount, 1);
        
        travelsal(r, -1);

        for (int i = 0; i < q; i++) {
            int subRoot = Integer.parseInt(reader.readLine());
            builder.append(childCount[subRoot]).append('\n');
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void travelsal(int index, int parent) {
        for(int next : adjList[index]) {
            if (parent != next) travelsal(next, index);
        }
        if (parent != -1) childCount[parent] += childCount[index];
    }

    public static void main(String[] args) throws IOException {
        new five15681().solution();
    }
}
