package com.example.javacodingtest.boj.gold;

import java.io.IOException;
import java.util.*;

class Virus {
    int col;
    int row;
    int num;
    int time;

    Virus(int col, int row, int num, int time) {
        this.col = col;
        this.row = row;
        this.num = num;
        this.time = time;
    }
}

public class five18405 {
    private int mapSize, virusNum, endTime, endCol, endRow;
    private int[][] map;
    private boolean[][] visited;
    private int[] dCol = new int[]{0, 0, 1, -1};
    private int[] dRow = new int[]{1, -1, 0, 0};

    public void solution() throws IOException {
        Scanner sc = new Scanner(System.in);
        mapSize = sc.nextInt();
        virusNum = sc.nextInt();
        map = new int[mapSize][mapSize];
        ArrayList<Virus> virus = new ArrayList<>();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] != 0) {
                    virus.add(new Virus(i, j, map[i][j], 0));
                }
            }
        }

        Collections.sort(virus, new Comparator<Virus>() {
            @Override
            public int compare(Virus o1, Virus o2) {
                return o1.num - o2.num;
            }
        });

        endTime = sc.nextInt();
        endCol = sc.nextInt() - 1;
        endRow = sc.nextInt() - 1;

        Queue<Virus> toVisit = new LinkedList<>(virus);

        while (!toVisit.isEmpty()) {
            Virus now = toVisit.poll();
            if (now.time == endTime) break;

            for (int i = 0; i < 4; i++) {
                int nextCol = now.col + dCol[i];
                int nextRow = now.row + dRow[i];

                if (nextCol < 0 || nextCol >= mapSize || nextRow < 0 || nextRow >= mapSize) continue;
                if (map[nextCol][nextRow] != 0) continue;

                map[nextCol][nextRow] = now.num;
                toVisit.add(new Virus(nextCol, nextRow, now.num, now.time + 1));
            }
        }

        System.out.println(map[endCol][endRow]);
    }

    public static void main(String[] args) throws IOException {
        new five18405().solution();
    }
}
