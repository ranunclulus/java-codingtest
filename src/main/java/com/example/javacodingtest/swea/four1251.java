package com.example.javacodingtest.swea;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.08.30
 @link
 @timecomplex
 @performance 89064 KB, 822 MS
 @category #Prim
 @note
 */
public class four1251 {
    class Island {
        int row;
        int col;

    }
    class Edge implements Comparable<Edge> {
        int nextNode;
        long cost;

        public Edge(int nextNode, long cost) {
            this.nextNode = nextNode;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int nodeNum;
    static Island[] islands;
    static boolean[] visited;
    static PriorityQueue<Edge> priorityQueue;
    static long cost;
    static double e;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            nodeNum = Integer.parseInt(br.readLine());
            islands = new Island[nodeNum];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < nodeNum; i++) {
                islands[i] = new Island();
                islands[i].row = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < nodeNum; i++) {
                islands[i].col = Integer.parseInt(st.nextToken());
            }
            e = Double.parseDouble(br.readLine());

            cost = 0;
            visited = new boolean[nodeNum];
            priorityQueue = new PriorityQueue<>();
            priorityQueue.add(new Edge(0, 0));
            while(!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll();
                if (visited[edge.nextNode]) continue;

                visited[edge.nextNode] = true;
                cost += edge.cost;

                for (int i = 0; i < nodeNum; i++) {
                    if (visited[i]) continue;
                    Island now = islands[edge.nextNode];
                    Island next = islands[i];
                    long nextCost = (long) (now.col - next.col) * (now.col - next.col)
                                    + (long) (now.row - next.row) * (now.row - next.row);
                    priorityQueue.add(new Edge(i, nextCost));
                }
            }


            sb.append("#").append(test).append(" ").append(Math.round(cost * e)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        new four1251().solution();
    }
}


