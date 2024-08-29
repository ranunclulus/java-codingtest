package com.example.javacodingtest.boj.gold;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.29
 @link https://www.acmicpc.net/problem/1922
 @timecomplex
 @performance 52424 KB, 544 MS
 @category
 @note
 */
public class four1922 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int nodeNum;
    static int edgeNum;
    static int[] parents;
    static List<int[]> edges;

    public void solution() throws IOException {
        nodeNum = Integer.parseInt(br.readLine());
        edgeNum = Integer.parseInt(br.readLine());

        parents = new int[nodeNum + 1];
        edges = new LinkedList<>();
        for (int i = 0; i <= nodeNum; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < edgeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new int[] {from, to, cost});
        }

        edges.sort((o1, o2) -> o1[2] - o2[2]);

        long sum = 0L;
        for(int[] edge : edges) {
            if (find(edge[0]) != find(edge[1])) {
                sum += edge[2];
                union(edge[0], edge[1]);
            }
        }
        sb.append(sum);
        bw.write(sb.toString());
        bw.flush();
    }

    public int find(int target) {
        if (parents[target] == target) {
            return target;
        }
        else return parents[target] = find(parents[target]);
    }

    public void union(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);

        if (firstParent > secondParent) {
            parents[firstParent] = secondParent;
        } else parents[secondParent] = firstParent;
    }

    public static void main(String[] args) throws IOException {
        new four1922().solution();
    }
}
