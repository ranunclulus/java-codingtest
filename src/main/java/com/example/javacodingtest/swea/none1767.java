package com.example.javacodingtest.swea;

import com.sun.security.jgss.GSSUtil;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.08.30
 @link
 @timecomplex
 @performance 116508 KB, 748 MS
 @category #Prim
 @note
 */
public class none1767 {
    class Result implements Comparable<Result>{
        int count;
        int cost;

        public Result(int count, int cost) {
            this.count = count;
            this.cost = cost;
        }

        @Override
        public int compareTo(Result o) {
            if (this.count == o.count) return Integer.compare(this.cost, o.cost);
            else return -Integer.compare(this.count, o.count);
        }

        @Override
        public String toString() {
            return "Result{" +
                    "count=" + count +
                    ", cost=" + cost +
                    '}';
        }
    }
    class Core {
        int row;
        int col;
        boolean isConnected;
        // 0 연결 X
        // 1 상
        // 2 하
        // 3 좌
        // 4 우
        int direction;
        int distance;

        public Core(int row, int col) {
            this.row = row;
            this.col = col;
            this.isConnected = false;
            this.direction = 0;
            this.distance = 0;
        }

        public void connect(int distance, int direction) {
            this.distance = distance;
            this.direction = direction;
            this.isConnected = true;

            int nextRow = this.row;
            int nextCol = this.col;
            int count = 0;
            while (nextRow != 0 && nextRow != n - 1 && nextCol != 0 && nextCol != n - 1) {
                count++;
                nextRow += deltas[this.direction][0];
                nextCol += deltas[this.direction][1];
                map[nextRow][nextCol] = 1;
                if (count == this.distance) break;
            }

        }

        public void disConnect() {
            int nextRow = this.row;
            int nextCol = this.col;
            int count = 0;
            while (nextRow != 0 && nextRow != n - 1 && nextCol != 0 && nextCol != n - 1) {
                count++;
                nextRow += deltas[this.direction][0];
                nextCol += deltas[this.direction][1];
                map[nextRow][nextCol] = 0;
                if (count == this.distance) break;
            }
            this.distance = 0;
            this.direction = 0;
            this.isConnected = false;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testNum;
    static int n;
    static int[][] map;
    static List<Core> cores;
    static int[][] deltas = new int[][] {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static List<Result> candidate;

    public void solution() throws IOException {
        testNum = Integer.parseInt(br.readLine());
        for (int test = 1; test <= testNum; test++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            cores = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 0 || i == n - 1 || j == 0 || j == n - 1) continue;
                    if (map[i][j] == 1) {
                        cores.add(new Core(i, j));
                    }
                }
            }
            candidate = new LinkedList<>();
            recursive(0);
            Collections.sort(candidate);
            sb.append("#").append(test).append(" ").append(candidate.get(0).cost).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private void recursive(int depth) {
        if (depth == cores.size()) {
            calculateResult();
            return;
        }
        if (noMorePossible()) {
            calculateResult();
            return;
        }
        Core core = cores.get(depth);
        for (int i = 1; i <= 4; i++) {
            int distance = possible(core, i);
            if (distance == -1) continue;
            core.connect(distance, i);
            recursive(depth + 1);
            core.disConnect();
        }
        recursive(depth + 1);
    }

    private void calculateResult() {
        int count = 0;
        int distance = 0;
        for (Core core : cores) {
            if (!core.isConnected) continue;
            count++;
            distance += core.distance;
        }
        candidate.add(new Result(count, distance));
    }

    private boolean noMorePossible() {
        for(Core core : cores) {
            if (core.isConnected) continue;
            for (int i = 1; i <= 4; i++) {
                if (possible(core, i) != -1) return false;
            }
        }
        return true;
    }

    // 어떤 코어가 해당 방향으로 이동 가능한지
    // 가능하다면 거리, 불가능하다면 -1 리턴
    private int possible(Core core, int direction) {
        int nextRow = core.row;
        int nextCol = core.col;
        int count = 0;
        while (nextRow != 0 && nextRow != n - 1 && nextCol != 0 && nextCol != n - 1) {
            count++;
            nextRow += deltas[direction][0];
            nextCol += deltas[direction][1];
            if (map[nextRow][nextCol] == 1) return -1;

            if (nextRow == 0 || nextRow == n - 1 || nextCol == 0 || nextCol == n - 1) return count;

        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        new none1767().solution();
    }
}


/*
1
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
 */
