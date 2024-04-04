package com.example.javacodingtest.boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class one1325 {

    public static int N, M;
    public static Computer[] coms;
    public static boolean[] visited;
    public static int[] answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        coms = new Computer[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            coms[i] = new Computer(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            coms[p2].list.add(coms[p1]);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            visited[i] = true;

            DFS(i, i);
        }

        int max = 0;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(max, answer[i]);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < N + 1; i++) {
            if (answer[i] == max) {
                sb.append(i + " ");
            }
        }

        System.out.println(sb.toString());
    }

    public static void DFS(int original, int now) {
        for (Computer c : coms[now].list) {
            if (!visited[c.idx]) {
                visited[c.idx] = true;
                DFS(original, c.idx);
                answer[original]++;
            }
        }
    }

    public static class Computer {
        int idx;
        ArrayList<Computer> list;

        public Computer(int idx) {
            this.idx = idx;
            list = new ArrayList<>();
        }
    }
}
