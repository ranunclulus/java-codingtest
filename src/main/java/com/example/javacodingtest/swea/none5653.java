package com.example.javacodingtest.swea;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/*
 @author ranuinclulus
 @since 2024.10.14
 @link
 @timecomplex
 @performance
 @category
 @note
 */
public class none5653 {
    class Cell {
        int col;
        int row;
        int startTime;
        int endTime;
        String state;

        public Cell(int col, int row, int startTime, int endTime) {
            this.col = col;
            this.row = row;
            this.startTime = startTime;
            this.endTime = endTime;
            this.state = "비활성";
        }
        public Cell(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return col == cell.col && row == cell.row;
        }

        @Override
        public int hashCode() {
            return col * 10 + row;
        }


        @Override
        public String toString() {
            return "Cell{" +
                    "col=" + col +
                    ", row=" + row +
                    ", startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", state='" + state + '\'' +
                    '}';
        }
    }
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder builder = new StringBuilder();
    static StringTokenizer tokenizer;
    static int testNum, n, m, k, hour, answer;
    static int[][] deltas = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<Cell> cells;


    public void solution() throws IOException {
        testNum = Integer.parseInt(reader.readLine());
        for (int test = 1; test <= testNum; test++) {
            tokenizer = new StringTokenizer(reader.readLine());
            hour = 0;
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());
            k = Integer.parseInt(tokenizer.nextToken());
            cells = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int j = 0; j < m; j++) {
                    int value = Integer.parseInt(tokenizer.nextToken());
                    if (value != 0) cells.add(new Cell(i, j, value, value * 2));
                }
            }

            while (hour != k) {
                hour++;
                List<Cell> toSpread = new LinkedList<>();
                for(Cell cell : cells) {
                    if (cell.state.equals("활성")) {
                        toSpread.add(cell);
                    }
                    if (cell.startTime == hour) {
                        cell.state = "활성";
                    }
                    if (cell.endTime == hour) {
                        cell.state = "죽음";
                    }
                }
                for(Cell cell : toSpread) {
                    spread(cell);
                }
            }
            answer = 0;
            for(Cell cell : cells) {
                if (cell.state.equals("죽음")) continue;
                answer++;
            }
            builder.append("#" + test + " " + answer).append("\n");
        }
        writer.write(builder.toString());
        writer.flush();
    }

    private void spread(Cell cell) {
        for (int i = 0; i < 4; i++) {
            int nextCol = cell.col + deltas[i][0];
            int nextRow = cell.row + deltas[i][1];
            Cell newCell = new Cell(nextCol, nextRow);
            if (cells.contains(newCell)) continue;
            newCell.startTime = hour + (cell.endTime - cell.startTime);
            newCell.endTime = newCell.startTime + (cell.endTime - cell.startTime);
            newCell.state = "비활성";
            cells.add(newCell);
        }
    }

    public static void main(String[] args) throws IOException {
        new none5653().solution();
    }

}

