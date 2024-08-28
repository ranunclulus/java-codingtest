package com.example.javacodingtest.swea;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.29
 @link
 @timecomplex
 @performance 136424 KB, 2572 MS
 @category
 @note
 */
public class four3124 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int nodeNum;
    static int edgeNum;
    static List<int[]> edges;
    static int[] parents;
    static long answer;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            st = new StringTokenizer(br.readLine());
            nodeNum = Integer.parseInt(st.nextToken());
            edgeNum = Integer.parseInt(st.nextToken());

            edges = new LinkedList<>();
            parents = new int[nodeNum];
            for (int i = 0; i < nodeNum; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < edgeNum; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new int[] {from, to, cost});
            }

            edges.sort((o1, o2) -> o1[2] - o2[2]);

            answer = 0;
            for(int[] edge : edges) {
                if (find(edge[0]) != find(edge[1])) {
                    union(edge[0], edge[1]);
                    answer += edge[2];
                }
            }
            sb.append("#").append(test).append(" ").append(answer).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private void union(int first, int second) {
        int firstParent = find(first);
        int secondParent = find(second);

        if (firstParent > secondParent) {
            parents[firstParent] = secondParent;
        } else {
            parents[secondParent] = firstParent;
        }
    }

    private int find(int target) {
        if (parents[target] == target) {
            return target;
        }
        else return find(parents[target]);
    }

    public static void main(String[] args) throws IOException {
        new four3124().solution();
    }
}


/*
3
3 3
1 2 1
2 3 2
1 3 3
6 9
1 6 5
2 4 6
1 2 7
3 5 15
5 6 9
3 4 10
1 3 11
2 3 3
4 5 7
0 0
 */
