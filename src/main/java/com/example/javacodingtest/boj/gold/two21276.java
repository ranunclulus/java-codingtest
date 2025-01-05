package com.example.javacodingtest.boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.01.05
 @link https://www.acmicpc.net/problem/21276
 @timecomplex
 @performance 136828kb 680ms
 @category
 @note
 */
public class two21276 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int n, m;
    static String[] people;
    static Map<String, Integer> peopleNum;
    static List<List<Integer>> adjList;
    static int[] degree;

    public void solution() throws IOException {
        n = Integer.parseInt(reader.readLine());
        people = new String[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            people[i] = tokenizer.nextToken();
        }
        Arrays.sort(people);

        peopleNum = new HashMap<>();
        adjList = new ArrayList<>();
        degree = new int[n];
        for (int i = 0; i < n; i++) {
            peopleNum.put(people[i], i);
            adjList.add(new ArrayList<>());
        }

        m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int end = peopleNum.get(tokenizer.nextToken());
            int start = peopleNum.get(tokenizer.nextToken());
            adjList.get(start).add(end);
            degree[end]++;
        }

        List<String> root = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) root.add(people[i]);
        }
        builder.append(root.size()).append('\n');
        for(String name : root) {
            builder.append(name).append(' ');
        }
        builder.append('\n');

        List<List<String>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        Deque<String> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) deque.add(people[i]);
        }

        while (!deque.isEmpty()) {
            String now = deque.poll();
            for (int next : adjList.get(peopleNum.get(now))) {
                if (degree[next] == 1) {
                    children.get(peopleNum.get(now)).add(people[next]);
                }
                degree[next]--;
                if (degree[next] == 0) {
                    deque.add(people[next]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            builder.append(people[i]).append(' ').append(children.get(i).size()).append(' ');
            for (int j = children.get(i).size() - 1; j >= 0; j--) {
                builder.append(children.get(i).get(j)).append(' ');
            }
            builder.append('\n');
        }

        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new two21276().solution();
    }
}