/*

1
49 43 283
0 6 0 0 0 10 0 0 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 4 0 8 0 0 0 0 0 0 0 0 0 0 0 0 0 0 4 0 0
0 5 0 0 0 2 0 0 0 0 0 0 8 0 0 8 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 3 7 0 0 0 0 0 0 9 0 1 0 5 0 0 1 0 0 0 0 0 0 0 0 0 0 0 9 0 7 0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 0 8 0 0 0 0 0 0 0 0 0 3 0 0 0 6 0 0 0 0 6 0 0 0 0 0 0
7 0 0 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 0 0 0 0 8 0 0 0 0 0 0 0 0 1 0 0
0 9 0 0 0 0 0 0 0 0 9 6 0 2 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 9
0 0 0 0 1 0 0 0 0 0 0 3 0 0 0 0 0 0 5 0 10 0 0 0 0 0 0 0 0 0 9 4 0 0 0 0 0 0 9 0 9 0 8
0 0 0 0 0 0 0 0 0 7 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 8 0 0 0 0 0 0 0 0 0 0 0 0 0 3 2 0 1
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 3 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 2 0 0 0 0 0
0 0 0 0 0 0 0 0 0 7 0 0 0 2 0 0 0 0 0 0 8 0 0 0 0 10 0 0 1 7 0 8 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 2 0 0 9 0 0 0 0 0 8 0 0 0 0 0 4 0 6 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 6 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 6 0 5 0 0 0 0 0 0 7 0 0 0
8 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 9 0 0 0 0 0 0 5 8
0 0 0 10 0 9 0 8 0 0 0 0 0 0 2 9 0 0 0 7 2 7 0 7 0 0 0 0 2 0 4 3 0 0 0 0 0 0 0 0 0 2 0
1 0 0 0 0 0 0 4 9 1 0 0 0 0 0 0 0 0 0 5 0 0 0 0 6 0 0 5 0 0 0 0 0 0 0 0 0 0 0 3 3 0 0
0 0 0 0 0 0 0 0 0 0 0 0 1 0 3 1 10 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 5 0 1 0 0 0 9 0 0
0 0 0 0 0 0 0 10 0 0 0 0 0 0 9 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 0 0 1 3 0 0
0 0 0 0 0 0 6 0 0 0 1 0 0 2 0 0 0 0 9 0 0 0 0 0 0 0 3 0 0 0 0 0 0 0 0 0 0 0 0 7 7 0 0
0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 5 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 10
0 0 0 0 9 0 8 0 0 0 0 0 0 4 0 0 0 10 8 0 0 0 0 0 0 10 0 0 0 5 0 0 0 0 0 0 0 1 0 0 10 4 7
0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 0 4 0 7 0 0 0 0 0 3 0
0 0 0 0 5 0 3 0 0 0 0 0 0 0 8 1 0 0 7 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 9 0 1 0 0 0 0 10 7 0 0 0 0 0 2 0 0 7 0 0 0 0 0 0 0 7 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 8 2 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 8 0 0 7 0 2 0 0 0 0
0 8 0 0 0 0 0 0 0 0 3 0 0 1 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 5 0 9 0 0 0 0 0
0 0 0 0 0 0 0 0 3 5 0 0 1 0 4 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 5 0 0 4 0 0 0 0 10 8 0 0 0
0 0 0 0 0 0 0 0 4 0 0 7 10 0 10 0 0 0 0 0 0 0 0 0 0 9 0 0 0 0 0 0 0 8 3 9 6 7 0 0 0 0 2
0 0 0 0 0 0 0 0 0 0 0 0 0 5 0 0 0 8 7 10 0 0 0 0 0 0 6 0 0 0 5 0 0 0 0 0 0 0 0 0 0 10 0
7 0 0 0 8 0 0 0 8 9 0 0 0 0 0 0 9 0 0 0 0 0 0 0 0 6 0 0 5 0 0 0 0 0 0 0 0 0 0 3 0 0 0
0 0 0 0 0 0 0 0 0 0 0 6 0 6 0 0 0 0 3 0 0 5 3 0 0 0 0 1 9 0 6 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 7 2 0 0 0 0 0 0 0 0 0 0 5 0 0 0 0 8 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 7 6
0 9 0 0 0 0 0 0 0 0 0 3 0 9 2 0 0 0 4 0 2 9 2 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 8 0 0 0 0
0 0 0 3 0 1 0 0 8 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 0 0 0 7 0 6 0 0 0 0 0 7 0 0 0 0 4 7 10
1 0 0 0 5 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 5 0 8 0 0 0 0 0 0 0 0 3 9 2
5 0 0 0 0 0 0 0 0 0 1 0 0 1 0 0 0 0 6 0 0 0 0 0 0 0 0 9 0 8 0 0 0 0 0 0 0 3 0 0 0 0 0
0 0 0 0 7 0 10 0 0 0 6 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 0 0 0 0 0 0 0 0 0 8 2 3 0 0
0 0 0 0 0 5 0 0 6 0 0 3 0 0 0 0 0 8 0 0 6 0 0 0 8 0 0 5 0 0 0 0 8 0 0 0 0 0 0 0 5 0 1
7 0 9 0 7 0 0 9 0 0 0 0 4 0 0 0 0 0 0 8 1 0 4 0 0 0 0 0 0 0 0 0 4 7 0 0 8 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 4 0 0 0 0 0 0 0 0 0 2 3 1 0 0 4 0 3 10 0 0 0 5
0 0 4 0 0 0 0 0 0 4 4 0 0 0 8 0 4 0 2 0 8 0 0 0 0 0 0 0 9 0 0 0 0 5 0 0 0 0 0 0 0 0 0
0 0 8 0 7 0 0 0 0 0 0 0 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 2 6 0 0 0 0 1 0 0 0 0 4 3
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 10 3 0 0 0 0 0 3 0
0 0 2 0 0 0 0 0 8 5 0 0 0 0 0 0 0 0 0 0 0 0 4 8 0 0 0 0 0 1 0 5 0 0 0 0 2 3 9 0 0 0 0
0 5 8 9 0 0 0 0 0 4 0 0 0 10 0 0 0 1 0 0 0 0 0 10 0 7 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
6 0 0 0 0 0 10 0 5 0 0 0 0 0 0 0 0 0 0 0 6 0 0 0 0 0 0 0 0 10 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 9 0 0 0 0 0 0 2 0 0 0 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 3 0 0 0 0 0 2 4 0
0 3 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 1 0 0 0 0 0 0 0 0 1 0 0 0
0 0 0 9 0 0 0 0 4 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 9 0 0 0 0 0 0 0 0 0 0 0 5 0 0 0 9 2 0
0 0 0 0 0 2 0 0 0 0 0 0 10 0 0 0 0 0 2 0 0 0 8 0 0 0 0 0 0 10 0 0 0 0 0 0 7 0 0 0 0 0 0
 */
