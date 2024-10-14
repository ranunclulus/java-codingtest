package com.example.javacodingtest.swea;

import java.io.*;
import java.util.*;

/*
 @author ranuinclulus
 @since 2024.10.14
 @link
 @timecomplex
 @performance 113696 kb 947 ms
 @category
 @note
 */
public class none5653 {
    class Point {
        int col;
        int row;

        public Point(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return col == point.col && row == point.row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(col, row);
        }
    }
    class Cell implements Comparable<Cell>{
        int col;
        int row;
        int createdAt;
        int time;
        int state;

        public Cell(int col, int row, int time, int createdAt, int state) {
            this.col = col;
            this.row = row;
            this.time = time;
            this.createdAt = createdAt;
            this.state = state;
        }

        @Override
        public int compareTo(Cell o) {
            return -Integer.compare(this.time, o.time);
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, m, k, answer;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Map<Point, Cell> lived;
    static Set<Point> dead;


    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
            lived = new HashMap<>();
            dead = new HashSet<>();

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    int time = Integer.parseInt(tokenizer.nextToken());
                    if (time == 0) continue;
                    lived.put(new Point(i, j), new Cell(i, j, time, 0, 0));
                }
            }

            for (int time = 1; time <= k; time++) {
                Map<Point, Cell> newLived = new HashMap<>();
                List<Point> newDead = new LinkedList<>();

                for(Map.Entry<Point, Cell> cellEntry : lived.entrySet()) {
                    Cell cell = cellEntry.getValue();

                    if (cell.state == 2) continue; // 죽은 경우
                    if (cell.state == 0 && (cell.createdAt + cell.time) == time) cell.state = 1; // 활성화
                    if (cell.state == 1) {
                        if (cell.createdAt + cell.time + 1 == time) { // 번식
                            for (int i = 0; i < 4; i++) {
                                int nextCol = cell.col + deltas[i][0];
                                int nextRow = cell.row + deltas[i][1];

                                Point next = new Point(nextCol, nextRow);

                                if (lived.containsKey(next) || dead.contains(next)) continue;

                                if ((newLived.containsKey(next) && newLived.get(next).time < cell.time)
                                    || !newLived.containsKey(next)) {
                                        newLived.put(next, new Cell(nextCol, nextRow, cell.time, time, 0));
                                    }
                                }
                            }
                        if (cell.createdAt + cell.time * 2 == time) {
                            cell.state = 2;
                            newDead.add(new Point(cell.col, cell.row));
                        }
                    }
                }
                lived.putAll(newLived);

                for(Point point : newDead) {
                    lived.remove(point);
                }
                dead.addAll(newDead);
            }
            builder.append("#" + test + " " + lived.size()).append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    public static void main(String[] args) throws IOException {
        new none5653().solution();
    }

}
